package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected final String BASE_URL = "https://www.saucedemo.com/";
    protected final String VALID_PASSWORD = "secret_sauce";
    protected final String[] VALID_USERNAMES = {"standard_user", "problem_user", "performance_glitch_user"};

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    protected int getRandomIndex() {
        int index = (int) (Math.random() * VALID_USERNAMES.length);
        if (index == VALID_USERNAMES.length) {
            return (int) (Math.random() * (VALID_USERNAMES.length - 1));
        } else {
            return index;
        }
    }

    protected String getRandomValidUsername() {
        return VALID_USERNAMES[getRandomIndex()];
    }
}
