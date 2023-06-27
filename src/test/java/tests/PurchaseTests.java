package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class PurchaseTests extends BaseTest {
    protected YourCartPage yourCartPage;

    private LoginPage loginPage;

    protected final String YOUR_CART_PAGE_URL_ENDING = "/cart.html";

    protected CheckOutYourInformationPage checkOutYourInformationPage;

    protected CheckOutOverviewPage checkOutOverviewPage;

    protected CheckOutCompletePage checkOutCompletePage;

    protected final String EXPETCTED_THANK_YOU_FOR_YOUR_ORDER_INSCRIPTION = "Thank you for your order!";

    protected final String EXCPECTED_TEXT_CONFIRMING_THE_ORDER = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    protected final String EXCPECTED_CHECKOUT_COMPLETE_TITLE_TEXT = "Checkout: Complete!";

    protected final String EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE = "Error: First Name is required";

    protected final String EXCPECTED_LAST_NAME_IS_REQUIRED_MESAGE = "Error: Last Name is required";

    protected final String EXCPECTED_ZIP_POSTAL_CODE_IS_REQUIRED_MESSAGE = "Error: Postal Code is required";


    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        yourCartPage = new YourCartPage(driver, webDriverWait);
        checkOutYourInformationPage = new CheckOutYourInformationPage(driver, webDriverWait);
        checkOutOverviewPage = new CheckOutOverviewPage(driver, webDriverWait);
        checkOutCompletePage = new CheckOutCompletePage(driver, webDriverWait);

    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        driverFullscreen();
        loginPage.logIn(STANDARD_USER_LOGIN, VALID_PASSWORD);
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
    public void continueShoppingMultipleTimes() {
        for (int i = 0; i <= getRandomIterations(); i++) {
            yourCartPage.continueShopping();
            assertTrue(INVENTORY_PAGE_URL_ENDING);
        }
    }

    @Test
    public void checkOutCancel() {
        yourCartPage.checkoutCancel();
        assertTrue(YOUR_CART_PAGE_URL_ENDING);
    }

    @Test
    public void checkOutCancelMultipleTimes() {
        for (int i = 0; i <= getRandomIterations(); i++) {
            yourCartPage.checkoutCancel();
            assertTrue(YOUR_CART_PAGE_URL_ENDING);
        }
    }

    @Test
    public void makeAPurchase() {
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
    public void cancelAPurchase() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithAllData();
        checkOutOverviewPage.clickOnWebElement(checkOutOverviewPage.getCancelButton());
        assertTrue(INVENTORY_PAGE_URL_ENDING);
    }

    @Test
    public void makeAPurchaseNoData() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.clickOnWebElement(checkOutYourInformationPage.getContinueButton());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseNoFirstName() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithFakeLastNameZipCode();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseNoLastName() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithFakeFirstNameZipCode();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_LAST_NAME_IS_REQUIRED_MESAGE);
    }

    @Test
    public void makeAPurchaseNoZipCode() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShopingWithFakeFirstNameLastName();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_ZIP_POSTAL_CODE_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseOnlyFirstName() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithOnlyFakeFirstName();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_LAST_NAME_IS_REQUIRED_MESAGE);
    }

    @Test
    public void makeAPurchaseOnlyLastName() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithOnlyFakeLastName();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    @Test
    public void makeAPurchaseOnlyZipPostalCode() {
        yourCartPage.checkoutContinue();
        checkOutYourInformationPage.continueShoppingWithOnlyZipCode();
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()));
        assertTrue(checkOutYourInformationPage.checkErrorButtonListSize());
        assertTrue(checkOutYourInformationPage.errorButtonsListButtonsAreDisplayed());
        assertTrue(checkOutYourInformationPage.webElementIsDisplayed(checkOutYourInformationPage.getErrorButton()));
        assertEquals(checkOutYourInformationPage.getWebElementText(checkOutYourInformationPage.getErrorMessageFirstNameIsRequired()), EXCPECTED_FIRST_NAME_IS_REQUIRED_MESSAGE);
    }

    protected int getRandomIterations() {
        return (int) (Math.random() * 10);
    }
}
