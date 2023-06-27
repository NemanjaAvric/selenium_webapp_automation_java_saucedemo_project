package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "product_sort_container")
    private WebElement orderAToZDropDownMenu;
    @FindBys({@FindBy(className = "inventory_item_name")})
    private List<WebElement> inventoryItemNameList;
    @FindBys({@FindBy(className = "inventory_item_price")})
    private List<WebElement> inventoryItemPriceList;
    @FindBys({@FindBy(className = "btn_inventory")})
    private List<WebElement> addToCartButtonsList;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartBadge;

    public InventoryPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public Select getSelect(WebElement element) {
        return new Select(element);
    }

    public void selectOrderItemsByIndex(int index, WebElement element) {
        getSelect(element).selectByIndex(index);
    }

    public boolean ckeckAToZOrder() {
        selectOrderItemsByIndex(0, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemNameList.size() - 1; i++) {
            if ((utility.compare(getWebElementText(inventoryItemNameList.get(i)), getWebElementText(inventoryItemNameList.get(i + 1)))) <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkZToAOrder() {
        selectOrderItemsByIndex(1, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemNameList.size() - 1; i++) {
            if ((utility.compare(getWebElementText(inventoryItemNameList.get(i)), getWebElementText(inventoryItemNameList.get(i + 1)))) >= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLowToHighPriceOrder() {
        selectOrderItemsByIndex(2, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemPriceList.size() - 1; i++) {
            if ((utility.compare(getWebElementText(inventoryItemNameList.get(i)), getWebElementText(inventoryItemNameList.get(i + 1)))) <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHighToLowPriceOrder() {
        selectOrderItemsByIndex(3, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemNameList.size() - 1; i++) {
            if ((utility.compare(getWebElementText(inventoryItemNameList.get(i)), getWebElementText(inventoryItemNameList.get(i + 1)))) >= 0) {
                return true;
            }
        }
        return false;
    }


    public void addRandomNumberOfItemsToCart() {
        for (int i = 0; i < utility.getRandomListIndex(addToCartButtonsList); i++) {
            clickOnWebElement(addToCartButtonsList.get(utility.getRandomListIndex(addToCartButtonsList)));
        }
    }

    public int countNumberOfItemsAddedToCart() {
        int counter = 0;
        for (int i = 0; i < addToCartButtonsList.size(); i++) {
            if (getWebElementText(addToCartButtonsList.get(i)).equals("Remove")) {
                counter++;
            }
        }
        return counter;
    }

    public int checkNumberInShoppingCartBadge() {
        if (!getWebElementText(shoppingCartBadge).equals("")) {
            return Integer.parseInt(getWebElementText(shoppingCartBadge));
        }
        return 0;
    }

    public int getShoppingCartBadgeNumberOfItems() {
        waitForTextToBePresentInElement(shoppingCartBadge, utility.convertIntToString(countNumberOfItemsAddedToCart()));
        return checkNumberInShoppingCartBadge();
    }

    public boolean checkNumberOfItemsAddedToTheCart() {
        if (countNumberOfItemsAddedToCart() == getShoppingCartBadgeNumberOfItems()) {
            return true;
        }
        return false;
    }


    public boolean checkStatusOfCart() {
        addRandomNumberOfItemsToCart();
        return checkNumberOfItemsAddedToTheCart();
    }

    public boolean checkIfStatusOfCartEquals0() {
        if (checkNumberInShoppingCartBadge() == 0) {
            return true;
        }
        return false;
    }

    public boolean doButtonWorkProperlyOrderly() {
        for (int i = 0; i < addToCartButtonsList.size(); i++) {
            if (getWebElementText(addToCartButtonsList.get(i)).equals("Add to cart")) {
                clickOnWebElement(addToCartButtonsList.get(i));
                if (!getWebElementText(addToCartButtonsList.get(i)).equals("Remove")) {
                    return false;
                }
            } else if (getWebElementText(addToCartButtonsList.get(i)).equals("Remove")) {
                clickOnWebElement(addToCartButtonsList.get(i));
                if (!getWebElementText(addToCartButtonsList.get(i)).equals("Add to cart")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean doButtonWorkProperlyRandomly() {
        for (int i = 0; i < addToCartButtonsList.size(); i++) {
            int randomIndex = utility.getRandomListIndex(addToCartButtonsList);
            if (getWebElementText(addToCartButtonsList.get(randomIndex)).equals("Add to cart")) {
                clickOnWebElement(addToCartButtonsList.get(randomIndex));
                if (!getWebElementText(addToCartButtonsList.get(randomIndex)).equals("Remove")) {
                    return false;
                }
            } else if (getWebElementText(addToCartButtonsList.get(randomIndex)).equals("Remove")) {
                clickOnWebElement(addToCartButtonsList.get(randomIndex));
                if (!getWebElementText(addToCartButtonsList.get(randomIndex)).equals("Add to cart")) {
                    return false;
                }
            }
        }
        return true;
    }


    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }
}
