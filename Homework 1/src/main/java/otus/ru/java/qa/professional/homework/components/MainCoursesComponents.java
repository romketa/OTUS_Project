package otus.ru.java.qa.professional.homework.components;


import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import otus.ru.java.qa.professional.homework.actions.BaseActions;
import otus.ru.java.qa.professional.homework.data.Course;
import otus.ru.java.qa.professional.homework.data.LessonType;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainCoursesComponents extends BaseComponents {

    private static final String LESSON_DATE_COURSE = ".lessons__new-item-start";

    private static final String LESSON_DATE_SPECIALIZATION = ".lessons__new-item-time";

    private static final String COURSE_NAME = ".lessons__new-item-title"; //".//div[contains(@class, 'lessons__new-item-title')]";

    private static final String ELEMENT_FOR_HIGHLIGHTING = ".//div[contains(@class, 'lessons__new-item-container')]";

    public static List<Course> coursesList = new ArrayList<>();

    public static Course selectedCourse = null;

    @FindBy(xpath = "//div[text()='Популярные курсы']/following-sibling::*[@class='lessons']/a")
    public List<WebElement> blockOfCourses;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    public List<WebElement> blockSpecializations;

    @FindBy(xpath = "//div[@class='lessons']/a")
    public List<WebElement> blockOfCoursesAndSpecializations;

    @FindBy(css = ".lessons__new-item-container")
    private List<WebElement> elCoursesList;

    public MainCoursesComponents(WebDriver driver) {
        super(driver);
    }

    public MainCoursesComponents collectCourses() throws ParseException {
        for (WebElement hrefWebElement : blockOfCourses) {
            WebElement visibleElementOnPage = CustomAndDefaultWait.waitForElementVisible(hrefWebElement, driver);
            String courseDate = getElementTextByCss(visibleElementOnPage, LESSON_DATE_COURSE);
            String urlCourse = visibleElementOnPage.getAttribute("href");
            String courseName = getElementTextByCss(visibleElementOnPage, COURSE_NAME);
            WebElement elementForBorderHighlight = visibleElementOnPage.findElement(By.xpath(ELEMENT_FOR_HIGHLIGHTING));

            if (courseDate.startsWith("С")) {
                String result = courseDate.substring(2);
                coursesList.add(new Course(courseName, toDate(result), hrefWebElement, urlCourse, elementForBorderHighlight, LessonType.COURSES));
            }
        }
        for (WebElement hrefWebElement : blockSpecializations) {
            WebElement visibleElementOnPage = CustomAndDefaultWait.waitForElementVisible(hrefWebElement, driver);
            String courseDate = getElementTextByCss(visibleElementOnPage, LESSON_DATE_SPECIALIZATION);
            String urlCourse = visibleElementOnPage.getAttribute("href");
            String courseName = getElementTextByCss(visibleElementOnPage, COURSE_NAME);
            WebElement elementForBorderHighlight = visibleElementOnPage.findElement(By.xpath(ELEMENT_FOR_HIGHLIGHTING));

            if (courseDate.matches(".*\\d.*")) {
                String[] s = courseDate.split(" ");
                String result = s[0] + " " + s[1];
                coursesList.add(new Course(courseName, toDate(result), hrefWebElement, urlCourse, elementForBorderHighlight, LessonType.SPECIALIZATIONS));
            }
        }
        return this;
    }

    public MainCoursesComponents filterByCourseName(String nameOfCourse) {
        System.out.println("Courses have been filtered by input name:");
        elCoursesList.stream()
                .filter(element -> getElementTextByCss(element, COURSE_NAME).contains(nameOfCourse))
                .map(element -> getElementTextByCss(element, COURSE_NAME))
                .forEach(System.out::println);
        return this;
    }

    private Date toDate(String sDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM", new Locale("ru"));
        return simpleDateFormat.parse(sDate);
    }

    public WebElement getEarlyOrLaterCourse(Boolean byDate) {
        System.out.println(byDate ? "Looking for earlier course" : "Looking for later course");

        var selectedDate = elCoursesList.stream()
                .filter(element -> !element.findElement(By.cssSelector(LESSON_DATE_SPECIALIZATION)).getText().startsWith("О дате старта"))
                .reduce((el1, el2) -> {
                    Date dateFirstElementDate = new Date();
                    Date dateSecondElementDate = new Date();
                    WebElement element = getSortedWebElementByDate(el1, el2, dateFirstElementDate, dateSecondElementDate, byDate);
                    return element;
                });
        String selectedCourse = selectedDate.map(element -> getElementTextByCss(element, COURSE_NAME)).get();
        String date;
        if (selectedDate.get().findElements(By.cssSelector(LESSON_DATE_COURSE)).size() == 0) {
            date = selectedDate.map(element -> getElementTextByCss(element, LESSON_DATE_SPECIALIZATION)).get();
        } else date = selectedDate.map(element -> getElementTextByCss(element, LESSON_DATE_COURSE)).get();
        System.out.println(selectedCourse + " has been selected! Date of start is " + date);

        return selectedDate.get();
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
        BaseActions.moveToElementAction(currentElement, driver);
        specialEventListener.beforeClickOn(currentElement, driver); //.findElement(By.xpath(elementForHighlighting)
        specialEventListener.afterClickOn(currentElement, driver); //.findElement(By.xpath(elementForHighlighting)
        return this;
    }

    public MainCoursesComponents goToLessonPage(WebElement element) {
        BaseActions.clickByElementAction(element, driver);
        return this;
    }

    public void checkCourseName(WebElement element, LessonCourseComponents lessonCourseComponents) {
        if (getElementTextByCss(element, COURSE_NAME).startsWith(LessonType.SPECIALIZATIONS.getName())) {
            Assert.assertEquals(getElementTextByCss(element, COURSE_NAME), lessonCourseComponents.getSpecializationName());
        } else {
            Assert.assertEquals(getElementTextByCss(element, COURSE_NAME), lessonCourseComponents.getCourseName());
        }
    }

    public MainCoursesComponents checkThatCourseExistOnPage(WebElement element) {
        Assert.assertEquals(element, CustomAndDefaultWait.waitForCourseName(element, driver));
        return this;
    }
}
