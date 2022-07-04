package otus.java.qa.professional.homework.waiters;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

public class Waiter {

    private static int waitForElementTimeoutSeconds = 10;
    private static int pollingEveryInMillis = 2;

    protected WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForCourseName(WebElement webElement) {
        Wait customWaiter = new FluentWait<>(driver)
                .withTimeout(Duration.of(waitForElementTimeoutSeconds, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(pollingEveryInMillis, ChronoUnit.MILLIS))
                .ignoring(NoSuchElementException.class);

        return (WebElement) customWaiter.until((Function<WebDriver, WebElement>) driver -> webElement);
    }

    public WebElement waitForElementVisible(WebElement element) {
        return new WebDriverWait(driver, waitForElementTimeoutSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    public Boolean waitForElementInvisible(WebElement element) {
        return new WebDriverWait(driver, waitForElementTimeoutSeconds).until(ExpectedConditions.invisibilityOf(element));
    }


    public WebElement waitForElementVisibleLocated(By locator) {
        return new WebDriverWait(driver, waitForElementTimeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
