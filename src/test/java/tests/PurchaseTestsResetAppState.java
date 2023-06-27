package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BurgerMenuPage;
import pages.InventoryPage;

public class PurchaseTestsResetAppState extends PurchaseTests {

    private BurgerMenuPage burgerMenuPage;

    private InventoryPage inventoryPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        burgerMenuPage = new BurgerMenuPage(driver, webDriverWait);
        inventoryPage = new InventoryPage(driver, webDriverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        burgerMenuPage.resetAppState();
        assertTrue(inventoryPage.checkIfStatusOfCartEquals0());
    }

    @Test
    public void checkNumberOfItemsInCart() {
        assertTrue(yourCartPage.checkNumberOfItemsInCart());
    }

    @Test
    public void continueShopping() {
        yourCartPage.continueShopping();
        assertTrue(INVENTORY_PAGE_URL_ENDING);
    }

    @Test
    public void continueShoppingMultipleTimesResetAppState() {
        for (int i = 0; i <= getRandomIterations(); i++) {
            yourCartPage.continueShopping();
            assertTrue(INVENTORY_PAGE_URL_ENDING);
        }
    }

    @Test
    public void checkOutCancelResetAppState() {
        yourCartPage.checkoutCancel();
        assertTrue(YOUR_CART_PAGE_URL_ENDING);
    }

    @Test
    public void checkOutCancelMultipleTimesResetAppState() {
        for (int i = 0; i <= getRandomIterations(); i++) {
            yourCartPage.checkoutCancel();
            assertTrue(YOUR_CART_PAGE_URL_ENDING);
        }
    }

    @Test
    public void makeAPurchaseResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithAllData();
        checkOutOverviewPage.clickOnWebElement(checkOutOverviewPage.getFinishButton());
        assertEquals(checkOutCompletePage.getWebElementText(checkOutCompletePage.getThankYouForYourOrderInscription()), EXPETCTED_THANK_YOU_FOR_YOUR_ORDER_INSCRIPTION);
        assertEquals(checkOutCompletePage.getWebElementText(checkOutCompletePage.getTextConfirmingTheOrder()), EXCPECTED_TEXT_CONFIRMING_THE_ORDER);
        assertEquals(checkOutCompletePage.getWebElementText(checkOutCompletePage.getCheckOutCompleteTitle()), EXCPECTED_CHECKOUT_COMPLETE_TITLE_TEXT);
        checkOutCompletePage.clickOnWebElement(checkOutCompletePage.getBackHomeButton());
        assertTrue(INVENTORY_PAGE_URL_ENDING);
    }

    @Test
    public void cancelAPurchaseResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithAllData();
        checkOutOverviewPage.clickOnWebElement(checkOutOverviewPage.getCancelButton());
        assertTrue(INVENTORY_PAGE_URL_ENDING);
    }

    @Test
    public void makeAPurchaseNoDataResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.clickOnWebElement(checkOutYourInformationPage.getContinueButton());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseNoFirstNameResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithFakeLastNameZipCode();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseNoLastNameResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithFakeFirstNameZipCode();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_LAST_NAME_IS_REQUIRED_MESAGE);
    }

    @Test
    public void makeAPurchaseNoZipCodeResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithFakeFirstNameLastName();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_ZIP_POSTAL_CODE_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseOnlyFirstNameResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithOnlyFakeFirstName();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_LAST_NAME_IS_REQUIRED_MESAGE);
    }

    @Test
    public void makeAPurchaseOnlyLastNameResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithOnlyFakeLastName();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseOnlyZipPostalCodeResetAppState() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithOnlyZipCode();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @AfterMethod
    public void afterMethod() {
        burgerMenuPage.resetAppState();
        assertTrue(inventoryPage.checkIfStatusOfCartEquals0());
    }
}
