package otus.ru.java.qa.professional.homework.po;

import com.google.inject.Inject;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public class MainPage extends BasePage<MainPage>{

    @Inject
    MainPage mainPage;

    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }
}
