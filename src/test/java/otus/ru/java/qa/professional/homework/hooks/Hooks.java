package otus.ru.java.qa.professional.homework.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public class Hooks {

    @Inject
    public GuiceScoped guiceScoped;


    @After
    public void tearDown() {
        if (guiceScoped.driver != null) {
            guiceScoped.driver.close();
//            guiceScoped.driver.quit();
        }
    }
}
