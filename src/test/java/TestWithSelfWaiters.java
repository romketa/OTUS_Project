import org.testng.annotations.*;
import otus.java.qa.professional.homework.components.MainCoursesComponents;
import otus.java.qa.professional.homework.pages.MainPage;

public class TestWithSelfWaiters extends BaseTest{

    @Test(description = "Check filtering")
    public void mainTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        MainCoursesComponents mainCoursesComponents = new MainCoursesComponents(driver);
        mainCoursesComponents.getEarlyCourse();
        //mainCoursesComponents.filterByName("QA");

    }

}

