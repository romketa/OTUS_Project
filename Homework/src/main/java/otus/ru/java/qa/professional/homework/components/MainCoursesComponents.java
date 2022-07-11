package otus.ru.java.qa.professional.homework.components;


import com.mifmif.common.regex.Main;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.ru.java.qa.professional.homework.actions.BaseActions;
import otus.ru.java.qa.professional.homework.data.LessonType;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withMarginOf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainCoursesComponents extends BaseComponents {

    private static final String LESSON_DATE_COURSE = ".lessons__new-item-start";

    private static final String LESSON_DATE_SPECIALIZATION = ".lessons__new-item-time";

    private static final String COURSE_NAME = ".lessons__new-item-title";

    @FindBy(css = ".lessons__new-item")
    private List<WebElement> elCoursesList;


    @Inject
    public MainCoursesComponents(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public MainCoursesComponents filterByCourseName(String nameOfCourse) {
        System.out.println("|-------|-------| Courses have been filtered by input name:");
        elCoursesList.stream()
                .filter(element -> getElementTextByCss(element, COURSE_NAME).contains(nameOfCourse))
                .map(element -> getElementTextByCss(element, COURSE_NAME))
                .forEach(s -> System.out.println("|-------|-------| " + s));
        return this;
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

    public MainCoursesComponents moveToElementAndHighlight(WebElement element) {
        SpecialEventListener specialEventListener = new SpecialEventListener();
        WebElement currentElement = CustomAndDefaultWait.waitForElementVisible(element, guiceScoped.driver);
        specialEventListener.scrollIntoView(element, guiceScoped.driver);
        BaseActions.moveToElementAction(currentElement, guiceScoped.driver);
        specialEventListener.beforeClickOn(currentElement, guiceScoped.driver);
        specialEventListener.afterClickOn(currentElement, guiceScoped.driver);
        return this;
    }


    public String getCourseName(WebElement element) {
        WebElement currentElement = CustomAndDefaultWait.waitForElementVisible(element, guiceScoped.driver);
        return getElementTextByCss(currentElement, COURSE_NAME);
    }

    public MainCoursesComponents clickByElement(WebElement element) {
        BaseActions.clickByElementAction(element, guiceScoped.driver);
        CustomAndDefaultWait.waitForElementInvisible(element, guiceScoped.driver);
        return this;
    }

    public void checkCourseName(String courseName, LessonCourseComponents lessonCourseComponents) {
        if (courseName.startsWith(LessonType.SPECIALIZATIONS.getName())) {
            assertThat(courseName)
                    .as("|-------|-------| Tittle {} on a page Specialization isn't correct", lessonCourseComponents.getSpecializationName())
                    .isEqualTo(lessonCourseComponents.getSpecializationName());
        } else {
            assertThat(courseName)
                    .as("|-------|-------| Tittle {} on a page Specialization isn't correct", lessonCourseComponents.getCourseName())
                    .isEqualTo(lessonCourseComponents.getCourseName());
        }
        System.out.println("|-------|-------| Course name is correctly displayed");
    }

    public MainCoursesComponents checkThatCourseExistOnPage(WebElement element) {
        assertThat(element)
                .isEqualTo(CustomAndDefaultWait.waitForCourseName(element, guiceScoped.driver));
        return this;
    }
}
