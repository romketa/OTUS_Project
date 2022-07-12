package otus.ru.java.qa.professional.homework.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import otus.ru.java.qa.professional.homework.actions.BaseActions;
import otus.ru.java.qa.professional.homework.data.LessonType;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainCoursesComponents extends BaseComponents {

    private static final String LESSON_DATE_COURSE = ".lessons__new-item-start";

    private static final String LESSON_DATE_SPECIALIZATION = ".lessons__new-item-time";

    private static final String COURSE_NAME = ".lessons__new-item-title";

    @FindBy(css = ".lessons__new-item")
    private List<WebElement> elCoursesList;

    public MainCoursesComponents(WebDriver driver) {
        super(driver);
    }

    public MainCoursesComponents filterByCourseName(String nameOfCourse) {
        System.out.println("|-------|-------| Courses have been filtered by input name:");
        elCoursesList.stream()
                .filter(element -> getElementTextByCss(element, COURSE_NAME).contains(nameOfCourse))
                .forEach(s -> System.out.println("|-------|-------| " + getElementTextByCss(s, COURSE_NAME)));
        return this;
    }

    public WebElement getEarlyDateCourse() {
        System.out.println("|-------|-------| Looking for earlier course");

        var currentWebElement = elCoursesList.stream()
                .filter(element -> !element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().matches("^[А-Я].*[а-я]"))
                .reduce((el1, el2) -> {
                    Date dateFirstElementDate = getDate(el1);
                    Date dateSecondElementDate = getDate(el2);
                    if (dateFirstElementDate.compareTo(dateSecondElementDate) < 0) return el1;
                    else return el2;
                });
        currentWebElement.stream()
                .forEach(s -> {
                    if (getElementTextByCss(s, COURSE_NAME).startsWith(LessonType.SPECIALIZATIONS.getName())) {
                        System.out.println("|-------|-------| " + getElementTextByCss(s, COURSE_NAME) + " has been selected! Date of start is" + getElementTextByCss(s, LESSON_DATE_SPECIALIZATION));
                    } else
                        System.out.println("|-------|-------| " + getElementTextByCss(s, COURSE_NAME) + " has been selected! Date of start is" + getElementTextByCss(s, LESSON_DATE_COURSE));
                });
        return currentWebElement.get();
    }

    public WebElement getOlderDateCourse() {
        System.out.println("|-------|-------| Looking for later course");

        var currentWebElement = elCoursesList.stream()
                .filter(element -> !element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().matches("^[А-Я].*[а-я]"))
                .reduce((el1, el2) -> {
                    Date dateFirstElementDate = getDate(el1);
                    Date dateSecondElementDate = getDate(el2);
                    if (dateFirstElementDate.compareTo(dateSecondElementDate) > 0) return el1;
                    else return el2;
                });
        currentWebElement.stream()
                .forEach(s -> {
                    if (getElementTextByCss(s, COURSE_NAME).startsWith(LessonType.SPECIALIZATIONS.getName())) {
                        System.out.println("|-------|-------| " + getElementTextByCss(s, COURSE_NAME) + " has been selected! Date of start is" + getElementTextByCss(s, LESSON_DATE_SPECIALIZATION));
                    } else
                        System.out.println("|-------|-------| " + getElementTextByCss(s, COURSE_NAME) + " has been selected! Date of start is" + getElementTextByCss(s, LESSON_DATE_COURSE));
                });
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
        WebElement currentElement = CustomAndDefaultWait.waitForElementVisible(element, driver);
        specialEventListener.scrollIntoView(element, driver);
        BaseActions.moveToElementAction(currentElement, driver);
        specialEventListener.beforeClickOn(currentElement, driver);
        specialEventListener.afterClickOn(currentElement, driver);
        return this;
    }


    public String getCourseName(WebElement element) {
        WebElement currentElement = CustomAndDefaultWait.waitForElementVisible(element, driver);
        return getElementTextByCss(currentElement, COURSE_NAME);
    }

    public MainCoursesComponents clickByElement(WebElement element) {
        BaseActions.clickByElementAction(element, driver);
        CustomAndDefaultWait.waitForElementInvisible(element, driver);
        return this;
    }

    public void checkCourseName(String courseName, LessonCourseComponents lessonCourseComponents) {
        if (courseName.startsWith(LessonType.SPECIALIZATIONS.getName())) {
            Assert.assertEquals(courseName, lessonCourseComponents.getSpecializationName(), "|-------|-------| Tittle on a page Specialization isn't correct");
        } else {
            Assert.assertEquals(courseName, lessonCourseComponents.getCourseName(), "|-------|-------| Tittle on a page Course isn't correct");
        }
        System.out.println("|-------|-------| Course name is correctly displayed");
    }

    public MainCoursesComponents checkThatCourseExistOnPage(WebElement element) {
        Assert.assertEquals(element, CustomAndDefaultWait.waitForCourseName(element, driver));
        return this;
    }
}
