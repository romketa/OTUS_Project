package otus.ru.java.qa.professional.homework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public interface IDriverFactory {

    WebDriver getDriver();
}
