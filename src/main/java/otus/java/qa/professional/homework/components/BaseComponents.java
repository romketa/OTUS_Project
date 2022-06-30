package otus.java.qa.professional.homework.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseComponents {

    protected WebDriver driver;

    protected Actions actions;

    public BaseComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
}
