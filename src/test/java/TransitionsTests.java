import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.pageobject.HomePage;
import java.util.concurrent.TimeUnit;


public class TransitionsTests {
    private WebDriver driver;

    @Before
    public void startUp() {
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Orekhov/Diplom-3/src/test/resources/yandexdriver.exe"); //для запуска тестов в Яндекс браузере нужно раскомментировать эту строку
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

    }
    @Test
    @DisplayName("Переход к разделу Булки")
    public void TransitionsToTheSectionOfBread() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickTheFillingButton();
        homePage.clickTheBunButton();
        Assert.assertEquals("Булки", homePage.getTextFromSelectedSection());

    }
    @Test
    @DisplayName("Переход к разделу Соусы")
    public void TransitionsToTheSectionOfSauces() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickTheFillingButton();
        homePage.clickTheSaucesButton();
        Assert.assertEquals("Соусы", homePage.getTextFromSelectedSection());

    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void TransitionsToTheSectionOfFilling() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickTheSaucesButton();
        homePage.clickTheFillingButton();
        Assert.assertEquals("Начинки", homePage.getTextFromSelectedSection());

    }
    public void tearDown() {
        driver.quit();
    }

}
