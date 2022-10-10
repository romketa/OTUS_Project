package otus.ru.java.qa.professional.homework.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import otus.ru.java.qa.professional.homework.data.BrowserData;
import otus.ru.java.qa.professional.homework.driver.DriverFactoryImpl;
import otus.ru.java.qa.professional.homework.driver.impl.DriverManager;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import java.util.Locale;

public class CommonPageSteps {

    @Inject
    private DriverManager driverManager;

    @Inject
    private DriverFactoryImpl driverFactory;
    @Inject
    private GuiceScoped guiceScoped;

    @Пусть("Я открываю браузер {string}")
    @Step("Открываем браузер")
    @Description("Шаг инциирующий веб драйвер и открывает браузер")
    public void initBrowser(String browserName) {
        BrowserData browserData = BrowserData.valueOf(browserName.toUpperCase(Locale.ROOT));
        guiceScoped.browserName = browserData;
        guiceScoped.driver = driverFactory.getDriver();
        DriverManager.manageDriver(guiceScoped.driver);
    }
}
