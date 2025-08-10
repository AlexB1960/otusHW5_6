package ru.otus.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.otus.data.DriverNameData;
import ru.otus.exception.BrowserNotSupportedException;
import ru.otus.factory.settings.ChromeDriverSettings;
import ru.otus.factory.settings.EdgeDriverSettings;
import ru.otus.factory.settings.FirefoxDriverSettings;
import ru.otus.factory.settings.ISettings;

import java.util.Arrays;

public class WebDriverFactory {
    private String browser = System.getProperty("browser").toLowerCase().trim();

    public WebDriver create(String option) {
        WebDriver driver;

        if (!isValid(browser)) {
            System.out.print("Браузер " + browser + " не входит в состав используемых. ");
            //browser = DriverNameData.CHROME.toString().toLowerCase();
            //browser = DriverNameData.CHROME.name().toLowerCase();
            /*browser = Arrays.stream(DriverNameData.values()).findFirst().toString().substring(9,
                                    Arrays.stream(DriverNameData.values()).findFirst().toString()
                                    .length() - 1).toLowerCase();
            browser = Arrays.stream(DriverNameData.values()).findFirst().stream().
                      iterator().next().name().toLowerCase();*/

            browser = Arrays.stream(DriverNameData.values()).iterator().next().name().toLowerCase();
            System.out.println("Будет вызван браузер " + browser);
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
        if (option.toLowerCase().trim().equals("fullscreen")) {
            driver.manage().window().fullscreen();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public boolean isValid(String name) {
        for (DriverNameData driverNameData: DriverNameData.values()) {
            if (driverNameData.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
