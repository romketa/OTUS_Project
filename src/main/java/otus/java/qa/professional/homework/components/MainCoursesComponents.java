package otus.java.qa.professional.homework.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import otus.java.qa.professional.homework.data.Courses;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MainCoursesComponents extends BaseComponents {

    /*private String nameOfCourse = "//div[contains(@class, 'lessons__new-item-title')]";

    private String famousCourses = "//div[@class='text-center']/preceding-sibling::*[@class='lessons']/a";
*/


/*
    @FindBy(xpath = "//div[@class='text-center']/preceding-sibling::*[@class='lessons']/a")
    private List<WebElement> blockFamousCourses;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    private List<WebElement> blockSpecializations;

    @FindBy(xpath = "//div[@class='text-center']/following-sibling::*[@class='lessons']/a")
    private List<WebElement> blockRecommendationCourses;

    @FindBy(xpath = "//div[text()='Популярные курсы']/following-sibling::*[@class='lessons']/a")
    private List<WebElement> blockOfCourses;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    private List<WebElement> blockSpecializations;

        private String nameOfCourse = ".//div[contains(@class, 'lessons__new-item-title')]";

    private String dateOfCourse = ".//span[@class='lessons__new-item-calendar']";

    private String dateOfSpecialization = ".//div[@class='lessons__new-item-time']";

    private String datesOfCourse = ".lessons__new-item-calendar";
        private String datesOfSpecializations = ":not(span) ~ .lessons__new-item-time";
 */


    @FindBy(xpath = "//div[@class='lessons']/a") //было .lessons > a
    public List<WebElement> blockOfCoursesAndSpecializations;

    public String cssCourseName = ".lessons__new-item-title";

    private String nameOfCourse = ".//div[contains(@class, 'lessons__new-item-title')]";

    private String elementForHighlighting = "//div[contains(@class, 'lessons__new-item-bg-right')]";
    @FindBy(xpath = "//div[text()='Популярные курсы']/following-sibling::*[@class='lessons']/a")
    public List<WebElement> blockOfCourses;

    public String dateOfCourse = ".//*[@class='lessons__new-item-start']";

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    //".container-padding-bottom .lessons > a "
    public List<WebElement> blockSpecializations;

    private String dateOfSpecialization = ".//div[@class='lessons__new-item-time']";
    //public String dateOfSpecializations = ".lessons__new-item-time";

//*[@class='lessons']/a//span[@class='lessons__new-item-calendar']

    //    .lessons > a .lessons__new-item-calendar
//.lessons > a :not(span) ~ .lessons__new-item-time
    /*
    @FindBy(css = ".container-lessons .lessons > a .lessons__new-item-time")
    private List<WebElement> getListOfStartDates;

    @FindBy(css = ".container-lessons .lessons > a .lessons__new-item-start")
    private List<WebElement> listOfStartDatesOne;

    @FindBy(css = ".container-lessons .lessons > a .lessons__new-item-courses ~ .lessons__new-item-time")
    private List<WebElement> listOfStartDatesSecond;
*/
    public static Map<String, WebElement> mapCourseNamesAndCourseLink = new HashMap<>();

    public static Map<Date, WebElement> mapDatesAndCourseLink = new HashMap<>();

    public static List<Courses> coursesList = new ArrayList<>();

    public MainCoursesComponents(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getListOfTextFromBlock(List<WebElement> block, String textInput) {
        ArrayList<String> result = new ArrayList<>();
        for (WebElement webElement : block) {
            String text = webElement.findElement(By.xpath(textInput)).getText();
            result.add(text);
        }
        return result;
    }

    public MainCoursesComponents filterByName(String name) throws ParseException {

        for (WebElement courseWebElement : blockOfCoursesAndSpecializations) {
            String courseName = courseWebElement.findElement(By.xpath(nameOfCourse)).getText();
            String urlCourse = courseWebElement.getAttribute("href");
            String courseDate = courseWebElement.findElement(By.xpath(dateOfCourse)).getText();
            if (courseName.contains(name)) {
                mapCourseNamesAndCourseLink.put(courseName, courseWebElement);
                coursesList.add(new Courses(courseName, toDate(courseDate), courseWebElement, urlCourse));
            }
        }
        System.out.println("Courses have been filtered by input name:");
        mapCourseNamesAndCourseLink.forEach((s, webElement) -> System.out.println(s));
        return this;
    }

    public MainCoursesComponents checkThatCourseExistOnPage() {
        for (String courseName : mapCourseNamesAndCourseLink.keySet()) {
            Assert.assertNotNull(waiter.waitForCourseName(courseName));
        }
        return this;
    }

    public MainCoursesComponents collectCoursesByDates() throws ParseException {
        for (WebElement hrefWebElement : blockOfCourses) {
            String courseDate = hrefWebElement.findElement(By.xpath(dateOfCourse)).getText();
            String urlCourse = hrefWebElement.getAttribute("href");
            String courseName = hrefWebElement.findElement(By.xpath(nameOfCourse)).getText();
            if (courseDate.startsWith("С")) {
                String result = courseDate.substring(2);
                mapDatesAndCourseLink.put(toDate(result), hrefWebElement);
                coursesList.add(new Courses(courseName, toDate(result), hrefWebElement, urlCourse));
            }
        }
        for (WebElement hrefWebElement : blockSpecializations) {
            String courseDate = hrefWebElement.findElement(By.xpath(dateOfSpecialization)).getText();
            String urlCourse = hrefWebElement.getAttribute("href");
            String courseName = hrefWebElement.findElement(By.xpath(nameOfCourse)).getText();
            if (courseDate.matches(".*\\d.*")) {
                String[] s = courseDate.split(" ");
                String result = s[0] + " " + s[1];
                mapDatesAndCourseLink.put(toDate(result), hrefWebElement);
                coursesList.add(new Courses(courseName, toDate(result), hrefWebElement, urlCourse));
            }
        }
        return this;
    }

    public Date toDate(String sDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM");
        return simpleDateFormat.parse(sDate);
        //System.out.println(simpleDateFormat.format(date));
    }

    public MainCoursesComponents orderCoursesByDates(Boolean byDates) {
        if (byDates) {
            /*mapDatesAndCourseLink.keySet().stream()
                    .sorted()
                    .reduce((date, date2) -> )
                    .*/
            coursesList.stream()
                    .map(courses -> courses.getDate())
                    .sorted()
                    .forEach(System.out::println);

        }
        return this;
    }

    public MainCoursesComponents getCoursesFromMainPage() {
        for (WebElement hrefWebElement : blockOfCoursesAndSpecializations) {
            String courseName = hrefWebElement.findElement(By.xpath(nameOfCourse)).getText();
            mapCourseNamesAndCourseLink.put(courseName, hrefWebElement);
        }
        System.out.println("Courses haven't been filtered by input name:");
        mapCourseNamesAndCourseLink.forEach((s, webElement) -> System.out.println(s));
        return this;
    }

    public MainCoursesComponents getEarlyCourse() {

        for (WebElement hrefWebElement : blockOfCourses) {
            String courseDate = hrefWebElement.findElement(By.xpath(dateOfCourse)).getText();
            String courseName = hrefWebElement.findElement(By.xpath(nameOfCourse)).getText();
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("C dd ");
            mapCourseNamesAndCourseLink.put(courseDate + " " + courseName, hrefWebElement);
        }
        /*for (WebElement hrefWebElement : blockSpecializations) {
            String courseName = hrefWebElement.findElement(By.cssSelector(dateOfSpecializations)).getText();
            mapElementsAndHref.put(courseName, hrefWebElement);
        }*/
        mapCourseNamesAndCourseLink.forEach((s, webElement) -> System.out.println(s));
        return this;
    }

    public MainCoursesComponents moveToEarlyComponent() {
        //WebElement element = waiter.waitForElementVisible(mapElementsAndHref.values().stream().findFirst().get());
        WebElement element = waiter.waitForElementVisible(mapCourseNamesAndCourseLink.values().stream().findFirst().get());
        actions.moveToElement(element.findElement(By.xpath(elementForHighlighting))).build().perform();
        mouseListeners.beforeClickOn(element.findElement(By.xpath(elementForHighlighting)), driver);
        mouseListeners.afterClickOn(element.findElement(By.xpath(elementForHighlighting)), driver);
        return this;
    }

}
