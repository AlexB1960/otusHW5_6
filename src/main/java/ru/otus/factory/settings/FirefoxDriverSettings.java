package ru.otus.factory.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverSettings implements ISettings {

    @Override
    public AbstractDriverOptions settings(DesiredCapabilities desiredCapabilities, String... userArgs) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        switch (userArgs[0].toLowerCase().trim()) {
            case "headless": {
                firefoxOptions.addArguments("headless");
                break;
            }
            case "fullscreen": {
                firefoxOptions.addArguments("fullscreen");
                //chromeOptions.addArguments("--start-maximized");
                break;
            }
            case "maximize": {
                firefoxOptions.addArguments("--start-maximized");
                break;
            }
            case null, default: {
                firefoxOptions.addArguments("--start-maximized");
            }
        }
        //firefoxOptions.merge(desiredCapabilities);
        return firefoxOptions;
    }
}
