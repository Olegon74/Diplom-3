package ru.yandex.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/";
    private final By thePersonalAccountButton = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");//кнопка Личный кабинет
    private final By theSignInAccountButton = By.xpath(".//button[text()='Войти в аккаунт'] ");//кнопка Войти в аккаунт
    private final By setBurgerIndicator = By.xpath(".//*[text()='Соберите бургер']"); //локатор надписи Соберите бургер
    private final By TheBunButton = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By TheSaucesButton = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By TheFillingButton = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']"); //локатор кнопки Оформить заказ
    private final By currentSection = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");


    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void open() {

        driver.get(URL);
    }

    @Step("Проверка, что отображается кнопка оформления заказа")
    public boolean isHomePageOpen(){
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Проверка, отображется ли надпись, Соберите")
    public boolean isBurgerInscriptionDisplayed() {

        return driver.findElement(setBurgerIndicator).isDisplayed();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccountButton() {

        driver.findElement(thePersonalAccountButton).click();
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickTheAccountButton() {
        driver.findElement(theSignInAccountButton).click();

    }
    @Step("Клик по кнопке Булки")
    public void clickTheBunButton() {
        driver.findElement(TheBunButton).click();

    }
    @Step("Клик по кнопке Соусы")
    public void clickTheSaucesButton() {
        driver.findElement(TheSaucesButton).click();

    }
    @Step("Клик по кнопке Начинки")
    public void clickTheFillingButton() {
        driver.findElement(TheFillingButton).click();

    }
    @Step("Выбрать тектс из текущего раздела")
    public String getTextFromSelectedSection() {
        return driver.findElement(currentSection).getText();
    }




}
