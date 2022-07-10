package otus.ru.java.qa.professional.homework.po;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public static void openSite(WebDriver driver) {
        driver.get(System.getProperty("webdriver.base.url"));
    }

}
