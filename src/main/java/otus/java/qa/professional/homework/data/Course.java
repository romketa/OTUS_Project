package otus.java.qa.professional.homework.data;

import org.openqa.selenium.WebElement;

import java.util.Date;

public class Course {
    private String name;
    private Date date;
    private WebElement element;
    private String linkToCourse;

    public WebElement elementForBorderHighlight;

    public Course(String name, Date date, WebElement element, String linkToCourse, WebElement elementForBorderHighlight) {
        this.name = name;
        this.date = date;
        this.element = element;
        this.linkToCourse = linkToCourse;
        this.elementForBorderHighlight = elementForBorderHighlight;
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

    public WebElement getElementForBorderHighlight() {
        return elementForBorderHighlight;
    }
}
