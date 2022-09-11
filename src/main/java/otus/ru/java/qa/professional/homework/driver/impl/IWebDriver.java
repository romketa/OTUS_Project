package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

import java.net.MalformedURLException;
import java.net.URL;

public interface IWebDriver {

    WebDriver driver = null;
    String REMOTE_URL = System.getProperty("webdriver.remote.url");
    WebDriver setUpDriver();

    default URL getRemoteUrl() {
        try {
            return new URL(REMOTE_URL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    default void downloadLocalWebDriver(DriverManagerType driverType, String browserVersion) throws DriverTypeNotSupported {
        Config wdmConfig = WebDriverManager.globalConfig();
        wdmConfig.setAvoidBrowserDetection(true);

        if (!browserVersion.isEmpty()) {
            switch (driverType) {
                case CHROME:
                    wdmConfig.setChromeDriverVersion(browserVersion);
                    break;
                case FIREFOX:
                    wdmConfig.setFirefoxVersion(browserVersion);
                    break;
                case OPERA:
                    wdmConfig.setOperaVersion(browserVersion);
                    break;
                default:
                    throw new DriverTypeNotSupported(driverType);
            }
        }
        WebDriverManager.getInstance(driverType).setup();
    }
}
