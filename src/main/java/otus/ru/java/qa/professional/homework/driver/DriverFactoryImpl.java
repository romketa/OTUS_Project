package otus.ru.java.qa.professional.homework.driver;

import com.google.inject.Inject;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.ru.java.qa.professional.homework.driver.impl.ChromeWebDriverImpl;
import otus.ru.java.qa.professional.homework.driver.impl.FirefoxWebDriverImpl;
import otus.ru.java.qa.professional.homework.driver.impl.OperaWebDriverImpl;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public class DriverFactoryImpl implements IDriverFactory {

    public GuiceScoped guiceScoped;

    @Inject
    public DriverFactoryImpl(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
    }

    @Override
    public EventFiringWebDriver getDriver() {
        switch (guiceScoped.browserName) {
            case CHROME: {
                return new EventFiringWebDriver(new ChromeWebDriverImpl().setUpDriver());
            }
            case FIREFOX : {
                return new EventFiringWebDriver(new FirefoxWebDriverImpl().setUpDriver());
            }
            case OPERA : {
                return new EventFiringWebDriver(new OperaWebDriverImpl().setUpDriver());
            }
            default : throw new DriverTypeNotSupported(guiceScoped.browserName.getName());
        }
    }
}
