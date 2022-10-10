package otus.ru.java.qa.professional.homework.steps.components;

import com.google.inject.Inject;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import otus.ru.java.qa.professional.homework.components.ContainerLessonsBlock;
import otus.ru.java.qa.professional.homework.steps.World;

public class ContainerLessonsBlockSteps {

    private final World world;

    @Inject
    private ContainerLessonsBlockSteps(World world) {
        this.world = world;
    }

    @Inject
    private ContainerLessonsBlock containerLessonsBlock;

    @Если("Найти курс {string} на главной странице")
    @Step("Ищем курс на главной странице")
    @Description("Шаг поиска курса на главной")
    public void findLessonOnMainPage(String courseName) {
        System.out.println("|-------|-------| Начинаем поиск указанного курса: ");
        world.lesson = containerLessonsBlock.findLessonInBlock(courseName);
        System.out.println("|-------|-------| Курс был найден: " + world.lesson.getName());
    }

    @Если("Найти курс по дате {string} или позже этой даты, если курса за указанную дату нет")
    @Step("Ищем курс по дате")
    @Description("Шаг инциирующий поиск по дате")
    public void findLessonByDate(String sDate){
        world.lesson = containerLessonsBlock.findLessonInBlockByLocalDate(sDate);
    }

    @Тогда("Вывести найденный курс на консоль")
    @Step("Выводим на консоль")
    @Description("Шаг инциирующий вывод на консоль")
    public void printOutLesson(){
        System.out.println("Курс - " + world.lesson.getName() + " Дата старта - " + world.lesson.getDate());
    }
}
