package otus.java.qa.professional.homework.waiters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Waiter {

    private static int waitForElementTimeoutSeconds = 10;

    protected WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementVisible(WebElement element) {
        return new WebDriverWait(driver, waitForElementTimeoutSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    public void WaitForCourseName(String courseName) {
        Wait customWaiter = new FluentWait(driver)
                .withTimeout(Duration.of(waitForElementTimeoutSeconds, ChronoUnit.SECONDS))
                .withMessage(courseName);
        customWaiter.until(ExpectedConditions.titleIs(courseName));
    }

    public void WaitForElementIsVisible() {

    }

}
