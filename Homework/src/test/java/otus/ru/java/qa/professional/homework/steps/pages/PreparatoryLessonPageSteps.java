package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import otus.ru.java.qa.professional.homework.po.PreparatoryLessonPage;

public class PreparatoryLessonPageSteps {

    @Inject
    private PreparatoryLessonPage preparatoryLessonPage;

    @Если("Открыть страницу курсов {string}")
    public void openPreparatoryLessonPage(String preparatoryLesson) throws Exception {
        preparatoryLessonPage.open("online", preparatoryLesson);
    }

    @Тогда("Страница курсов {string} открыта")
    public void lessonPageShouldBeOpened(String lessonName) {
        preparatoryLessonPage.pageTitleShouldBeSameAs(lessonName);
    }
}
