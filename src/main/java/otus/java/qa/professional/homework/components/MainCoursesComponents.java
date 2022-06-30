package otus.java.qa.professional.homework.components;


import com.mifmif.common.regex.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class MainCoursesComponents extends BaseComponents {

    /*private String nameOfCourse = "//div[contains(@class, 'lessons__new-item-title')]";

    private String famousCourses = "//div[@class='text-center']/preceding-sibling::*[@class='lessons']/a";
*/

    private String nameOfCourse = ".//div[contains(@class, 'lessons__new-item-title')]";

    private String dateOfCourse = ".//span[@class='lessons__new-item-calendar']";

    private String dateOfSpecialization = ".//div[@class='lessons__new-item-time']";

    @FindBy(xpath = "//div[@class='text-center']/preceding-sibling::*[@class='lessons']/a")
    private List<WebElement> blockFamousCourses;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    private List<WebElement> blockSpecializations;

    @FindBy(xpath = "//div[@class='text-center']/following-sibling::*[@class='lessons']/a")
    private List<WebElement> blockRecommendationCourses;


    @FindBy(css = ".container-lessons .lessons > a")
    private List<WebElement> allCourses;

    @FindBy(css = ".lessons__new-item-title")
    private WebElement cssCourseName;
    /*
    @FindBy(css = ".container-lessons .lessons > a .lessons__new-item-time")
    private List<WebElement> getListOfStartDates;

    @FindBy(css = ".container-lessons .lessons > a .lessons__new-item-start")
    private List<WebElement> listOfStartDatesOne;

    @FindBy(css = ".container-lessons .lessons > a .lessons__new-item-courses ~ .lessons__new-item-time")
    private List<WebElement> listOfStartDatesSecond;
*/
    public static Map<String, WebElement> mapElementAndHref = new HashMap<>();

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

        for (WebElement hrefWebElement : allCourses) {
            String courseName = hrefWebElement.findElement(By.cssSelector(".lessons__new-item-title")).getText();
            if (courseName.contains(name)) {
                mapElementAndHref.put(courseName, hrefWebElement);
            }
        }

//        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        System.out.println("Courses have been filtered by input name:");
        mapElementAndHref.forEach((s, webElement) -> System.out.println(s));
        return this;
    }


    public MainCoursesComponents getCoursesFromMainPage(){
        for (WebElement hrefWebElement : allCourses) {
            String courseName = hrefWebElement.findElement(By.cssSelector(".lessons__new-item-title")).getText();
            mapElementAndHref.put(courseName, hrefWebElement);
        }
        System.out.println("Courses haven't been filtered by input name:");
        mapElementAndHref.forEach((s, webElement) -> System.out.println(s));
        return this;
    }


/*
        Stream<String> listOfFamousCourseName = getListOfTextFromBlock(allCourses, nameOfCourse).stream();
        Stream<String> listOfSpecializationsCourseName = getListOfTextFromBlock(blockSpecializations, nameOfCourse).stream();
        Stream<String> listOfRecommendationCourseName = getListOfTextFromBlock(blockRecommendationCourses, nameOfCourse).stream();
*/

/*
        switch (mainCourses) {
            case FAMOUS_COURSES -> {
                listOfFamousCourseName
                        .filter(s -> s.equals(name))
                        .collect(Collectors.toList());

            }
            case SPECIALIZATIONS -> {
                Stream<String> listOfSpecializationsCourseName = getListOfTextFromBlock(blockSpecializations, nameOfCourse).stream();

            }
            case RECOMMENDATIONS -> {
                Stream<String> listOfRecommendationCourseName = getListOfTextFromBlock(blockRecommendationCourses, nameOfCourse).stream();

            }
        }*/


}
