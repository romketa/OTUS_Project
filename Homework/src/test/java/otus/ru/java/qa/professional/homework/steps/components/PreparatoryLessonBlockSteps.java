package otus.ru.java.qa.professional.homework.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.����;
import io.cucumber.java.ru.�;
import io.cucumber.java.ru.�����;
import otus.ru.java.qa.professional.homework.components.PreparatoryLessonBlock;
import otus.ru.java.qa.professional.homework.steps.World;

public class PreparatoryLessonBlockSteps {

    private World world;

    @Inject
    private PreparatoryLessonBlockSteps(World world) {
        this.world = world;
    }

    @Inject
    private PreparatoryLessonBlock preparatoryLessonBlock;

    @����("����� ����� ������� ����")
    public void findHighCostLesson(){
        world.lesson = preparatoryLessonBlock.findHighCostLesson();
    }

    @�("����� ����� ������� ����")
    public void findLowCostLesson(){
        world.lesson = preparatoryLessonBlock.findLowCostLesson();
    }

    @�����("������� ���������� � ��� �� �������")
    public void printInConsole(){
        System.out.println(world.lesson.getName() + " - " + world.lesson.getPrice());
    }
}
