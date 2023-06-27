package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests extends BaseTest {


    protected InventoryPage inventoryPage;

    private LoginPage loginPage;


    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        inventoryPage = new InventoryPage(driver, webDriverWait);

    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        driverFullscreen();
        loginPage.logIn(STANDARD_USER_LOGIN, VALID_PASSWORD);
    }

    @Test
    public void dropDownMenuAToZOrder() {
        assertTrue(inventoryPage.ckeckAToZOrder());
    }

    @Test
    public void dropDownMenuZToAOrder() {
        assertTrue(inventoryPage.checkZToAOrder());
    }

    @Test
    public void dropDownMenuPriceLowToHighOrder() {
        assertTrue(inventoryPage.checkLowToHighPriceOrder());
    }

    @Test
    public void dropDownMenuPriceHighToLowOrder() {
        assertTrue(inventoryPage.checkHighToLowPriceOrder());
    }

    @Test
    public void addToCart() {
        assertTrue(inventoryPage.checkStatusOfCart());
    }

    @Test
    public void testIfButtonsWorkProperlyOrderly() {
        assertTrue(inventoryPage.doButtonWorkProperlyOrderly());
        assertTrue(inventoryPage.checkNumberOfItemsAddedToTheCart());
    }

    @Test
    public void testIfButtonsWorkProperlyRandomly() {
        assertTrue(inventoryPage.doButtonWorkProperlyRandomly());
        assertTrue(inventoryPage.checkNumberOfItemsAddedToTheCart());
    }
}
