package otus.java.qa.professional.homework.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.format.DateTimeFormatter;
import java.util.*;

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


    @FindBy(css = ".lessons > a") //было .container-lessons .lessons > a
    public List<WebElement> cssBlockOfCoursesAndSpecializations;

    public String cssCourseName = ".lessons__new-item-title";

    private String nameOfCourse = ".//div[contains(@class, 'lessons__new-item-title')]";
    @FindBy(xpath = "//div[text()='Популярные курсы']/following-sibling::*[@class='lessons']/a")
    public List<WebElement> blockOfCourses;

    public String dateOfCourse = ".//*[@class='lessons__new-item-start']";

    @FindBy(css = ".container-padding-bottom .lessons > a ")
    public List<WebElement> blockSpecializations;

    public String dateOfSpecializations = ".lessons__new-item-time";

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
    public static Map<String, WebElement> mapElementsAndHref = new HashMap<>();

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

    public MainCoursesComponents filterByName(String name) {

        for (WebElement hrefWebElement : cssBlockOfCoursesAndSpecializations) {
            String courseName = hrefWebElement.findElement(By.cssSelector(cssCourseName)).getText();
            if (courseName.contains(name)) {
                mapElementsAndHref.put(courseName, hrefWebElement);
            }
        }

//        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        System.out.println("Courses have been filtered by input name:");
        mapElementsAndHref.forEach((s, webElement) -> System.out.println(s));
        return this;
    }


    public MainCoursesComponents getCoursesFromMainPage(){
        for (WebElement hrefWebElement : cssBlockOfCoursesAndSpecializations) {
            String courseName = hrefWebElement.findElement(By.cssSelector(cssCourseName)).getText();
            mapElementsAndHref.put(courseName, hrefWebElement);
        }
        System.out.println("Courses haven't been filtered by input name:");
        mapElementsAndHref.forEach((s, webElement) -> System.out.println(s));
        return this;
    }

    public MainCoursesComponents getEarlyCourse(){

        for (WebElement hrefWebElement : blockOfCourses) {
            String courseDate = hrefWebElement.findElement(By.xpath(dateOfCourse)).getText();
            String courseName = hrefWebElement.findElement(By.xpath(nameOfCourse)).getText();
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("C dd ");
            mapElementsAndHref.put(courseDate + " " + courseName, hrefWebElement);
        }
        /*for (WebElement hrefWebElement : blockSpecializations) {
            String courseName = hrefWebElement.findElement(By.cssSelector(dateOfSpecializations)).getText();
            mapElementsAndHref.put(courseName, hrefWebElement);
        }*/
        mapElementsAndHref.forEach((s, webElement) -> System.out.println(s));
        return this;
    }
}
