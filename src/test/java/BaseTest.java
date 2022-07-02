import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import otus.java.qa.professional.homework.driver.DriverFactory;
import otus.java.qa.professional.homework.listeners.MouseListeners;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected EventFiringWebDriver driver;

    @BeforeClass
    public void oneTimeSetUp(){
        driver = new DriverFactory().getDriver();
        driver.register(new MouseListeners());
    }

    @AfterClass
    public void TearDown(){
        driver.quit();
    }

}
