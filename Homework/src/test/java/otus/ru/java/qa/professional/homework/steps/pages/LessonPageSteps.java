package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ht.Le;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import otus.ru.java.qa.professional.homework.components.ContainerLessonsBlock;
import otus.ru.java.qa.professional.homework.data.Lesson;
import otus.ru.java.qa.professional.homework.po.LessonPage;
import otus.ru.java.qa.professional.homework.steps.World;
import otus.ru.java.qa.professional.homework.steps.common.CommonPageSteps;

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
