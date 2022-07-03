package otus.java.qa.professional.homework.components;


import com.mifmif.common.regex.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import otus.java.qa.professional.homework.data.Course;
import otus.java.qa.professional.homework.pages.CoursePage;

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

    /*public MainCoursesComponents filterByName(String name) throws ParseException {

        for (WebElement courseWebElement : blockOfCoursesAndSpecializations) {
            String courseName = courseWebElement.findElement(By.xpath(nameOfCourse)).getText();
            String urlCourse = courseWebElement.getAttribute("href");
            String courseDate = courseWebElement.findElement(By.xpath(dateOfCourse)).getText();
            if (courseName.contains(name)) {
                coursesList.add(new Course(courseName, toDate(courseDate), courseWebElement, urlCourse));
            }
        }
        System.out.println("Courses have been filtered by input name:");
        return this;
    }*/

    public MainCoursesComponents collectCourses() throws ParseException {
        for (WebElement hrefWebElement : blockOfCourses) {
            WebElement visibleElementOnPage = waiter.waitForElementVisible(hrefWebElement);
            String courseDate = visibleElementOnPage.findElement(By.xpath(dateOfCourse)).getText();
            String urlCourse = visibleElementOnPage.getAttribute("href");
            String courseName = visibleElementOnPage.findElement(By.xpath(nameOfCourse)).getText();
            WebElement elementForBorderHighlight = visibleElementOnPage.findElement(By.xpath(elementForHighlighting));

            if (courseDate.startsWith("С")) {
                String result = courseDate.substring(2);
                coursesList.add(new Course(courseName, toDate(result), hrefWebElement, urlCourse, elementForBorderHighlight));
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
                coursesList.add(new Course(courseName, toDate(result), hrefWebElement, urlCourse, elementForBorderHighlight));
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

    /*public MainCoursesComponents sortByDate(Boolean byDate){
        selectedCourse = getEarlyOrLaterCourse(byDate);
        return this;
    }*/

    public MainCoursesComponents moveToSelectedCourse() {
        WebElement element = waiter.waitForElementVisible(selectedCourse.getElement());
        actions.moveToElement(element).build().perform();
        mouseListeners.beforeClickOn(selectedCourse.getElementForBorderHighlight(), driver); //.findElement(By.xpath(elementForHighlighting)
        mouseListeners.afterClickOn(selectedCourse.getElementForBorderHighlight(), driver); //.findElement(By.xpath(elementForHighlighting)
        return this;
    }

    public CoursePage checkCoursePage() {
        actions.moveToElement(selectedCourse.getElement()).click().build().perform();
        return new CoursePage(driver);
    }

    public MainCoursesComponents getCoursesFromMainPage() {
        for (WebElement hrefWebElement : blockOfCoursesAndSpecializations) {
            String courseName = hrefWebElement.findElement(By.xpath(nameOfCourse)).getText();
            //mapCourseNamesAndCourseLink.put(courseName, hrefWebElement);
        }
        System.out.println("Courses haven't been filtered by input name:");
        //mapCourseNamesAndCourseLink.forEach((s, webElement) -> System.out.println(s));
        return this;
    }

    public MainCoursesComponents checkThatCourseExistOnPage() {
        Assert.assertEquals(selectedCourse.getElement(), waiter.waitForCourseName(selectedCourse.getElement()));
        /*coursesList.stream()
                .map(courses -> courses.getName())
                .forEach(courseName -> Assert.assertNotNull(waiter.waitForCourseName(courseName)));*/
        return this;
    }
}
