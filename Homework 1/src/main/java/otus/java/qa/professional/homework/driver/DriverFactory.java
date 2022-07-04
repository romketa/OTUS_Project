package otus.java.qa.professional.homework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.java.qa.professional.homework.driver.impl.ChromeWebDriver;
import otus.java.qa.professional.homework.driver.impl.FirefoxWebDriver;
import otus.java.qa.professional.homework.driver.impl.OperaWebDriver;
import otus.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

import java.util.Locale;

public class DriverFactory implements IDriverFactory{

    private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

    @Override
    public EventFiringWebDriver getDriver() {
        switch (this.browserType) {
            case "chrome": {
                return new EventFiringWebDriver(new ChromeWebDriver().setUpDriver());
            }
            case "firefox": {
                return new EventFiringWebDriver(new FirefoxWebDriver().setUpDriver());
            }
            case "opera": {
                return new EventFiringWebDriver(new OperaWebDriver().setUpDriver());
            }
            default:
                try {
                    throw new DriverTypeNotSupported(this.browserType);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }
}
