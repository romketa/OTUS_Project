import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.ru.java.qa.professional.homework.driver.DriverFactoryImpl;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.po.BasePage;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public class BaseTest {

    protected GuiceScoped guiceScoped;


    /*
    protected EventFiringWebDriver driver;

    @BeforeAll
    public void oneTimeSetUp(){
        driver = new DriverFactoryImpl().getDriver();
        driver.register(new SpecialEventListener());
        BasePage.openSite(driver);
    }

    @AfterAll
    public void TearDown(){
        driver.quit();
    }
*/
}
