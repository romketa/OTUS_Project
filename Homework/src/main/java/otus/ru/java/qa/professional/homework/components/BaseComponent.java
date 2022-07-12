package otus.ru.java.qa.professional.homework.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import otus.ru.java.qa.professional.homework.annotations.Component;
import otus.ru.java.qa.professional.homework.exceptions.ComponentLocatorException;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import javax.inject.Inject;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class BaseComponent<TComponent> {

    protected GuiceScoped guiceScoped;

    public BaseComponent(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        PageFactory.initElements(guiceScoped.driver, this);
        try {
            validateComponent();
        } catch (ComponentLocatorException e) {
            throw new RuntimeException(e);
        }
    }

    public By getComponentLocator() throws ComponentLocatorException{
        Class<?> clazz = this.getClass(); //получаем класс у наследника
        if(clazz.isAnnotationPresent(Component.class)){ //проверяем, висит-ли на классе аннотация компонент
            Component component = clazz.getAnnotation(Component.class);//если она висит, тогда получаем объект из класса
            return this.locatorAnalyzer(component.value());//получаем значение из анализатора
        }
        throw new ComponentLocatorException();
    }

    public WebElement getComponentEntity() throws ComponentLocatorException {
        return guiceScoped.driver.findElement(getComponentLocator());
    }

    public List<WebElement> getComponentsEntity() throws ComponentLocatorException{
        return guiceScoped.driver.findElements(getComponentLocator());
    }

    public void validateComponent() throws ComponentLocatorException {
        assertThat(this.getComponentEntity().isDisplayed())
                .as("Component not displayed")
                .isTrue();
    }

    private By locatorAnalyzer(String locator){
        if (locator.startsWith("/")) {
            return By.xpath(locator);
        } else if (locator.startsWith("id:")) {
            return By.id(locator.replace("id:", ""));
        }

        return By.cssSelector(locator);
    }

}
