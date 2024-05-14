package ru.yandex.pageobject;

import client.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationFormPage {
    private final WebDriver driver;
    private static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final By NameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");//поле Имя
    private final By EmailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");//поле Email
    private final By PasswordField= By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]"); //поле Пароль
    private final By TheRegisterButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");//кнопка Зарегестрироваться
    private final By theLoginButton = By.xpath(".//a[@class='Auth_link__1fOlj']");//кнопка Войти
    private final By wrongPassword = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;

    }
    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(REGISTER_PAGE_URL);
    }
    @Step("Ввод регистрационных данных, email, пароль, имя")
    public void registerUser(User user) {
        driver.findElement(EmailField).click();
        driver.findElement(EmailField).sendKeys(user.getEmail());
        driver.findElement(PasswordField).click();
        driver.findElement(PasswordField).sendKeys(user.getPassword());
        driver.findElement(NameField).click();
        driver.findElement(NameField).sendKeys(user.getName());
    }

    @Step("Receiving a boolean value when an incorrect password is entered")
    public boolean isWrongPasswordDisplayed() {
        return driver.findElement(wrongPassword).isDisplayed();
    }
    @Step("клик по кнопке Зарегестрироваться")
    public void clickRegisterButton() {
        driver.findElement(TheRegisterButton).click();

    }
    @Step("Клик по кнопке Войти внизу")
    public void clickSignInButton() {
        driver.findElement(theLoginButton).click();
    }

}

