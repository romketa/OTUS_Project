package otus.ru.java.qa.professional.homework.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

import javax.inject.Inject;

public class LessonCourseComponents extends BaseComponents {

    @FindBy(css = ".course-header2__title")
    private WebElement courseName;


    @Inject
    public LessonCourseComponents(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public String getCourseName() {
        CustomAndDefaultWait.waitForElementVisible(courseName, guiceScoped.driver);
        return courseName.getText();
    }

    public String getSpecializationName(){
        return guiceScoped.driver.getTitle();
    }

}
