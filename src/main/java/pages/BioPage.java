package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BioPage extends AbstractPage {
    @FindBy(css = "#post-79 > div > p:nth-child(11) > span > a")
    static WebElement returnToHomePageLink;
    @FindBy(css = "#cn-accept-cookie")
    static WebElement okCookies;
    @FindBy(id = "cn-close-notice")
    static WebElement closeCookies;


    public BioPage(WebDriver driver) {
        super(driver);
    }

    public void openBioPage() throws InterruptedException {
        PageFactory.initElements(driver, BioPage.class);
        Thread.sleep(2000);
    }

    public void returnToHomePage() throws InterruptedException {
        PageFactory.initElements(driver, BioPage.class);
        Thread.sleep(2000);
        acceptCookies();
        Thread.sleep(2000);
        new Actions(driver).moveToElement(returnToHomePageLink).perform();
        Thread.sleep(2000);
        returnToHomePageLink.click();
    }

    public void acceptCookies() throws InterruptedException {
        PageFactory.initElements(driver, BioPage.class);
        Thread.sleep(3000);
        okCookies.click();
    }

    public void declineCookies() throws InterruptedException {
        PageFactory.initElements(driver, BioPage.class);
        Thread.sleep(3000);
        closeCookies.click();

    }
}
