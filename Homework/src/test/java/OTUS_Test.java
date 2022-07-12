import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import otus.ru.java.qa.professional.homework.components.LessonCourseComponents;
import otus.ru.java.qa.professional.homework.components.MainCoursesComponents;
import otus.ru.java.qa.professional.homework.po.BasePage;

public class OTUS_Test extends BaseTest{

    @Test(description = "Check filtering by Name", priority = 1)
    public void checkFilteringCourseByName() {

        String filterByCourseName = System.getProperty("filterByCourseName");
        new MainCoursesComponents(driver)
                .filterByCourseName(filterByCourseName);
    }

    @Test(description = "Check selecting earlier Course", priority = 2)
    public void checkSelectingEarlierCourse() {

        MainCoursesComponents coursesComponents = new MainCoursesComponents(driver);
        WebElement selectedCourse = coursesComponents.getEarlyDateCourse();
        String courseName = coursesComponents.getCourseName(selectedCourse);
        coursesComponents
                .checkThatCourseExistOnPage(selectedCourse)
                .moveToElementAndHighlight(selectedCourse)
                .clickByElement(selectedCourse);
        coursesComponents.checkCourseName(courseName, new LessonCourseComponents(driver));
    }

    @Test(description = "Check selecting later Course", priority = 3)
    public void checkSelectingLaterCourse() {

        BasePage.openSite(driver);
        MainCoursesComponents coursesComponents = new MainCoursesComponents(driver);
        WebElement selectedCourse = coursesComponents.getOlderDateCourse();
        String courseName = coursesComponents.getCourseName(selectedCourse);
        coursesComponents
                .checkThatCourseExistOnPage(selectedCourse)
                .moveToElementAndHighlight(selectedCourse)
                .clickByElement(selectedCourse);
        coursesComponents.checkCourseName(courseName, new LessonCourseComponents(driver));;
    }

}

