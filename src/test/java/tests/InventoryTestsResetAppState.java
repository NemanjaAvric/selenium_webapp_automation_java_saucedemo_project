package tests;

import org.testng.annotations.*;
import pages.BurgerMenuPage;

public class InventoryTestsResetAppState extends InventoryTests {

    private BurgerMenuPage burgerMenuPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        burgerMenuPage = new BurgerMenuPage(driver, webDriverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        burgerMenuPage.resetAppState();
        assertTrue(inventoryPage.checkIfStatusOfCartEquals0());
    }

    @Test
    public void dropDownMenuAToZOrderResetAppState() {
        assertTrue(inventoryPage.ckeckAToZOrder());
    }

    @Test
    public void dropDownMenuZToAOrderResetAppState() {
        assertTrue(inventoryPage.checkZToAOrder());
    }

    @Test
    public void dropDownMenuPriceLowToHighOrderResetAppState() {
        assertTrue(inventoryPage.checkLowToHighPriceOrder());
    }

    @Test
    public void dropDownMenuPriceHighToLowOrderResetAppState() {
        assertTrue(inventoryPage.checkHighToLowPriceOrder());
    }

    @Test
    public void addToCartResetAppState() {
        assertTrue(inventoryPage.checkStatusOfCart());
    }

    @Test
    public void testIfButtonsWorkProperlyOrderlyResetAppState() {
        assertTrue(inventoryPage.doButtonWorkProperlyOrderly());
        assertTrue(inventoryPage.checkNumberOfItemsAddedToTheCart());
    }

    @Test
    public void testIfButtonsWorkProperlyResetAppState() {
        assertTrue(inventoryPage.doButtonWorkProperlyRandomly());
        assertTrue(inventoryPage.checkNumberOfItemsAddedToTheCart());
    }

    @AfterMethod
    public void afterMethod() {
        burgerMenuPage.clickOnWebElement(burgerMenuPage.getResetAppStateButton());
        assertTrue(inventoryPage.checkIfStatusOfCartEquals0());
    }
}
