package otus.ru.java.qa.professional.homework.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseComponents {

    protected WebDriver driver;

    public BaseComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
