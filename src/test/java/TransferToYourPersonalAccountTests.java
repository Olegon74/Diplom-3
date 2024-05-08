import client.Credentials;
import client.User;
import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.pageobject.HomePage;
import ru.yandex.pageobject.LoginFormPage;
import ru.yandex.pageobject.PersonalAccountPage;

import java.util.concurrent.TimeUnit;

import static generarator.data.DataGenerator.*;

public class TransferToYourPersonalAccountTests {
    private WebDriver driver;
    private User user;
    public UserClient userClient;
    @Before
    public void startUp() {
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Orekhov/Diplom-3/src/test/resources/yandexdriver.exe"); //для запуска тестов в Яндекс браузере нужно раскомментировать эту строку
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        user = new User(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userClient = new UserClient();

    }
    @Test
    @DisplayName("Переход в Личный кабинет")
    public void TransferToYourPersonalAccount() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickPersonalAccountButton();
        LoginFormPage loginFormPage = new LoginFormPage(driver);
        Assert.assertTrue(loginFormPage.isLoginIndicatorDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета, в конструктор")
    public void SwitchingFromYourPersonalAccountToTheConstructor(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickPersonalAccountButton();
        LoginFormPage loginFormPage = new LoginFormPage(driver);
        loginFormPage.enterEmailAndPassword(user);
        loginFormPage.clickTheLoginButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickConstructorButton();
        Assert.assertTrue(homePage.isBurgerInscriptionDisplayed());

    }
    @Test
    @DisplayName("Переход из личного кабинета, при клике на логотип")
    public void ToGoFromYourPersonalAccountClickOnTheLogo(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickPersonalAccountButton();
        LoginFormPage loginFormPage = new LoginFormPage(driver);
        loginFormPage.enterEmailAndPassword(user);
        loginFormPage.clickTheLoginButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogoButton();
        Assert.assertTrue(homePage.isBurgerInscriptionDisplayed());
    }
    @After
    public void tearDown() {
        UserClient userClient = new UserClient();
        Credentials credentials = new Credentials(user.getEmail(), user.getPassword());
        ValidatableResponse responseLogin = userClient.login(credentials);
        String accessToken = userClient.getAccessToken(responseLogin);
        userClient.deletingUsersAfterTests(accessToken);
        driver.quit();

    }

}
