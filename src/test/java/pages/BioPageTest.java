package pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.PrintStream;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class BioPageTest {
    WebDriver driver;
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://dostepnoscarchitektoniczna.pl/o-mnie");
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void tearDown() {
        System.setErr(originalOut);
        System.setErr(originalErr);
        driver.quit();
    }

    @Test
    void checkLanguage() throws InterruptedException {
        BioPage bioPage = new BioPage(driver);
        bioPage.openBioPage();
        String lang = driver.findElement(By.cssSelector("html")).getAttribute("lang");
        assertEquals("pl-PL",lang);
        //setting language correctly is important for screen reader users
    }
    @Test
    void returnToHomePageTest() throws InterruptedException {
        BioPage bioPage = new BioPage(driver);
        bioPage.returnToHomePage();
        String urlExpected = driver.getCurrentUrl();
        assertEquals("https://dostepnoscarchitektoniczna.pl/", urlExpected);
    }
    @Test
    void acceptCookiesTest() throws InterruptedException {
        BioPage bioPage = new BioPage(driver);
        bioPage.acceptCookies();
        Thread.sleep(2000);
        boolean cookiesDisplay = driver.findElement(By.cssSelector("#cookie-notice > div")).isDisplayed();
        assertFalse(cookiesDisplay);
    }

    @Test
    void declineCookiesTest() throws InterruptedException {
        BioPage bioPage = new BioPage(driver);
        bioPage.declineCookies();
        Thread.sleep(2000);
        boolean cookiesDisplay = driver.findElement(By.cssSelector("#cookie-notice > div")).isDisplayed();
        assertFalse(cookiesDisplay);
    }
}