package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utility.Utility;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected final String BASE_URL = "https://www.saucedemo.com/";
    protected final String VALID_PASSWORD = "secret_sauce";
    protected final String[] VALID_USERNAMES = {"standard_user", "problem_user", "performance_glitch_user"};
    protected final String INVENTORY_PAGE_URL_ENDING = "/inventory.html";
    protected final String STANDARD_USER_LOGIN = "standard_user";
    protected Utility utility;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(50));
        utility = new Utility();
    }

    @BeforeMethod
    public void beforeMethod() {
        driverGet(BASE_URL);
    }

    @AfterClass
    public void afterClass() {
        driverQuit();
    }


    protected String getRandomValidUsername() {
        return VALID_USERNAMES[utility.getRandomArrayIndex(VALID_USERNAMES)];
    }

    public void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    public void assertTrue(String urlEnding) {
        Assert.assertTrue(driverGetCurrentURL().endsWith(urlEnding));
    }

    public void assertEquals(String actual, String excpected) {
        Assert.assertEquals(actual, excpected);
    }

    public void driverGet(String url) {
        driver.get(url);
    }

    public void driverFullscreen() {
        driver.manage().window().fullscreen();
    }

    public String driverGetCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void driverQuit() {
        driver.quit();
    }
}
