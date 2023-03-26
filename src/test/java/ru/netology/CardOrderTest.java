package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderTest {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll(){
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Studies\\IdeaProjects\\WebInterfaceTestingV1\\Driver\\Win\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.chromedriver().win().setup();
    }
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();

    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestSomething(){
        driver.get("https://www.google.com/");
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Василий");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79114865807");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement (By.cssSelector("[data-test-id=order-success]")).getText().trim();

        assertEquals(expected, actual);
    }


}