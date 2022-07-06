package otus.ru.java.qa.professional.homework.driver.impl;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static void manageDriver(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void openSite(WebDriver driver){
        driver.get("https://otus.ru");
    }
}
