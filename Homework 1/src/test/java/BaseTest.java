import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import otus.java.qa.professional.homework.driver.DriverFactory;
import otus.java.qa.professional.homework.listeners.MouseListeners;
import otus.java.qa.professional.homework.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected EventFiringWebDriver driver;

    @BeforeClass
    public void oneTimeSetUp(){
        driver = new DriverFactory().getDriver();
        driver.register(new MouseListeners());
        new MainPage(driver).openSite();
    }


    @AfterClass
    public void TearDown(){
        driver.quit();
    }

}
