package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BurgerMenuPage;
import pages.LoginPage;
import utility.Utility;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;
    private BurgerMenuPage burgerMenuPage;
    private Utility faker;
    private final String LOCKED_OUT_USER_USERNAME = "locked_out_user";
    private String fakeUsername;
    private String fakePassword;
    private final String INVALID_DATA_LOGIN_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    private final String LOCKED_OUT_USER_LOGIN_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
    private final String INVENTORY_PAGE_URL_ENDING = "/inventory.html";
    private final String LOGOUT_BUTTON_EXPECTED_TEXT = "Logout";

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        burgerMenuPage = new BurgerMenuPage(driver, webDriverWait);
        faker = new Utility();
        fakeUsername = faker.getFakeUsername();
        fakePassword = faker.getFakePassword();
    }

    @Test
    public void loginValidData() {
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().endsWith(INVENTORY_PAGE_URL_ENDING));
        burgerMenuPage.burgerMenuButtonClick();
        Assert.assertEquals(burgerMenuPage.getLogoutButton().getText(), LOGOUT_BUTTON_EXPECTED_TEXT);
    }

    @DataProvider(name = "invalidLoginData")
    public String[][] getInvalidLoginData() {
        return new String[][]{
                {fakeUsername, VALID_PASSWORD},
                {getRandomValidUsername(), fakePassword},
                {fakeUsername, fakePassword}};
    }

    @Test(dataProvider = "invalidLoginData")
    public void loginInvalidData(String username, String password) {
        loginPage.logIn(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertEquals(loginPage.getEpicSadFaceMessage().getText(), INVALID_DATA_LOGIN_MESSAGE);
    }

    @Test
    public void loginLockedOutUser() {
        loginPage.logIn(LOCKED_OUT_USER_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertEquals(loginPage.getEpicSadFaceMessage().getText(), LOCKED_OUT_USER_LOGIN_MESSAGE);
    }

    @Test
    public void logout() {
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        burgerMenuPage.logOut();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }

    @Test(dataProvider = "invalidLoginData")
    public void loginValidDataAfterMultipleLoginInvalidData(String username, String password) {
        int randomIterations = getRandomIterations();
        for (int i = 0; i <= randomIterations; i++) {
            loginPage.logIn(username, password);
            Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
            Assert.assertEquals(loginPage.getEpicSadFaceMessage().getText(), INVALID_DATA_LOGIN_MESSAGE);
            loginPage.clearLoginInputFields();
        }
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().endsWith(INVENTORY_PAGE_URL_ENDING));
        burgerMenuPage.burgerMenuButtonClick();
        Assert.assertEquals(burgerMenuPage.getLogoutButton().getText(), LOGOUT_BUTTON_EXPECTED_TEXT);
    }

    @Test
    public void loginValidDataAfterMultipleLoginLockedOutUser() {
        int randomIterations = getRandomIterations();
        for (int i = 0; i <= randomIterations; i++) {
            loginPage.logIn(LOCKED_OUT_USER_USERNAME, VALID_PASSWORD);
            Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
            Assert.assertEquals(loginPage.getEpicSadFaceMessage().getText(), LOCKED_OUT_USER_LOGIN_MESSAGE);
            loginPage.clearLoginInputFields();
        }
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().endsWith(INVENTORY_PAGE_URL_ENDING));
        burgerMenuPage.burgerMenuButtonClick();
        Assert.assertEquals(burgerMenuPage.getLogoutButton().getText(), LOGOUT_BUTTON_EXPECTED_TEXT);
    }

    private int getRandomIterations() {
        return (int) (Math.random() * 10);
    }
}
