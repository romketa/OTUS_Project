package otus.ru.java.qa.professional.homework.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.ru.java.qa.professional.homework.waiters.CustomAndDefaultWait;

public class LessonCourseComponents extends BaseComponents {

    @FindBy(css = ".course-header2__title")
    private WebElement courseName;

    public LessonCourseComponents(WebDriver driver) {
        super(driver);
    }

    public String getCourseName() {
        CustomAndDefaultWait.waitForElementVisible(courseName, driver);
        return courseName.getText();
    }

    public String getSpecializationName(){
        return driver.getTitle();
    }

}
