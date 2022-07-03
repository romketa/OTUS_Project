package otus.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class OperaWebDriver implements IWebDriver{

    @Override
    public WebDriver setUpDriver() {
        WebDriver driver = WebDriverManager.operadriver().create();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
