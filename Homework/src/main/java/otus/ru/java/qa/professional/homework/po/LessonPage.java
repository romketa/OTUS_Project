package otus.ru.java.qa.professional.homework.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.ru.java.qa.professional.homework.annotations.Component;
import otus.ru.java.qa.professional.homework.annotations.PagePath;
import otus.ru.java.qa.professional.homework.annotations.navigation.Url;
import otus.ru.java.qa.professional.homework.annotations.navigation.UrlTemplate;
import otus.ru.java.qa.professional.homework.components.LessonCourseComponents;
import otus.ru.java.qa.professional.homework.data.LessonType;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Url({
        @UrlTemplate(name = "lesson", template = "{1}")
})
@PagePath("/lessons")
public class LessonPage extends BasePage<LessonPage> {

    @Inject
    LessonCourseComponents lessonCourseComponents;

    @Inject
    public LessonPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public LessonPage lessonPageHeaderShouldBeAs(String header){
        if (header.startsWith(LessonType.SPECIALIZATIONS.getName())) {
            assertThat(lessonCourseComponents.getSpecializationName())
                    .as("|-------|-------| Tittle {} on a page Specialization isn't correct", header)
                    .isEqualTo(header);
        } else {
            assertThat(lessonCourseComponents.getCourseName())
                    .as("|-------|-------| Tittle {} on a page Specialization isn't correct", header)
                    .isEqualTo(header);
        }
        System.out.println("|-------|-------| Course name is correctly displayed");
        return this;
    }


}