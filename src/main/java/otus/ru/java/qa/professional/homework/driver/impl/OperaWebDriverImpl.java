package otus.ru.java.qa.professional.homework.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import otus.ru.java.qa.professional.homework.exceptions.DriverTypeNotSupported;

public class OperaWebDriverImpl implements IWebDriver{

    @Override
    public WebDriver setUpDriver() {
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.addArguments("--start-maximized");
        operaOptions.addArguments("--no-sandbox");
        operaOptions.addArguments("--no-first-run");
        operaOptions.addArguments("--enable-extensions");
        operaOptions.addArguments("--homepage=about:blank");
        operaOptions.addArguments("--ignore-certificate-errors");
        operaOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        operaOptions.setCapability(CapabilityType.BROWSER_NAME, "opera");
        operaOptions.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("opera.browser.version", "88"));
        operaOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));
        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.OPERA, System.getProperty("opera.browser.version", "88"));
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }

            return new OperaDriver(operaOptions);
        } else
            return new RemoteWebDriver(getRemoteUrl(), operaOptions);
    }
}
