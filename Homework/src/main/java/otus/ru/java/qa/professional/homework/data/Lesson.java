package otus.ru.java.qa.professional.homework.data;

import io.cucumber.guice.ScenarioScoped;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Date;

public class Lesson {

    private String name;

    private String href;

    private LocalDate date;


    public Lesson(String name, String href, LocalDate date) {
        this.name = name;
        this.href = href;
        this.date = date;
    }

    public Lesson(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

}
