package otus.ru.java.qa.professional.homework.hooks;

import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import javax.inject.Inject;

public class Hooks {

    @Inject
    public GuiceScoped guiceScoped;

    public void tearDown() {
        if (guiceScoped.driver != null) {
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
