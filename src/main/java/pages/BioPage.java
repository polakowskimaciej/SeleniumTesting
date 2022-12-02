package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BioPage extends AbstractPage {

    public BioPage(WebDriver driver) {
        super(driver);
    }

    public void openBioPage() throws InterruptedException {
        PageFactory.initElements(driver, BioPage.class);
        Thread.sleep(2000);
    }
}
