package ru.yandex.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Клик по кнопке выйти")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по ссылке Логотип")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

}
