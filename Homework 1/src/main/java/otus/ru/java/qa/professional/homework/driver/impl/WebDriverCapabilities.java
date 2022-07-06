package otus.ru.java.qa.professional.homework.driver.impl;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverCapabilities {
//TODO вынести и как-то прикрутить
    public void getCapabilities(String browser){

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", Platform.WINDOWS);
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", browser);
        caps.setCapability("browser_version", "latest");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.14.0");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        //capabilities.setCapability("version", "77.0");
        capabilities.setCapability("platform", Platform.WINDOWS); // If this cap isn't specified, it will just get any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs

        capabilities.setCapability("selenium_version","4.0.0-alpha-2");
        capabilities.setCapability("timezone","UTC+05:30");
        capabilities.setCapability("geoLocation","IN");
        capabilities.setCapability("chrome.driver","78.0");
    }
}
