package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxWebDriverImpl implements IWebDriver{


    @Override
    public WebDriver setUpDriver() {
        WebDriver driver = WebDriverManager.firefoxdriver().create();
        DriverManager.manageDriver(driver);
        DriverManager.openSite(driver);
        return driver;
    }
}
