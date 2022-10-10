package otus.ru.java.qa.professional.homework.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import otus.ru.java.qa.professional.homework.components.PreparatoryLessonBlock;
import otus.ru.java.qa.professional.homework.steps.World;

public class PreparatoryLessonBlockSteps {

    private final World world;

    @Inject
    private PreparatoryLessonBlockSteps(World world) {
        this.world = world;
    }

    @Inject
    private PreparatoryLessonBlock preparatoryLessonBlock;

    @Если("Найти самый дорогой курс")
    @Step("Ищем самый дорогой курс")
    @Description("Шаг инциирующий поиск самого дорогого курса")
    public void findHighCostLesson(){
        world.lesson = preparatoryLessonBlock.findHighCostLesson();
    }

    @И("Найти самый дешёвый курс")
    @Step("Ищем самый дешёвый курс")
    @Description("Шаг инциирующий поиск самого дешёвого курса")
    public void findLowCostLesson(){
        world.lesson = preparatoryLessonBlock.findLowCostLesson();
    }

    @Тогда("Вывести информацию о них на консоль")
    @Step("Выводим информацию на консоль")
    @Description("Шаг инциирующий вывод на консоль")
    public void printInConsole(){
        System.out.println(world.lesson.getName() + " - " + world.lesson.getPrice());
    }
}
