package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import otus.ru.java.qa.professional.homework.po.LessonPage;
import otus.ru.java.qa.professional.homework.steps.World;

public class LessonPageSteps {

    @Inject
    private LessonPage lessonPage;

    @Inject
    private World world;

    @И("Перейти на страницу урока")
    @Step("Переходим на страницу урока")
    @Description("Шаг перехода на страницу урока")
    public void goToLessonPage() {
        lessonPage.open(world.lesson.getHref());
    }

    @Тогда("На странице урока отображается заголовок урока")
    @Step("Проверяем отображение заголовка урока")
    @Description("Шаг перехода на страницу урока и проверкой заголовка")
    public void lessonPageIsOpened(){
        lessonPage.lessonPageHeaderShouldBeAs(world.lesson.getName());
    }
}
