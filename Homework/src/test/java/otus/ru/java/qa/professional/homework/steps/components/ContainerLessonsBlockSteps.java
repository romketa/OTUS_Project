package otus.ru.java.qa.professional.homework.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import otus.ru.java.qa.professional.homework.components.ContainerLessonsBlock;
import otus.ru.java.qa.professional.homework.exceptions.ComponentLocatorException;
import otus.ru.java.qa.professional.homework.steps.World;

public class ContainerLessonsBlockSteps {

    private World world;

    @Inject
    private ContainerLessonsBlockSteps(World world) {
        this.world = world;
    }

    @Inject
    private ContainerLessonsBlock containerLessonsBlock;

    @Если("Найти курс {string} на главной странице")
    public void findLessonOnMainPage(String courseName) {
        System.out.println("|-------|-------| Начинаем поиск указанного курса: ");
        world.lesson = containerLessonsBlock.findLessonInBlock(courseName);
        System.out.println("|-------|-------| Курс был найден: " + world.lesson.getName());
    }
}
