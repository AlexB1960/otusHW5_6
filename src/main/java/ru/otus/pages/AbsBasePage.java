package ru.otus.pages;

import org.openqa.selenium.WebDriver;
import ru.otus.annotation.Path;
import ru.otus.common.AbsCommon;

public abstract class AbsBasePage extends AbsCommon {
    private String baseUrl = System.getProperty("baseURL");
    private String remoteURL = System.getProperty("remoteURL");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    private String getPath() {
        Class clazz = getClass();
        if (clazz.isAnnotationPresent(Path.class)) {
            Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
            return path.value();
        }
        return "";
    }

    public void open() {
        if (!remoteURL.isEmpty()) {
            super.driver.get(remoteURL);
        } else {
            super.driver.get(baseUrl + getPath());
        }
    }

}
