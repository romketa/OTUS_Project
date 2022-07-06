import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import otus.ru.java.qa.professional.homework.components.LessonCourseComponents;
import otus.ru.java.qa.professional.homework.components.MainCoursesComponents;

public class OTUS_Test extends BaseTest{

    @Test(description = "Check filtering by Name", priority = 1)
    public void checkFilteringCourseByName() {

        String filterByCourseName = System.getProperty("filterByCourseName");
        new MainCoursesComponents(driver)
                .filterByCourseName(filterByCourseName);
    }

    @Test(description = "Check selecting earlier Course", priority = 2)
    public void checkSelectingEarlierCourse() {

        MainCoursesComponents mainCoursesComponents = new MainCoursesComponents(driver);
        WebElement selectedCourse = mainCoursesComponents.getEarlyOrLaterCourse(true);
        mainCoursesComponents
                .checkThatCourseExistOnPage(selectedCourse)
                .moveToElementAndHighlight(selectedCourse);
    }

    @Test(description = "Check selecting later Course", priority = 3)
    public void checkSelectingLaterCourse() {


        MainCoursesComponents mainCoursesComponents = new MainCoursesComponents(driver);
        WebElement selectedCourse = mainCoursesComponents.getEarlyOrLaterCourse(false);
        mainCoursesComponents
                .checkThatCourseExistOnPage(selectedCourse)
                .moveToElementAndHighlight(selectedCourse);
    }

}

