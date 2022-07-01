package otus.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import otus.java.qa.professional.homework.driver.DriverFactory;

public class ChromeDriverManager extends DriverFactory {

    private static WebDriver driver;

    public WebDriver initializeWebDriver() {
        if(driver == null){
            setDriver();
        }
        return driver;
    }



    public static void setDriver(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }
}