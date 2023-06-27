package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class YourCartPage extends BasePage {
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    @FindBy(id = "continue-shopping")
    private WebElement continueShopingButton;
    @FindBys({@FindBy(className = "cart_button")})
    private List<WebElement> itemsInCart;
    private InventoryPage inventoryPage;
    private CheckOutYourInformationPage checkOutYourInformationPage;

    public YourCartPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
        inventoryPage = new InventoryPage(driver, webDriverWait);
        checkOutYourInformationPage = new CheckOutYourInformationPage(driver, webDriverWait);
    }

    public boolean checkNumberOfItemsInCart() {
        inventoryPage.addRandomNumberOfItemsToCart();
        clickOnWebElement(inventoryPage.getShoppingCartBadge());
        waitForTextToBePresentInElement(inventoryPage.getShoppingCartBadge(), utility.convertIntToString(itemsInCart.size()));
        if (inventoryPage.getShoppingCartBadgeNumberOfItems() == itemsInCart.size()) {
            return true;
        }
        return false;
    }

    public void continueShopping() {
        if (checkNumberOfItemsInCart()) {
            clickOnWebElement(continueShopingButton);
        }
    }

    public void checkoutCancel() {
        if (checkNumberOfItemsInCart()) {
            clickOnWebElement(checkoutButton);
            clickOnWebElement(checkOutYourInformationPage.getCancelButton());
        }
    }

    public void checkoutContinue() {
        if (checkNumberOfItemsInCart()) {
            clickOnWebElement(checkoutButton);
        }
    }
}
