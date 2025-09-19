package ru.otus.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.otus.exception.BrowserNotSupportedException;
import ru.otus.factory.settings.ChromeDriverSettings;
import ru.otus.factory.settings.EdgeDriverSettings;
import ru.otus.factory.settings.FirefoxDriverSettings;
import ru.otus.factory.settings.ISettings;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class WebDriverFactory {

    public RemoteWebDriver create(String browser, String option) {
        RemoteWebDriver driver;

        String remoteURL = System.getProperty("remoteURL");
        if (!remoteURL.isEmpty()) {
            //String remoteURL = System.getProperty("remoteURL");
            String browserName = "chrome";
            String browserVersion = System.getProperty("browserVersion");
            ChromeOptions options = new ChromeOptions();
            options.addArguments(option);
            options.setCapability("browserName", browserName);
            options.setCapability("browserVersion", browserVersion);
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                /* How to add test badge */
                put("name", "Test Login with Remote Driver");

                /* How to set session timeout */
                put("sessionTimeout", "15m");

                /* How to set timezone */
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});

                /*                    *//* How to add "trash" button *//*
                    put("labels", new HashMap<String, Object>() {{
                        put("manual", "true");
                    }});

                    *//* How to enable video recording *//*
                    put("enableVideo", true);*/
            }});
            try {
                driver = new RemoteWebDriver(new URL(remoteURL), options);
                //"http://193.104.57.173/wd/hub"
                return driver;
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }

        switch(browser) {
            case "chrome": {
                ISettings set = new ChromeDriverSettings();
                driver = new ChromeDriver((ChromeOptions) set.settings(null, option));
                break;
            }
            case "firefox": {
                ISettings set = new FirefoxDriverSettings();
                driver = new FirefoxDriver((FirefoxOptions) set.settings(null, option));
                break;
            }
            case "edge": {
                ISettings set = new EdgeDriverSettings();
                driver = new EdgeDriver((EdgeOptions) set.settings(null, option));
                break;
            }
            case null, default: {
                throw new BrowserNotSupportedException(browser);
            }
        }
        return driver;
    }

}
