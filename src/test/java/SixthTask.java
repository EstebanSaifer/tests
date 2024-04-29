import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SixthTask {


    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Установка пути к драйверу Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/elinasihmirzaeva/Desktop/chromedriver_mac64/chromedriver");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testTrianglePuzzle() {
        // Перейти на странийу
        driver.get("https://playground.learnqa.ru/puzzle/triangle");

        // Найти кнопку "я сдаюсь" и кликнуть по ней
        WebElement giveUpButton = driver.findElement(By.xpath("//button[contains(text(), 'Я сдаюсь')]"));
        giveUpButton.click();

        // Проверка, что появилась ссылка "ссылка на ответы"
        WebElement answersLink = driver.findElement(By.linkText("Ссылка на ответы"));
        Assert.assertTrue(answersLink.isDisplayed(), "Ссылка на ответы не найдена на странице");

        //Поверка, что появилась кнопка "спрятать ответы"
        WebElement hideAnswersButton = driver.findElement(By.xpath("//button[contains(text(), 'Спрятать ответы')]"));
        Assert.assertTrue(hideAnswersButton.isDisplayed(), "Кнопка 'Спрятать ответы' не найдена на странице");
    }

    @AfterClass
    public void tearDown() {
        // Закрываемм браузер после выполнения всех тестов
        if (driver != null) {
            driver.quit();
        }
    }
}
