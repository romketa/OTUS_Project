package otus.java.qa.professional.homework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements IDriver {

    private static WebDriver driver;

    @Override
    public WebDriver initializeWebDriver() {
        if(driver == null){
            setDriver();
        }
        return driver;
    }



    public static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }
}