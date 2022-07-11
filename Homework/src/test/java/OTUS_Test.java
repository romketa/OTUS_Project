import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import otus.ru.java.qa.professional.homework.components.LessonCourseComponents;
import otus.ru.java.qa.professional.homework.components.MainCoursesComponents;
import otus.ru.java.qa.professional.homework.po.BasePage;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public class OTUS_Test extends BaseTest{



    @DisplayName("Check filtering by Name")
    @Order(1)
    public void checkFilteringCourseByName() {

        String filterByCourseName = System.getProperty("filterByCourseName");
        new MainCoursesComponents(guiceScoped)
                .filterByCourseName(filterByCourseName);
    }

    @DisplayName("Check selecting earlier Course")
    @Order(2)
    public void checkSelectingEarlierCourse() {

        MainCoursesComponents coursesComponents = new MainCoursesComponents(guiceScoped);
        WebElement selectedCourse = coursesComponents.getEarlyOrLaterCourse(true);
        String courseName = coursesComponents.getCourseName(selectedCourse);
        coursesComponents
                .checkThatCourseExistOnPage(selectedCourse)
                .moveToElementAndHighlight(selectedCourse)
                .clickByElement(selectedCourse);
        coursesComponents.checkCourseName(courseName, new LessonCourseComponents(guiceScoped));
    }

    @DisplayName("Check selecting later Course")
    @Order(3)
    public void checkSelectingLaterCourse() {

        MainCoursesComponents coursesComponents = new MainCoursesComponents(guiceScoped);
        WebElement selectedCourse = coursesComponents.getEarlyOrLaterCourse(false);
        String courseName = coursesComponents.getCourseName(selectedCourse);
        coursesComponents
                .checkThatCourseExistOnPage(selectedCourse)
                .moveToElementAndHighlight(selectedCourse)
                .clickByElement(selectedCourse);
        coursesComponents.checkCourseName(courseName, new LessonCourseComponents(guiceScoped));;
    }

}

