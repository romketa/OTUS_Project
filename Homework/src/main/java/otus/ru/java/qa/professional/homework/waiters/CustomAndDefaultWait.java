package otus.ru.java.qa.professional.homework.waiters;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.ru.java.qa.professional.homework.driver.impl.DriverManager;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.function.Function;

public class CustomAndDefaultWait {

    public static WebElement waitForCourseName(WebElement webElement, WebDriver driver) {
        Properties props = new Properties();
        try {
            props.load(DriverManager.class.getClassLoader().getResourceAsStream("project.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("File with properties cannot be found");;
        }
        Wait customWaiter = new FluentWait<>(driver)
                .withTimeout(Duration.of(Long.parseLong(System.getProperty("wait.for.element.timeout.seconds")), ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(Long.parseLong(System.getProperty("polling.every.in.millis")), ChronoUnit.MILLIS))
                .ignoring(NoSuchElementException.class);

        return (WebElement) customWaiter.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
    }

    public static WebElement waitForElementVisible(WebElement element, WebDriver driver) {
        return new WebDriverWait(driver, Long.parseLong(System.getProperty("wait.for.element.timeout.seconds"))).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementInvisible(WebElement element, WebDriver driver){
        new WebDriverWait(driver, Long.parseLong(System.getProperty("wait.for.element.timeout.seconds"))).until(ExpectedConditions.invisibilityOf(element));
    }
}
