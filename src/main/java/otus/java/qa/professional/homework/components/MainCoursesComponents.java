package otus.java.qa.professional.homework.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import otus.java.qa.professional.homework.data.MainCourses;
import otus.java.qa.professional.homework.listeners.MouseListeners;

import java.util.List;
import java.util.stream.Stream;

public class MainCoursesComponents extends BaseComponents {

    //@FindBy(xpath = "//div[text()='Популярные курсы']")
    //private WebElement famous;

    private String nameOfCourse = "//div[contains(@class, 'lessons__new-item-title')]";

    private String famousCourses = "//div[@class='text-center']/preceding-sibling::*[@class='lessons']/a";

    //@FindBy(xpath = "//div[text()='Рекомендации для вас']")
    //private WebElement recommendation;


    @FindBy(xpath = "//div[@class='text-center']/following-sibling::*[@class='lessons']/a")
    private List<WebElement> listOfRecommendationCourses;



    //@FindBy(xpath = "//div[text()='Специализации']")
    //private WebElement specializations;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']/a")
    private WebElement listOfSpecializations;

    public MainCoursesComponents(WebDriver driver) {
        super(driver);
    }

    public MainCoursesComponents filterBy(){

        List<WebElement> courses = driver.findElements(By.xpath(famousCourses));

        for (WebElement c:courses) {
            System.out.println(c);
        }



        for (WebElement webElement:listOfRecommendationCourses) {
            String nameOfElement = webElement.toString() + nameOfCourse.toString();

            System.out.println(nameOfElement);
        }
/*
        switch (mainCourses) {
            case FAMOUS_COURSES -> {

            }
            case SPECIALIZATIONS -> {

            }
            case RECOMMENDATIONS -> {

            }
        }
*/

        return this;
    }

}
