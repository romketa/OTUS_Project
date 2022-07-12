package otus.ru.java.qa.professional.homework.data;

import io.cucumber.guice.ScenarioScoped;

public class Lesson {

    private String name;

    private String href;


    public Lesson(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public Lesson(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        return name;
    }

}
