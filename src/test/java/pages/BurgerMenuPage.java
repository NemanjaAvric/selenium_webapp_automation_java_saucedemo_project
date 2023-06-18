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
    private final String LOGOUT_BUTTON_TEXT = "Logout";

    public BurgerMenuPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void burgerMenuButtonClick() {
        burgerMenuButton.click();
        waitForTextToBePresentInElement(logoutButton, LOGOUT_BUTTON_TEXT);
    }

    public void logOut() {
        burgerMenuButton.click();
        logoutButton.click();
    }
}
