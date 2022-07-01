package otus.java.qa.professional.homework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import otus.java.qa.professional.homework.driver.impl.ChromeWebDriver;
import otus.java.qa.professional.homework.driver.impl.FirefoxWebDriver;
import otus.java.qa.professional.homework.driver.impl.OperaWebDriver;
import otus.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

import java.util.Locale;

public class DriverFactory implements IDriverFactory{

    private String browserType = System.getProperty("browser.name").toLowerCase(Locale.ROOT);

    @Override
    public WebDriver getDriver() {
        switch (this.browserType) {
            case "chrome": {
                return new ChromeWebDriver().setUpDriver();
            }
            case "firefox": {
                return new FirefoxWebDriver().setUpDriver();
            }
            case "opera": {
                return new OperaWebDriver().setUpDriver();
            }
            default:
                try {
                    throw new DriverTypeNotSupported(this.browserType);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }
}
