package ru.yandex.pageobject;

import client.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFormPage {

    private final WebDriver driver;
    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");//поле Email
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");// поле Password
    private final By TheLoginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");//кнопка Войти
    private final By TheRestorePasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");//кнопка Восстановить пароль
    private final By TheRegisterButton = By.xpath(".//a[text()='Зарегистрироваться']"); //кнопка Зарегестрироваться
    private final By loginIndicator = By.xpath(".//*[text()='Вход']"); //локатор надписи Вход

    public LoginFormPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Переход на страницу Авторизации")
    public void open() {
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("Отображение надписи Вход на старнице Авторизации")
    public boolean isLoginIndicatorDisplayed() {

        return driver.findElement(loginIndicator).isDisplayed();
    }
    @Step("клик по кнопке Зарегестрироваться")
    public void clickTheRegisterButton() {
        driver.findElement(TheRegisterButton).click();

    }
    @Step("клик по кнопке Войти")
    public void clickTheLoginButton() {
        driver.findElement(TheLoginButton).click();

    }
    @Step("Ввод данных в поля email и login")
    public void enterEmailAndPassword(User user) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

}
