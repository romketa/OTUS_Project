import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import otus.ru.java.qa.professional.homework.driver.DriverFactoryImpl;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;

public class BaseTest {

    protected EventFiringWebDriver driver;

    @BeforeClass
    public void oneTimeSetUp(){
        driver = new DriverFactoryImpl().getDriver();
        driver.register(new SpecialEventListener());
    }


    @AfterClass
    public void TearDown(){
        driver.quit();
    }

}
