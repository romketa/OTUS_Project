package otus.ru.java.qa.professional.homework.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import otus.ru.java.qa.professional.homework.data.BrowserData;
import otus.ru.java.qa.professional.homework.driver.DriverFactoryImpl;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import java.util.Locale;

public class CommonPageSteps {

    @Inject
    private DriverFactoryImpl driverFactory;
    @Inject
    private GuiceScoped guiceScoped;

    @Пусть("Я открываю браузер {string}")
    public void initBrowser(String browserName) {
        BrowserData browserData = BrowserData.valueOf(browserName.toUpperCase(Locale.ROOT));
        guiceScoped.browserName = browserData;
        guiceScoped.driver = driverFactory.getDriver();
    }
}
