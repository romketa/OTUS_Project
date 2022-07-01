import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import otus.java.qa.professional.homework.driver.DriverFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void oneTimeSetUp(){
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.getDriver();
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }

}
