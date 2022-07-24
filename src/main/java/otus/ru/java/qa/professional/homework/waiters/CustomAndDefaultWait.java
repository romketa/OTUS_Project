package otus.ru.java.qa.professional.homework.waiters;

import com.google.inject.Inject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class CustomAndDefaultWait {


    @Inject
    protected GuiceScoped guiceScoped;

    @Inject
    public CustomAndDefaultWait(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
    }

    public WebElement waitForCourseName(WebElement webElement, WebDriver driver) {
        FluentWait<WebDriver> customWaiter = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.of(Long.parseLong(System.getProperty("wait.for.element.timeout.seconds")), ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(Long.parseLong(System.getProperty("polling.every.in.millis")), ChronoUnit.MILLIS))
                .ignoring(NoSuchElementException.class);

        return customWaiter.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
    }

    public WebElement waitForElementVisible(WebElement element, WebDriver driver) {
        return new WebDriverWait(driver, Long.parseLong(System.getProperty("wait.for.element.timeout.seconds"))).until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForElementsVisible(List<WebElement> elements, WebDriver driver){
        return new WebDriverWait(driver, Long.parseLong(System.getProperty("wait.for.element.timeout.seconds"))).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementInvisible(WebElement element, WebDriver driver){
        new WebDriverWait(driver, Long.parseLong(System.getProperty("wait.for.element.timeout.seconds"))).until(ExpectedConditions.invisibilityOf(element));
    }
}
