package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import otus.ru.java.qa.professional.homework.po.LessonPage;
import otus.ru.java.qa.professional.homework.steps.World;

public class LessonPageSteps {

    @Inject
    private LessonPage lessonPage;

    @Inject
    private World world;

    @И("Перейти на страницу урока")
    public void goToLessonPage() {
        lessonPage.open(world.lesson.getHref());
    }

    @Тогда("На странице урока отображается заголовок урока")
    public void lessonPageIsOpened(){
        lessonPage.lessonPageHeaderShouldBeAs(world.lesson.getName());
    }
}