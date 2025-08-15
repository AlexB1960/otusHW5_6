package ru.otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;


public class SecondPage extends AbsBasePage {
    private final By FIELD_NAME = By.id("username");
    private final By FIELD_EMAIL = By.id("email");
    private final By FIELD_PASS = By.id("password");
    private final By FIELD_CHECK_PASS = By.id("confirm_password");
    private final By FIELD_BIRTHDAY = By.id("birthdate");
    private final By FIELD_LEVEL = By.id("language_level");
    private final By BUTTON_REGISTRY = By.cssSelector("input[type='submit']");
    private final By OUTPUT_TEXT = By.id("output");
    private final Logger log = LogManager.getLogger(SecondPage.class);

    public SecondPage(WebDriver driver) {
        super(driver, "/form.html");
    }

    public void fillForm(String name, String email, String pass, String pass2,
                         String birthday, String level) {

        log.info("Сравнение пароля с подтверждением пароля");
        if (!pass.equals(pass2)) {
            log.info("Ошибка совпвдения пароля с подтверждением пароля.");
        }
        String script = "if(arguments[2]!=arguments[3]) {alert('Пароль не совпадает с подтверждением пароля.'); return false; } else {"
                      + "document.querySelector('#username').value=arguments[0];"
                      + "document.querySelector('#email').value=arguments[1];"
                      + "document.querySelector('#password').value=arguments[2];"
                      + "document.querySelector('#confirm_password').value=arguments[3];"
                      + "document.querySelector('#birthdate').value=arguments[4].toString().split('-').reverse().join('-');"
                      + "document.querySelector('#language_level').value=arguments[5];"
                      + "document.querySelector('input[type=\"submit\"]').click();"
                      + "return true;}";
        ((JavascriptExecutor) driver).executeScript(script, name, email, pass, pass2,
                                                    birthday, level);
    }

    public void assertOutputText(String login) {
        if (isAvailable(OUTPUT_TEXT)) {
            assertThat(getElement(OUTPUT_TEXT).getText())
                    .isEqualTo("Имя пользователя: " + login +"\n" +
                    "Электронная почта: qa@mail.ru\n" +
                    "Дата рождения: 2001-12-30\n" +
                    "Уровень языка: beginner");
        }
    }

}
