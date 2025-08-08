package second;

import common.AbsBaseTestSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.otus.pages.SecondPage;


public class SecondPageFullscreenTest extends AbsBaseTestSuite {
    private final Logger log = LogManager.getLogger(SecondPageFullscreenTest.class);
    private final String login = System.getProperty("login");
    private final String password = System.getProperty("password");

    public SecondPageFullscreenTest() {
        super.option = "fullscreen";
    }

    @Test
    public void fillTest() {
        SecondPage secondPage = new SecondPage(driver);
        secondPage.open();
        log.info("Старт теста заполнения всех полей формы");
        secondPage.fillForm(login, "qa@mail.ru", password,"qwe123",
                "30-12-2001", "beginner");
        log.info("Проверка результата теста заполнения всех полей формы");
        secondPage.assertOutputText(login);
    }

}
