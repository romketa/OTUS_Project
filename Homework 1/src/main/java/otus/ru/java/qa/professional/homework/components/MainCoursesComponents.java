package otus.ru.java.qa.professional.homework.components;


import com.mifmif.common.regex.Main;
import org.jetbrains.annotations.NotNull;
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
                .map(element -> getElementTextByCss(element, COURSE_NAME))
                .forEach(s -> System.out.println("|-------|-------| " + s));
        return this;
    }

    public WebElement getEarlyOrLaterCourse(Boolean byDate) {
        System.out.println(byDate ? "|-------|-------| Looking for earlier course" : "|-------|-------| Looking for later course");

        var currentWebElement = elCoursesList.stream()
                //.filter(element -> !element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().startsWith("О дате старта"))
                .filter(element -> element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().matches("\\d.*\\s.*"))
                .reduce((el1, el2) -> {
                    Date dateFirstElementDate = new Date();
                    Date dateSecondElementDate = new Date();
                    WebElement element = getSortedWebElementByDate(el1, el2, dateFirstElementDate, dateSecondElementDate, byDate);
                    return element;
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

    @NotNull
    private WebElement getSortedWebElementByDate(WebElement el1, WebElement el2, Date dateFirstElementDate, Date dateSecondElementDate, Boolean byDate) {
        if (el1.findElements(By.cssSelector(LESSON_DATE_COURSE)).size() == 0) {
            String[] date = getElementTextByCss(el1, LESSON_DATE_SPECIALIZATION).split(" ");
            try {
                dateFirstElementDate = toDate(date[0] + " " + date[1]);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Date " + date[0] + " " + date[1] + "is incorrect parsed");
            }
        } else {
            try {
                dateFirstElementDate = toDate(getElementTextByCss(el1, LESSON_DATE_COURSE).substring(2));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Date " + getElementTextByCss(el1, LESSON_DATE_COURSE) + "is incorrect parsed");
            }
        }

        if (el2.findElements(By.cssSelector(LESSON_DATE_COURSE)).size() == 0) {
            String[] date = getElementTextByCss(el2, LESSON_DATE_SPECIALIZATION).split(" ");
            try {
                dateSecondElementDate = toDate(date[0] + " " + date[1]);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Date " + date[0] + " " + date[1] + "is incorrect parsed");
            }
        } else {
            try {
                dateSecondElementDate = toDate(getElementTextByCss(el2, LESSON_DATE_COURSE).substring(2));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Date " + getElementTextByCss(el2, LESSON_DATE_COURSE) + "is incorrect parsed");
            }
        }
        if (byDate) return dateFirstElementDate.getTime() <= dateSecondElementDate.getTime() ? el1 : el2;
        else return dateFirstElementDate.getTime() >= dateSecondElementDate.getTime() ? el1 : el2;
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



    public String getCourseName(WebElement element){
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
