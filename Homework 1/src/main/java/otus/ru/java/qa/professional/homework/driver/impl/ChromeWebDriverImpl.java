package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class ChromeWebDriverImpl implements IWebDriver{

    @Override
    public WebDriver setUpDriver() {
        WebDriver driver = WebDriverManager.chromedriver().create();
        DriverManager.manageDriver(driver);
        DriverManager.openSite(driver);
        return driver;
    }
}
