package otus.ru.java.qa.professional.homework.steps.pages;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import otus.ru.java.qa.professional.homework.po.LessonPage;

import javax.inject.Inject;

public class LessonPageSteps {

    @Inject
    public LessonPage lessonPage;

    @И("Перейти на страницу урока {string}")
    public void goToLessonPage(String lesson) throws Exception {
        lessonPage.open("lesson", lesson);
    }

    @Тогда("На странице урока отображается {string}")
    public void lessonPageHeaderShouldBeAs(String header){
        lessonPage.lessonPageHeaderShouldBeAs(header);
    }

}
