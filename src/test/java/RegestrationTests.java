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
import ru.yandex.pageobject.RegistrationFormPage;

import java.util.concurrent.TimeUnit;

import static generarator.data.DataGenerator.*;

public class RegestrationTests {
    private WebDriver driver;
    private User user;


    @Before
    public void startUp() {
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Orekhov/Diplom-3/src/test/resources/yandexdriver.exe"); //для запуска тестов в Яндекс браузере нужно раскомментировать эту строку
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }
    @Test
    @DisplayName("Проверка успешной регистрации")
    public void successfulRegistrationTest() {
        user = new User(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();
        registrationFormPage.registerUser(user);
        registrationFormPage.clickRegisterButton();
        LoginFormPage loginFormPage = new LoginFormPage(driver);
        loginFormPage.enterEmailAndPassword(user);
        loginFormPage.clickTheLoginButton();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageOpen());
    }
    @Test
    @DisplayName("Регистрация с некорректным паролем, состоящим из 4 смиволов")
    public void registrationWithaShortPassword() {
        user = new User(RANDOM_EMAIL, RANDOM_PASS_5_CHAR, RANDOM_NAME);
        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();
        registrationFormPage.registerUser(user);
        Assert.assertTrue(registrationFormPage.isWrongPasswordDisplayed());

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







