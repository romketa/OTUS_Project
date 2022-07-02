package otus.java.qa.professional.homework.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import otus.java.qa.professional.homework.listeners.MouseListeners;
import otus.java.qa.professional.homework.waiters.Waiter;

public abstract class BaseComponents {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;


    protected MouseListeners mouseListeners;

    public BaseComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        waiter = new Waiter(driver);
        mouseListeners = new MouseListeners();
    }
}
