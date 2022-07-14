package otus.ru.java.qa.professional.homework.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ht.Le;
import io.cucumber.java.ru.�;
import io.cucumber.java.ru.�����;
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

    @�("������� �� �������� �����")
    public void goToLessonPage() {
        lessonPage.open(world.lesson.getHref());
    }

    @�����("�� �������� ����� ������������ ��������� �����")
    public void lessonPageIsOpened(){
        lessonPage.lessonPageHeaderShouldBeAs(world.lesson.getName());
    }
}
