package otus.java.qa.professional.homework.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import otus.java.qa.professional.homework.data.Course;
import otus.java.qa.professional.homework.data.LessonType;
import otus.java.qa.professional.homework.pages.LessonPage;
import otus.java.qa.professional.homework.waiters.Waiter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainCoursesComponents extends BaseComponents {

    @FindBy(xpath = "//div[@class='lessons']/a")
    public List<WebElement> blockOfCoursesAndSpecializations;

    private String nameOfCourse = ".//div[contains(@class, 'lessons__new-item-title')]";

    private String elementForHighlighting = ".//div[contains(@class, 'lessons__new-item-container')]";
    @FindBy(xpath = "//div[text()='Популярные курсы']/following-sibling::*[@class='lessons']/a")
    public List<WebElement> blockOfCourses;

    public String dateOfCourse = ".//*[@class='lessons__new-item-start']";

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    public List<WebElement> blockSpecializations;

    private String dateOfSpecialization = ".//div[@class='lessons__new-item-time']";

    public static List<Course> coursesList = new ArrayList<>();

    public static Course selectedCourse = null;

    public MainCoursesComponents(WebDriver driver) {
        super(driver);
    }

    public MainCoursesComponents collectCourses() throws ParseException {
        for (WebElement hrefWebElement : blockOfCourses) {
            WebElement visibleElementOnPage = waiter.waitForElementVisible(hrefWebElement);
            String courseDate = visibleElementOnPage.findElement(By.xpath(dateOfCourse)).getText();
            String urlCourse = visibleElementOnPage.getAttribute("href");
            String courseName = visibleElementOnPage.findElement(By.xpath(nameOfCourse)).getText();
            WebElement elementForBorderHighlight = visibleElementOnPage.findElement(By.xpath(elementForHighlighting));

            if (courseDate.startsWith("С")) {
                String result = courseDate.substring(2);
                coursesList.add(new Course(courseName, toDate(result), hrefWebElement, urlCourse, elementForBorderHighlight, LessonType.COURSES));
            }
        }
        for (WebElement hrefWebElement : blockSpecializations) {
            WebElement visibleElementOnPage = waiter.waitForElementVisible(hrefWebElement);
            String courseDate = visibleElementOnPage.findElement(By.xpath(dateOfSpecialization)).getText();
            String urlCourse = visibleElementOnPage.getAttribute("href");
            String courseName = visibleElementOnPage.findElement(By.xpath(nameOfCourse)).getText();
            WebElement elementForBorderHighlight = visibleElementOnPage.findElement(By.xpath(elementForHighlighting));

            if (courseDate.matches(".*\\d.*")) {
                String[] s = courseDate.split(" ");
                String result = s[0] + " " + s[1];
                coursesList.add(new Course(courseName, toDate(result), hrefWebElement, urlCourse, elementForBorderHighlight, LessonType.SPECIALIZATIONS));
            }
        }
        return this;
    }

    public MainCoursesComponents filterByCourseName(String nameOfCourse) {
        coursesList = coursesList.stream()
                .filter(courses -> courses.getName().contains(nameOfCourse))
                .collect(Collectors.toList());
        System.out.println("Courses have been filtered by input name:");
        coursesList.stream()
                .map(course -> course.getName())
                .forEach(System.out::println);
        return this;
    }

    private Date toDate(String sDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM");
        return simpleDateFormat.parse(sDate);
    }

    public MainCoursesComponents getEarlyOrLaterCourse(Boolean byDate) {
        System.out.println(byDate ? "Looking for earlier course" : "Looking for later course");
        Date soonOrLateDate = coursesList.stream()
                .map(courses -> courses.getDate())
                .distinct()
                .sorted()
                .reduce((date, date2) -> {
                    if (byDate) {
                        return date.getTime() <= date2.getTime() ? date : date2;
                    } else return date.getTime() >= date2.getTime() ? date : date2;
                }).get();

        List<Course> courseList = coursesList.stream()
                .filter(courses -> courses.getDate().equals(soonOrLateDate)).toList();

        selectedCourse = courseList.get(new Random().nextInt(courseList.size()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);

        System.out.println(selectedCourse.getName() + " has been selected! Date of start is " + simpleDateFormat.format(soonOrLateDate));
        return this;
    }

    public MainCoursesComponents moveToSelectedCourse() {
        WebElement element = waiter.waitForElementVisible(selectedCourse.getElement());
        actions.moveToElement(element).build().perform();
        mouseListeners.beforeClickOn(selectedCourse.getElementForBorderHighlight(), driver); //.findElement(By.xpath(elementForHighlighting)
        mouseListeners.afterClickOn(selectedCourse.getElementForBorderHighlight(), driver); //.findElement(By.xpath(elementForHighlighting)
        return this;
    }

    public LessonCourseComponents getCoursePage() {
        actions.click(selectedCourse.getElement()).build().perform();
        return new LessonCourseComponents(driver);
    }

    public void checkCourseName(){
        LessonCourseComponents lessonCourseComponents = new LessonCourseComponents(driver);
        if (selectedCourse.getTypeOfLesson() == LessonType.COURSES){
            Assert.assertEquals(selectedCourse.getName(), lessonCourseComponents.getCourseName());
        } else if (selectedCourse.getTypeOfLesson() == LessonType.SPECIALIZATIONS){
            Assert.assertEquals(selectedCourse.getName(), lessonCourseComponents.getSpecializationName());
        }
    }

    public MainCoursesComponents checkThatCourseExistOnPage() {
        Assert.assertEquals(selectedCourse.getElement(), waiter.waitForCourseName(selectedCourse.getElement()));
        return this;
    }
}
