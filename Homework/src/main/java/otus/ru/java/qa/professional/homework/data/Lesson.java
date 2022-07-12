package otus.ru.java.qa.professional.homework.data;

import io.cucumber.guice.ScenarioScoped;

import java.util.Date;

public class Lesson {

    private String name;

    private String href;

    private Date date;


    public Lesson(String name, String href, Date date) {
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

    public Date getDate() {
        return date;
    }

}
