package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import otus.ru.java.qa.professional.homework.po.MainPage;

public class MainPageSteps {

    @Inject
    private MainPage mainPage;

    @И("Открываю главную страницу otus.ru")
    @Step("Открываем главную страницу")
    @Description("Шаг открытия главной")
    public void goToMainPage(){
        mainPage.open();
    }

}
