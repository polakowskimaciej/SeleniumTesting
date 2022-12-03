package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    @FindBy(css = "#wpforms-77-field_0")
    static WebElement inputFieldName;
    @FindBy(css = "#wpforms-77-field_1")
    static WebElement inputFieldEmail;
    @FindBy(css = "#wpforms-77-field_2")
    static WebElement inputFieldMessage;
    @FindBy(css = "#wpforms-submit-77")
    static WebElement submitButton;
    @FindBy(linkText = "Notka bio")
    static WebElement bioPageLink;
    @FindBy(css = "#cn-accept-cookie")
    static WebElement okCookies;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void fillForm() throws InterruptedException {
        PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(2000);
        inputFieldName.sendKeys("John Doe");
        inputFieldEmail.sendKeys("johndoe@xmail.com");
        inputFieldMessage.sendKeys("Sample Message");
        submitButton.submit();
        //Accessibility - .click() simulates only mouse user behaviour,
        // to test this for both keyboard and mouse, I used .submit()
    }

    public void openHomePage() throws InterruptedException {
        PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(2000);
    }

    public void leaveFormEmptyAndSubmit() throws InterruptedException {
        PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(2000);
        submitButton.submit();
    }

    public void submitWithInvalidEmail() throws InterruptedException {
        PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(2000);
        inputFieldName.sendKeys("John Doe");
        inputFieldEmail.sendKeys("johndoemail.com");
        inputFieldMessage.sendKeys("Sample Message");
        submitButton.submit();
    }
    public void navigateToBioPage() throws InterruptedException {
        PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(3000);
        acceptCookies();
        new Actions(driver).moveToElement(bioPageLink).scrollToElement(bioPageLink).perform();
        bioPageLink.click();
    }
    public void acceptCookies() throws InterruptedException {
        PageFactory.initElements(driver, HomePage.class);
        Thread.sleep(3000);
        okCookies.click();
    }
}