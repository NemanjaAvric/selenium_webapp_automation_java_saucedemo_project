package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BurgerMenuPage extends BasePage {
    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;
    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateButton;
    private final String LOGOUT_BUTTON_EXPECTED_TEXT = "Logout";

    public BurgerMenuPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void burgerMenuButtonClick() {
        clickOnWebElement(burgerMenuButton);
        waitForTextToBePresentInElement(logoutButton, LOGOUT_BUTTON_EXPECTED_TEXT);
    }

    public void logOut() {
        clickOnWebElement(burgerMenuButton);
        clickOnWebElement(logoutButton);
    }

    public WebElement getResetAppStateButton() {
        return resetAppStateButton;
    }

    public String getLOGOUT_BUTTON_EXPECTED_TEXT() {
        return LOGOUT_BUTTON_EXPECTED_TEXT;
    }

    public void resetAppState() {
        clickOnWebElement(burgerMenuButton);
        clickOnWebElement(resetAppStateButton);
    }
}
