package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BurgerMenuPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;
    private BurgerMenuPage burgerMenuPage;
    private final String LOCKED_OUT_USER_USERNAME = "locked_out_user";
    private String fakeUsername;
    private String fakePassword;
    private final String INVALID_DATA_LOGIN_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    private final String LOCKED_OUT_USER_LOGIN_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        burgerMenuPage = new BurgerMenuPage(driver, webDriverWait);
        fakeUsername = utility.getFakeUsername();
        fakePassword = utility.getFakePassword();
    }

    @Test
    public void loginValidData() {
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        assertTrue(INVENTORY_PAGE_URL_ENDING);
        burgerMenuPage.burgerMenuButtonClick();
        assertEquals(burgerMenuPage.getWebElementText(burgerMenuPage.getLogoutButton()), burgerMenuPage.getLOGOUT_BUTTON_EXPECTED_TEXT());
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
        assertEquals(driverGetCurrentURL(), BASE_URL);
        assertEquals(loginPage.getWebElementText(loginPage.getEpicSadFaceMessage()), INVALID_DATA_LOGIN_MESSAGE);
    }

    @Test
    public void loginLockedOutUser() {
        loginPage.logIn(LOCKED_OUT_USER_USERNAME, VALID_PASSWORD);
        assertEquals(driverGetCurrentURL(), BASE_URL);
        assertEquals(loginPage.getWebElementText(loginPage.getEpicSadFaceMessage()), LOCKED_OUT_USER_LOGIN_MESSAGE);
    }

    @Test
    public void logout() {
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        burgerMenuPage.logOut();
        assertEquals(driverGetCurrentURL(), BASE_URL);
    }

    @Test(dataProvider = "invalidLoginData")
    public void loginValidDataAfterMultipleLoginInvalidData(String username, String password) {
        int randomIterations = getRandomIterations();
        for (int i = 0; i <= randomIterations; i++) {
            loginPage.logIn(username, password);
            assertEquals(driverGetCurrentURL(), BASE_URL);
            assertEquals(loginPage.getWebElementText(loginPage.getEpicSadFaceMessage()), INVALID_DATA_LOGIN_MESSAGE);
            loginPage.clearLoginInputFields();
        }
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        assertTrue(INVENTORY_PAGE_URL_ENDING);
        burgerMenuPage.burgerMenuButtonClick();
        assertEquals(burgerMenuPage.getWebElementText(burgerMenuPage.getLogoutButton()), burgerMenuPage.getLOGOUT_BUTTON_EXPECTED_TEXT());
    }

    @Test
    public void loginValidDataAfterMultipleLoginLockedOutUser() {
        int randomIterations = getRandomIterations();
        for (int i = 0; i <= randomIterations; i++) {
            loginPage.logIn(LOCKED_OUT_USER_USERNAME, VALID_PASSWORD);
            assertEquals(driverGetCurrentURL(), BASE_URL);
            assertEquals(loginPage.getWebElementText(loginPage.getEpicSadFaceMessage()), LOCKED_OUT_USER_LOGIN_MESSAGE);
            loginPage.clearLoginInputFields();
        }
        loginPage.logIn(getRandomValidUsername(), VALID_PASSWORD);
        assertTrue(INVENTORY_PAGE_URL_ENDING);
        burgerMenuPage.burgerMenuButtonClick();
        assertEquals(burgerMenuPage.getWebElementText(burgerMenuPage.getLogoutButton()), burgerMenuPage.getLOGOUT_BUTTON_EXPECTED_TEXT());
    }

    private int getRandomIterations() {
        return (int) (Math.random() * 10);
    }
}
