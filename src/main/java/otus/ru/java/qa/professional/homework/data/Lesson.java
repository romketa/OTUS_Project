package otus.ru.java.qa.professional.homework.data;


import java.time.LocalDate;


public class Lesson {

    private final String name;

    private String href;

    private LocalDate date;

    private int price;


    public Lesson(String name, String href, LocalDate date) {
        this.name = name;
        this.href = href;
        this.date = date;
    }

    public Lesson(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public Lesson(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
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
