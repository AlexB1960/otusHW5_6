package common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.otus.factory.WebDriverFactory;

public abstract class AbsBaseTestSuite {
    protected WebDriver driver;
    protected String option;

    @BeforeEach
    public void init() {
        this.driver = new WebDriverFactory().create(option);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}
