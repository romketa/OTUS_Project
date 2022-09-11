package otus.ru.java.qa.professional.homework.driver.impl;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public GuiceScoped guiceScoped;

    @Inject
    public DriverManager(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
    }

    public static void manageDriver(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Long.parseLong(System.getProperty("webdriver.timeouts.implicitlywait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
