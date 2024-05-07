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

public class LogOutOfYourAccountTest {
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
        ValidatableResponse validatableResponse = userClient.createUser(user);
    }
    @Test
    @DisplayName("Проверка, выход по кнопке «Выйти» в личном кабинете")
    public void logOutOfYourAccount() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickTheAccountButton();
        LoginFormPage loginFormPage = new LoginFormPage(driver);
        loginFormPage.enterEmailAndPassword(user);
        loginFormPage.clickTheLoginButton();
        homePage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickExitButton();
        Assert.assertTrue(loginFormPage.isLoginIndicatorDisplayed());

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
