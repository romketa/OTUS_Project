package otus.java.qa.professional.homework.data;

import org.openqa.selenium.WebElement;

import java.util.Date;

public class Courses {
    private String name;
    private Date date;
    private WebElement element;

    private String linkToCourse;

    public Courses(String name, Date date, WebElement element, String linkToCourse) {
        this.name = name;
        this.date = date;
        this.element = element;
        this.linkToCourse = linkToCourse;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public WebElement getElement() {
        return element;
    }

    public String getLinkToCourse() {
        return linkToCourse;
    }
}
