package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests extends BaseTest {


    private InventoryPage inventoryPage;

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
        driver.manage().window().fullscreen();
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);

    }

    @Test
    public void dropDownMenuAToZOrder() {
        Assert.assertTrue(inventoryPage.ckeckAToZOrder());
    }

    @Test
    public void dropDownMenuZToAOrder() {
        Assert.assertTrue(inventoryPage.checkZToAOrder());
    }

    @Test
    public void dropDownMenuPriceLowToHighOrder() {
        Assert.assertTrue(inventoryPage.checkLowToHighPriceOrder());
    }

    @Test
    public void dropDownMenuPriceHighToLowOrder() {
        Assert.assertTrue(inventoryPage.checkHighToLowPriceOrder());
    }
}
