package otus.ru.java.qa.professional.homework.steps.components;

import com.google.inject.Inject;
import cucumber.api.Format;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import otus.ru.java.qa.professional.homework.components.ContainerLessonsBlock;
import otus.ru.java.qa.professional.homework.steps.World;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @Если("Найти курс по дате {string} или позже этой даты, если курса за указанную дату нет")
    public void findLessonByDate(String sDate){
        world.lesson = containerLessonsBlock.findLessonInBlockByLocalDate(sDate);
    }

    
    @Тогда("Вывести найденный курс на консоль")
    public void printOutLesson(){
        System.out.println("Курс - " + world.lesson.getName() + " Дата старта - " + world.lesson.getDate());
    }
}
