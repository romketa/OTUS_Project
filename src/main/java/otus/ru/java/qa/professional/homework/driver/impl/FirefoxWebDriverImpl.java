package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

public class FirefoxWebDriverImpl implements IWebDriver{


    @Override
    public WebDriver setUpDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--no-first-run");
        firefoxOptions.addArguments("--enable-extensions");
        firefoxOptions.addArguments("--homepage=about:blank");
        firefoxOptions.addArguments("--ignore-certificate-errors");
        firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        firefoxOptions.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        firefoxOptions.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("firefox.browser.version", "104"));
        firefoxOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));
        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.FIREFOX, System.getProperty("firefox.browser.version", "104"));
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }

            return new FirefoxDriver(firefoxOptions);
        } else
            return new RemoteWebDriver(getRemoteUrl(), firefoxOptions);
    }
}
