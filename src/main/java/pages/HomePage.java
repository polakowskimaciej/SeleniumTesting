package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    }
}