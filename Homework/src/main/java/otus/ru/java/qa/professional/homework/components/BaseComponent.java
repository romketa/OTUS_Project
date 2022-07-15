package otus.ru.java.qa.professional.homework.components;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import otus.ru.java.qa.professional.homework.annotations.Component;
import otus.ru.java.qa.professional.homework.exceptions.ComponentLocatorException;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import javax.inject.Inject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class BaseComponent<TComponent> {

    protected GuiceScoped guiceScoped;

    @Inject
    public CustomAndDefaultWait wait;

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

    @Nullable
    protected List<WebElement> initElements(String sElement) {
        List<WebElement> initElements = null;
        try {
            initElements = wait.waitForElementsVisible(this.getComponentEntity().findElements(By.cssSelector(sElement)), guiceScoped.driver);
        } catch (ComponentLocatorException e) {
            System.out.println(e.getMessage());
        }
        return initElements;
    }

    protected String getElementTextByCss(WebElement element, String lessonDate) {
        return element.findElement(By.cssSelector(lessonDate)).getText();
    }

    protected LocalDate getDateToLocalDate(WebElement element, String LessonDateCourse, String LessonDateSpecialization) {
        if (element.findElements(By.cssSelector(LessonDateCourse)).size() == 0) {
            String[] date = getElementTextByCss(element, LessonDateSpecialization).split(" ");
            return fromStringToLocalDate(date[0] + " " + date[1]);
        } else {
            return fromStringToLocalDate(getElementTextByCss(element, LessonDateCourse).substring(2));
        }
    }


    private LocalDate fromStringToLocalDate(String sDate) {
        Locale loc_rus = new Locale("ru", "RU");
        DateTimeFormatter dtf1 = new DateTimeFormatterBuilder()
                .appendPattern("d[uu] MMMM")
                .parseDefaulting(ChronoField.YEAR, 2022)
                .toFormatter(loc_rus);
        return LocalDate.parse(sDate, dtf1);
    }


}
