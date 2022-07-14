import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.ru.java.qa.professional.homework.driver.DriverFactoryImpl;
import otus.ru.java.qa.professional.homework.listeners.SpecialEventListener;
import otus.ru.java.qa.professional.homework.po.BasePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class BaseTest {

    @Test
    public void test(){



        String b = "30 сент€бр€";
        Locale loc_rus = new Locale("ru", "RU");
        //DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("d[uu].MMMM.yyyy", loc_rus);
        //LocalDate lDate = LocalDate.parse(b, dtf1);
        //System.out.println(lDate);
        DateTimeFormatter dtf1 = new DateTimeFormatterBuilder()
                .appendPattern("d[uu] MMMM")
                .parseDefaulting(ChronoField.YEAR, 2022)
                .toFormatter(loc_rus);
        LocalDate lDate = LocalDate.parse(b, dtf1);
        System.out.println(lDate);

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM", new Locale("ru", "RU"));
    }


    /*
    protected EventFiringWebDriver driver;

    @BeforeAll
    public void oneTimeSetUp(){
        driver = new DriverFactoryImpl().getDriver();
        driver.register(new SpecialEventListener());
        BasePage.openSite(driver);
    }

    @AfterAll
    public void TearDown(){
        driver.quit();
    }
*/
}
