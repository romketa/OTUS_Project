package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ChromeWebDriverImpl implements IWebDriver{

    @Override
    public WebDriver setUpDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--enable-extensions");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("chrome.browser.version", "104"));
        chromeOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));
        if (getRemoteUrl() == null) {

            try {
                downloadLocalWebDriver(DriverManagerType.CHROME, System.getProperty("chrome.browser.version", "104"));
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }
            return new ChromeDriver(chromeOptions);
        } else
            return new RemoteWebDriver(getRemoteUrl(), chromeOptions);
    }
}
