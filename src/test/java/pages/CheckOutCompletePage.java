package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutCompletePage extends BasePage {
    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;
    @FindBy(className = "complete-header")
    private WebElement thankYouForYourOrderInscription;
    @FindBy(className = "complete-text")
    private WebElement textConfirmingTheOrder;
    @FindBy(className = "title")
    private WebElement checkOutCompleteTitle;

    public WebElement getCheckOutCompleteTitle() {
        return checkOutCompleteTitle;
    }

    public CheckOutCompletePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getBackHomeButton() {
        return backHomeButton;
    }

    public WebElement getThankYouForYourOrderInscription() {
        return thankYouForYourOrderInscription;
    }

    public WebElement getTextConfirmingTheOrder() {
        return textConfirmingTheOrder;
    }
}
