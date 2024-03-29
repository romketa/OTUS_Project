package otus.ru.java.qa.professional.homework.driver.impl;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static void manageDriver(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Long.parseLong(System.getProperty("wait.for.element.timeout.seconds")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
