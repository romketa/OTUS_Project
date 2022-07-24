package otus.ru.java.qa.professional.homework.support;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import otus.ru.java.qa.professional.homework.data.BrowserData;

@ScenarioScoped
public class GuiceScoped {

    public BrowserData browserName;
    public WebDriver driver;

}
