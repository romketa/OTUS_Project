package otus.ru.java.qa.professional.homework.actions;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public class BaseActions {

    @Inject
    protected GuiceScoped guiceScoped;

    @Inject
    public BaseActions(GuiceScoped guiceScoped){
        this.guiceScoped = guiceScoped;
    }

    public void moveToElementAction(WebElement element, WebDriver driver){
        new Actions(driver).moveToElement(element).build().perform();
    }

    public void clickByElementAction(WebElement element, WebDriver driver){
        new Actions(driver).moveToElement(element).click().build().perform();
    }
}
