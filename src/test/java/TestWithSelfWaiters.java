import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import otus.java.qa.professional.homework.components.MainCoursesComponents;
import otus.java.qa.professional.homework.driver.ChromeDriverManager;
import otus.java.qa.professional.homework.pages.MainPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestWithSelfWaiters extends BaseTest{

    @Test
    public void mainTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        MainCoursesComponents mainCoursesComponents = new MainCoursesComponents(driver);
        mainCoursesComponents.filterBy();
    }

}
