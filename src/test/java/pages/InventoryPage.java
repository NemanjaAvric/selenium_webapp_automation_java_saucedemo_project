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
            if ((compare(inventoryItemNameList.get(i).getText(), inventoryItemNameList.get(i + 1).getText())) <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkZToAOrder() {
        selectOrderItemsByIndex(1, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemNameList.size() - 1; i++) {
            if ((compare(inventoryItemNameList.get(i).getText(), inventoryItemNameList.get(i + 1).getText())) >= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLowToHighPriceOrder() {
        selectOrderItemsByIndex(2, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemPriceList.size() - 1; i++) {
            if ((compare(inventoryItemNameList.get(i).getText(), inventoryItemNameList.get(i + 1).getText())) <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHighToLowPriceOrder() {
        selectOrderItemsByIndex(3, orderAToZDropDownMenu);
        for (int i = 0; i < inventoryItemNameList.size() - 1; i++) {
            if ((compare(inventoryItemNameList.get(i).getText(), inventoryItemNameList.get(i + 1).getText())) >= 0) {
                return true;
            }
        }
        return false;
    }

    public int compare(String a, String b) {
        return a.compareTo(b);
    }
}
