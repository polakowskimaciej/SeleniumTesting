package pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class HomePageTest {
    WebDriver driver;
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;



    public String ColorVerify(WebElement target) {
        /*method to verify color code*/
        String colorCode = target.getCssValue("color");
        String hexacolor = Color.fromString(colorCode).asHex();
        return hexacolor;
    }

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
    void acceptCookiesTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        Thread.sleep(2000);
        Boolean cookiesDisplay = driver.findElement(By.cssSelector("#cookie-notice > div")).isDisplayed();
        assertFalse(cookiesDisplay);
    }
    @Test
    void declineCookiesTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.declineCookies();
        Thread.sleep(2000);
        Boolean cookiesDisplay = driver.findElement(By.cssSelector("#cookie-notice > div")).isDisplayed();
        assertFalse(cookiesDisplay);
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
    void testEmptyFormNumberOfErrors() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.leaveFormEmptyAndSubmit();
        Thread.sleep(2000);
        List<WebElement> errors = driver.findElements(By.className("wpforms-error"));
        assertEquals(6, errors.size());
        //3 errors for every inputfield and 3 correcponding error messages
    }

    @Test
    void testEmptyFormErrorText() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.leaveFormEmptyAndSubmit();
        Thread.sleep(2000);
        List<WebElement> errors = driver.findElements(By.className("wpforms-error"));
        for (WebElement error :
                errors) {
            if (error.getText().length() > 1)
                assertEquals("This field is required.", error.getText());
        }
    }

    @Test
    void testEmptyFormErrorTextColor() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.leaveFormEmptyAndSubmit();
        Thread.sleep(2000);
        List<WebElement> errors = driver.findElements(By.className("wpforms-error"));
        for (WebElement errorcolor :
                errors) {
            if (errorcolor.getText().length() > 1) ;
            assertEquals("#990000", ColorVerify(errorcolor));
        }
    } //does not work yet, detects original color of text instead of red

    @Test
    void testEmptyFormErrorBorderColor() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.leaveFormEmptyAndSubmit();
        Thread.sleep(2000);
        List<WebElement> errors = driver.findElements(By.className("wpforms-error"));
        for (WebElement errorcolor :
                errors) {
            if (errorcolor.getText().length() < 1) ;
            assertEquals("rgb(153 0 0)", ColorVerify(errorcolor));
        }
    } //does not work yet, detects original color of text instead of red

    @Test
    void testInvalidEmailText() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.submitWithInvalidEmail();
        Thread.sleep(2000);
        List<WebElement> errors = driver.findElements(By.className("wpforms-error"));
        for (WebElement error :
                errors) {
            if (error.getText().length() > 1)
                assertEquals("Please enter a valid email address.", error.getText());
        }
    }

    @Test
    void checkLanguage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        String lang = driver.findElement(By.cssSelector("html")).getAttribute("lang");
        assertEquals("pl-PL", lang);
        //setting language correctly is important for screen reader users
    }
    @Test
    void navigateToBioPageTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToBioPage();
        String urlExpected = driver.getCurrentUrl();
        assertEquals("https://dostepnoscarchitektoniczna.pl/o-mnie/", urlExpected);
        //Test passes with .click() but manual test shows, that using tab pressing enter works as well
    }
}