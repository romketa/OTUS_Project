import org.testng.Assert;
import org.testng.annotations.*;
import otus.java.qa.professional.homework.components.MainCoursesComponents;

import java.text.ParseException;

public class OTUS_Test extends BaseTest{

    @Test(description = "Check filtering by Name", priority = 1)
    public void checkFilteringCourseByName() throws ParseException {

        String filterByCourseName = System.getProperty("filterByCourseName");
        new MainCoursesComponents(driver)
                .collectCourses()
                .filterByCourseName(filterByCourseName);
    }

    @Test(description = "Check selecting earlier Course", priority = 2)
    public void checkSelectingEarlierCourse() throws ParseException {

        MainCoursesComponents mainCoursesComponents = new MainCoursesComponents(driver);
        mainCoursesComponents
                .collectCourses()
                .getEarlyOrLaterCourse(false)
                .checkThatCourseExistOnPage()
                .moveToSelectedCourse()
                .getCoursePage();
        mainCoursesComponents.checkCourseName();
    }

    @Test(description = "Check selecting later Course", priority = 3)
    public void checkSelectingLaterCourse() throws ParseException {

        new MainCoursesComponents(driver)
                .collectCourses()
                .getEarlyOrLaterCourse(true)
                .checkThatCourseExistOnPage()
                .moveToSelectedCourse();
    }

}

