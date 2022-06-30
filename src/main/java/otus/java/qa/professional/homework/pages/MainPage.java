package otus.java.qa.professional.homework.pages;

import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    private static final String SITE = "https://otus.ru";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSite(){
        driver.get(SITE);
    }

}
