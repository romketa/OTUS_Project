package otus.ru.java.qa.professional.homework.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

public class CustomAndDefaultWait {

    public static WebElement waitForCourseName(WebElement webElement, WebDriver driver) {
        Wait customWaiter = new FluentWait<>(driver)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(200, ChronoUnit.MILLIS))
                .ignoring(NoSuchElementException.class);

        return (WebElement) customWaiter.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
    }

    public static WebElement waitForElementVisible(WebElement element, WebDriver driver) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }


}
