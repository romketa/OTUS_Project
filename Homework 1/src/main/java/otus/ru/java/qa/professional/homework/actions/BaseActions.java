package otus.ru.java.qa.professional.homework.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BaseActions {

    public static void moveToElementAction(WebElement element, WebDriver driver){
        new Actions(driver).moveToElement(element).build().perform();
    }

    public static void clickByElementAction(WebElement element, WebDriver driver){
        new Actions(driver).moveToElement(element).click().build().perform();
    }
}
