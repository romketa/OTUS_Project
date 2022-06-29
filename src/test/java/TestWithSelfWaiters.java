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
import otus.java.qa.professional.homework.driver.ChromeDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestWithSelfWaiters {

    public WebDriver driver;

    public final static String SITE = "https://otus.ru";

    @BeforeMethod(description = "Start browser")
    public void oneTimeSetUp(){
        ChromeDriverManager chromeDriverManager = new ChromeDriverManager();
        chromeDriverManager.initializeWebDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest(){
        driver.get(SITE);
    }

    @AfterMethod
    public void TearDown(){
        driver.close();
    }

}
