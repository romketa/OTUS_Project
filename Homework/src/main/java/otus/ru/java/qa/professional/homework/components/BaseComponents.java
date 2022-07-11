package otus.ru.java.qa.professional.homework.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import javax.inject.Inject;

public abstract class BaseComponents {

    protected GuiceScoped guiceScoped;

    public BaseComponents(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        PageFactory.initElements(guiceScoped.driver, this);
    }
}
