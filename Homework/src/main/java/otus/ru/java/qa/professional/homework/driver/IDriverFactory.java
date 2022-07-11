package otus.ru.java.qa.professional.homework.driver;

import org.openqa.selenium.WebDriver;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

public interface IDriverFactory {

    WebDriver getDriver() throws DriverTypeNotSupported;
}
