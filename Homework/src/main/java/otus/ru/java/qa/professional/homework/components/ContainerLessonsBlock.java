package otus.ru.java.qa.professional.homework.components;


import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.ru.java.qa.professional.homework.actions.BaseActions;
import otus.ru.java.qa.professional.homework.annotations.Component;
import otus.ru.java.qa.professional.homework.data.Lesson;
import otus.ru.java.qa.professional.homework.exceptions.ComponentLocatorException;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component(".container-lessons")
public class ContainerLessonsBlock extends BaseComponent<ContainerLessonsBlock> {

    private static final String LESSON_DATE_COURSE = ".lessons__new-item-start";

    private static final String LESSON_DATE_SPECIALIZATION = ".lessons__new-item-time";

    private static final String COURSE_NAME = ".lessons__new-item-title";

    private List<WebElement> elCoursesList;

    private String lessonList = ".lessons__new-item";

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
        List<WebElement> initElements = null;
        try {
            initElements = wait.waitForElementsVisible(this.getComponentEntity().findElements(By.cssSelector(lessonList)), guiceScoped.driver);
        } catch (ComponentLocatorException e) {
            System.out.println(e.getMessage());
        }

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

    public WebElement getEarlyOrLaterCourse(Boolean byDate) {
        System.out.println(byDate ? "|-------|-------| Looking for earlier course" : "|-------|-------| Looking for later course");

        var currentWebElement = elCoursesList.stream()
                .filter(element -> !element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().matches("^[А-Я].*[а-я]"))
                .reduce((el1, el2) -> {
                    Date dateFirstElementDate = getDate(el1);
                    Date dateSecondElementDate = getDate(el2);
                    if (byDate) {
                        if (dateFirstElementDate.compareTo(dateSecondElementDate) < 0) return el1;
                        else return el2;
                    } else if (!byDate) {
                        if (dateFirstElementDate.compareTo(dateSecondElementDate) > 0) return el1;
                        else return el2;
                    } else {
                        return el1;
                    }
                });
        String selectedCourse = currentWebElement.map(element -> getElementTextByCss(element, COURSE_NAME)).get();
        String date;
        if (currentWebElement.get().findElements(By.cssSelector(LESSON_DATE_COURSE)).size() == 0) {
            date = currentWebElement.map(element -> getElementTextByCss(element, LESSON_DATE_SPECIALIZATION)).get();
        } else date = currentWebElement.map(element -> getElementTextByCss(element, LESSON_DATE_COURSE)).get();
        System.out.println("|-------|-------| " + selectedCourse + " has been selected! Date of start is " + date);

        return currentWebElement.get();
    }

    private Date toDate(String sDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM", new Locale("ru"));
        return simpleDateFormat.parse(sDate);
    }

    private String getElementTextByCss(WebElement element, String lessonDate) {
        return element.findElement(By.cssSelector(lessonDate)).getText();
    }

    private Date getDate(WebElement element) {
        Date currentDate = new Date();
        if (element.findElements(By.cssSelector(LESSON_DATE_COURSE)).size() == 0) {
            String[] date = getElementTextByCss(element, LESSON_DATE_SPECIALIZATION).split(" ");
            try {
                currentDate = toDate(date[0] + " " + date[1]);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Date " + date[0] + " " + date[1] + " is incorrect parsed");
            }
        } else {
            try {
                currentDate = toDate(getElementTextByCss(element, LESSON_DATE_COURSE).substring(2));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Date " + getElementTextByCss(element, LESSON_DATE_COURSE) + " is incorrect parsed");
            }
        }
        return currentDate;
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
