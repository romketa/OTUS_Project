package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import otus.ru.java.qa.professional.homework.po.PreparatoryLessonPage;

public class PreparatoryLessonPageSteps {

    @Inject
    private PreparatoryLessonPage preparatoryLessonPage;

    @Если("Открыть страницу курсов {string}")
    @Step("Переходим на страницу курса")
    @Description("Шаг перехода на страницу курса")
    public void openPreparatoryLessonPage(String preparatoryLesson) throws Exception {
        preparatoryLessonPage.open("online", preparatoryLesson);
    }

    @Тогда("Страница курсов {string} открыта")
    @Step("Првоеряем что страница урока открыта")
    @Description("Шаг проверки открытия страницы урока")
    public void lessonPageShouldBeOpened(String lessonName) {
        preparatoryLessonPage.pageTitleShouldBeSameAs(lessonName);
    }
}
