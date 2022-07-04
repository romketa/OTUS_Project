package otus.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxWebDriver implements IWebDriver{


    @Override
    public WebDriver setUpDriver() {
        WebDriver driver = WebDriverManager.firefoxdriver().create();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
