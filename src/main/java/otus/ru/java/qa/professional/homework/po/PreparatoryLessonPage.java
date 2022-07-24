package otus.ru.java.qa.professional.homework.po;


import com.google.inject.Inject;
import otus.ru.java.qa.professional.homework.annotations.PagePath;
import otus.ru.java.qa.professional.homework.annotations.navigation.Url;
import otus.ru.java.qa.professional.homework.annotations.navigation.UrlTemplate;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import static org.assertj.core.api.Assertions.assertThat;

@Url({
        @UrlTemplate(name = "online", template = "{1}")
})
@PagePath("/")
public class PreparatoryLessonPage extends BasePage<PreparatoryLessonPage>{

    @Inject
    public PreparatoryLessonPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public String getTitle(){
        return guiceScoped.driver.getTitle();
    }

    public PreparatoryLessonPage pageTitleShouldBeSameAs(String pageLessonName) {
        assertThat(getTitle())
                .as("Page title should be {}", pageLessonName)
                .isEqualTo(pageLessonName);

        return this;
    }

}
