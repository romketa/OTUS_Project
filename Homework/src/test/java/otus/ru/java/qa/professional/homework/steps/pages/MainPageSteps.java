package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.�;
import otus.ru.java.qa.professional.homework.po.MainPage;

public class MainPageSteps {

    @Inject
    private MainPage mainPage;

    @�("�������� ������� �������� otus.ru")
    public void goToMainPage(){
        mainPage.open();
    }

}
