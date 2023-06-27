package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutOverviewPage extends BasePage {
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckOutOverviewPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getFinishButton() {
        return finishButton;
    }
}
