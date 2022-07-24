package otus.ru.java.qa.professional.homework.po;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.ru.java.qa.professional.homework.annotations.PagePath;
import otus.ru.java.qa.professional.homework.annotations.navigation.Url;
import otus.ru.java.qa.professional.homework.annotations.navigation.UrlTemplate;
import otus.ru.java.qa.professional.homework.data.LessonType;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import static org.assertj.core.api.Assertions.assertThat;

@Url({
        @UrlTemplate(name = "lesson", template = "{1}")
})
@PagePath("/lessons")
public class LessonPage extends BasePage<LessonPage> {

    @Inject
    public LessonPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @Inject
    private CustomAndDefaultWait wait;

    @FindBy(css = ".course-header2__title")
    private WebElement courseName;

    public String getCourseName() {
        wait.waitForElementVisible(courseName, guiceScoped.driver);
        return courseName.getText();
    }

    public String getSpecializationName(){
        return guiceScoped.driver.getTitle();
    }

    public void lessonPageHeaderShouldBeAs(String header){
        if (header.startsWith(LessonType.SPECIALIZATIONS.getName())) {
            assertThat(getSpecializationName())
                    .as("|-------|-------| Tittle {} on a page Specialization isn't correct", header)
                    .isEqualTo(header);
        } else {
            assertThat(getCourseName())
                    .as("|-------|-------| Tittle {} on a page Specialization isn't correct", header)
                    .isEqualTo(header);
        }
        System.out.println("|-------|-------| Course name is correctly displayed");
    }

    @NotNull
    private LessonType getLessonType(String lesson) {
        LessonType lessonType = null;
        if(lesson.startsWith(LessonType.SPECIALIZATIONS.getName())){
            lessonType = LessonType.SPECIALIZATIONS;
        } else lessonType = LessonType.COURSES;
        return lessonType;
    }

}