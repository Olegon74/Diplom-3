package ru.yandex.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;
    public static final String RECOVERY_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By loginButton = By.xpath(".//*[text()='Войти']");


    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу, с восстановлением пароля")
    public void open() {
        driver.get(RECOVERY_PASSWORD_PAGE_URL);

    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();

    }
}
