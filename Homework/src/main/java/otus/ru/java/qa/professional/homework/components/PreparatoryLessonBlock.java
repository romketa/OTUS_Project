package otus.ru.java.qa.professional.homework.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.ru.java.qa.professional.homework.annotations.Component;
import otus.ru.java.qa.professional.homework.data.Lesson;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import java.util.List;

@Component(".container-lessons")
public class PreparatoryLessonBlock extends BaseComponent<PreparatoryLessonBlock> {

    @Inject
    public PreparatoryLessonBlock(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @Inject
    private CustomAndDefaultWait wait;


    private final static String LESSON_LIST = ".lessons__new-item-container";

    private final static String LESSON_NAME = ".lessons__new-item-title";

    private final static String LESSON_PRICE = ".lessons__new-item-price";

    private int getAnInt(WebElement element, String s) {
        return Integer.parseInt(element.findElement(By.cssSelector(LESSON_PRICE)).getText().replaceFirst(s, ""));
    }

    public Lesson findLowCostLesson() {

        List<WebElement> initElements = initElements(LESSON_LIST);
        int min = initElements.stream()
                .map(element -> getAnInt(element, ".\\W$"))
                .min(Integer::compareTo)
                .get();

        var wElement = initElements.stream()
                .filter(element -> getAnInt(element, ".\\W$") == min)
                .findFirst()
                .get();

        String lessonName = getElementTextByCss(wElement, LESSON_NAME);
        int price = getAnInt(wElement, "..\\W$");
        return new Lesson(lessonName, price);
    }



    public Lesson findHighCostLesson() {
        List<WebElement> initElements = initElements(LESSON_LIST);
        int max = initElements.stream()
                .map(element -> getAnInt(element, ".\\W$"))
                .max(Integer::compareTo)
                .get();

        var wElement = initElements.stream()
                .filter(element -> getAnInt(element, ".\\W$") == max)
                .findFirst()
                .get();
        String lessonName = getElementTextByCss(wElement, LESSON_NAME);
        int price = getAnInt(wElement, ".\\W$");
        return new Lesson(lessonName, price);
    }
}
