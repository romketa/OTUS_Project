package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.����;
import io.cucumber.java.ru.�����;
import otus.ru.java.qa.professional.homework.po.PreparatoryLessonPage;

public class PreparatoryLessonPageSteps {

    @Inject
    private PreparatoryLessonPage preparatoryLessonPage;

    @����("������� �������� ������ {string}")
    public void openPreparatoryLessonPage(String preparatoryLesson) throws Exception {
        preparatoryLessonPage.open("online", preparatoryLesson);
    }

    @�����("�������� ������ {string} �������")
    public void lessonPageShouldBeOpened(String lessonName) {
        preparatoryLessonPage.pageTitleShouldBeSameAs(lessonName);
    }
}
