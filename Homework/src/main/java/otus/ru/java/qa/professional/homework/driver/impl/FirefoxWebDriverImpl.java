package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class FirefoxWebDriverImpl implements IWebDriver{


    @Override
    public WebDriver setUpDriver() {
        WebDriver driver = WebDriverManager.firefoxdriver().create();
        DriverManager.manageDriver(driver);
        return driver;
    }
}
