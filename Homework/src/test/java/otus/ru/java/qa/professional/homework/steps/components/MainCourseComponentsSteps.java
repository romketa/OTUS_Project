package otus.ru.java.qa.professional.homework.steps.components;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.sl.In;
import io.cucumber.core.feature.*;
import otus.ru.java.qa.professional.homework.components.MainCoursesComponents;

import javax.inject.Inject;

public class MainCourseComponentsSteps {

    @Inject
    public MainCoursesComponents mainCoursesComponents;

    @Если("Найти урок {string} на главной странице")
    public void findLessonOnMainPage(String courseName){
        mainCoursesComponents.filterByCourseName(courseName);
    }
}
