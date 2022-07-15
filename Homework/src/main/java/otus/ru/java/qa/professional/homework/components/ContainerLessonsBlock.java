package otus.ru.java.qa.professional.homework.components;


import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.ru.java.qa.professional.homework.actions.BaseActions;
import otus.ru.java.qa.professional.homework.annotations.Component;
import otus.ru.java.qa.professional.homework.data.Lesson;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component(".container-lessons")
public class ContainerLessonsBlock extends BaseComponent<ContainerLessonsBlock> {

    private static final String LESSON_DATE_COURSE = ".lessons__new-item-start";

    private static final String LESSON_DATE_SPECIALIZATION = ".lessons__new-item-time";

    private static final String COURSE_NAME = ".lessons__new-item-title";

    private static final String LESSON_LIST = ".lessons__new-item";

    @FindBy(css = ".lessons__new-item")
    private List<WebElement> elCoursesList;

    @Inject
    public ContainerLessonsBlock(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @Inject
    private BaseActions actions;

    @Inject
    private CustomAndDefaultWait wait;

    public ContainerLessonsBlock filterByCourseName(String nameOfCourse) {
        System.out.println("|-------|-------| Courses have been filtered by input name:");
        List<WebElement> currentWebElements = wait.waitForElementsVisible(elCoursesList, guiceScoped.driver);
        currentWebElements.stream()
                .filter(element -> getElementTextByCss(element, COURSE_NAME).contains(nameOfCourse))
                .map(element -> getElementTextByCss(element, COURSE_NAME))
                .forEach(s -> System.out.println("|-------|-------| " + s));
        return this;
    }

    public Lesson findLessonInBlock(String nameOfCourse) {
        List<WebElement> initElements = initElements(LESSON_LIST);

        try {
            WebElement currentWebElement = initElements.stream()
                    .filter(element -> getElementTextByCss(element, COURSE_NAME).contains(nameOfCourse))
                    .findAny()
                    .get();
            String lessonName = getElementTextByCss(currentWebElement, COURSE_NAME);
            String href = currentWebElement.getAttribute("href");
            return new Lesson(lessonName, href);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.out.println("Looking '" + nameOfCourse + "' lesson is not present on main page");
        }
        return null;
    }

    private LocalDate getLocalDate(String date) {
        Locale loc_rus = new Locale("ru", "RU");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("d[uu]-MM-yyyy", loc_rus);
        return LocalDate.parse(date, dtf1);
    }

    public Lesson findLessonInBlockByLocalDate(String sDate) {
        LocalDate lDate = getLocalDate(sDate);
        List<WebElement> initElements = initElements(LESSON_LIST);
        var currentWebElement = initElements.stream()
                .filter(element -> !element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().matches("^[А-Я].*[а-я]"))
                .filter(element -> getDateToLocalDate(element, LESSON_DATE_COURSE, LESSON_DATE_SPECIALIZATION).isAfter(lDate) || getDateToLocalDate(element, LESSON_DATE_COURSE, LESSON_DATE_SPECIALIZATION).isEqual(lDate))
                .reduce((el1, el2) -> {
                    LocalDate dateFirstElementDate = getDateToLocalDate(el1, LESSON_DATE_COURSE, LESSON_DATE_SPECIALIZATION);
                    LocalDate dateSecondElementDate = getDateToLocalDate(el2, LESSON_DATE_COURSE, LESSON_DATE_SPECIALIZATION);
                    if (dateFirstElementDate.compareTo(dateSecondElementDate) < 0) return el1;
                    else return el2;
                }).get();
        String lessonName = getElementTextByCss(currentWebElement, COURSE_NAME);
        String href = currentWebElement.getAttribute("href");
        LocalDate currentDate = getDateToLocalDate(currentWebElement, LESSON_DATE_COURSE, LESSON_DATE_SPECIALIZATION);
        return new Lesson(lessonName, href, currentDate);
    }

    public ContainerLessonsBlock moveToElementAndHighlight(WebElement element) {
        SpecialEventListener specialEventListener = new SpecialEventListener();
        WebElement currentElement = wait.waitForElementVisible(element, guiceScoped.driver);
        specialEventListener.scrollIntoView(element, guiceScoped.driver);
        actions.moveToElementAction(currentElement, guiceScoped.driver);
        specialEventListener.beforeClickOn(currentElement, guiceScoped.driver);
        specialEventListener.afterClickOn(currentElement, guiceScoped.driver);
        return this;
    }


    public String getCourseName(WebElement element) {
        WebElement currentElement = wait.waitForElementVisible(element, guiceScoped.driver);
        return getElementTextByCss(currentElement, COURSE_NAME);
    }

    public ContainerLessonsBlock clickByElement(WebElement element) {
        actions.clickByElementAction(element, guiceScoped.driver);
        wait.waitForElementInvisible(element, guiceScoped.driver);
        return this;
    }

    public ContainerLessonsBlock checkThatCourseExistOnPage(WebElement element) {
        assertThat(element)
                .isEqualTo(wait.waitForCourseName(element, guiceScoped.driver));
        return this;
    }
}
