package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class OperaWebDriverImpl implements IWebDriver{

    @Override
    public WebDriver setUpDriver() {
        WebDriver driver = WebDriverManager.operadriver().create();
        DriverManager.manageDriver(driver);
        return driver;
    }
}
