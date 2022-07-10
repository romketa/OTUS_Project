package otus.ru.java.qa.professional.homework.driver;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.ru.java.qa.professional.homework.driver.impl.ChromeWebDriverImpl;
import otus.ru.java.qa.professional.homework.driver.impl.FirefoxWebDriverImpl;
import otus.ru.java.qa.professional.homework.driver.impl.OperaWebDriverImpl;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

import java.util.Locale;

public class DriverFactoryImpl implements IDriverFactory {

    @Override
    public EventFiringWebDriver getDriver() {
        String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);
        switch (browserType) {
            case "chrome": {
                return new EventFiringWebDriver(new ChromeWebDriverImpl().setUpDriver());
            }
            case "firefox": {
                return new EventFiringWebDriver(new FirefoxWebDriverImpl().setUpDriver());
            }
            case "opera": {
                return new EventFiringWebDriver(new OperaWebDriverImpl().setUpDriver());
            }
            default: throw new DriverTypeNotSupported(browserType);
        }
    }
}
