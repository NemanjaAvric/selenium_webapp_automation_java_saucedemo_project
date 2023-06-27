package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Utility;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    protected Utility utility = new Utility();

    public BasePage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnWebElement(WebElement element) {
        element.click();
    }

    public void clearInputField(WebElement element) {
        clickOnWebElement(element);
        sendKeysToElement(element, Keys.CONTROL + "a");
        sendKeysToElement(element, Keys.DELETE);
    }

    public void waitForTextToBePresentInElement(WebElement element, String text) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public String getWebElementText(WebElement element) {
        return element.getText();
    }

    public void sendKeysToElement(WebElement element, Keys keyToSend) {
        element.sendKeys(keyToSend);
    }

    public void sendKeysToElement(WebElement element, String keysToSend) {
        element.sendKeys(keysToSend);
    }

    public boolean webElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }
}
