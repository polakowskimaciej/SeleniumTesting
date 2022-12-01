package pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.PrintStream;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class HomePageTest {
    WebDriver driver;
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://dostepnoscarchitektoniczna.pl");
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        System.setErr(originalOut);
        System.setErr(originalErr);
        driver.quit();
    }
    @Test
    void testFillForm() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.fillForm();
        Thread.sleep(2000);
        Boolean display = driver.findElement(By.cssSelector("#wpforms-confirmation-77 > p")).isDisplayed();
        assertTrue(display);
    }

    @Test
    void checkLanguage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        String lang = driver.findElement(By.cssSelector("html")).getAttribute("lang");
        assertEquals("pl-PL",lang);
    }
}