package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckOutYourInformationPage extends BasePage {
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    @FindBy(id = "first-name")
    private WebElement firstNameInputField;
    @FindBy(id = "last-name")
    private WebElement lastNameInputField;
    @FindBy(id = "postal-code")
    private WebElement zipPostalCodeInputField;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(className = "error-message-container")
    private WebElement errorMessageFirstNameIsRequired;
    @FindBys({@FindBy(className = "error_icon")})
    private List<WebElement> errorButtonList;
    @FindBy(className = "error-button")
    private WebElement errorButton;

    public WebElement getErrorButton() {
        return errorButton;
    }

    public WebElement getErrorMessageFirstNameIsRequired() {
        return errorMessageFirstNameIsRequired;
    }

    public CheckOutYourInformationPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getContinueButton() {
        return continueButton;
    }

    public void insertDataInInputFields(String firstName, String lastName, String zipPostalCode) {
        sendKeysToElement(firstNameInputField, firstName);
        sendKeysToElement(lastNameInputField, lastName);
        sendKeysToElement(zipPostalCodeInputField, zipPostalCode);
    }

    public void insertAllFakeDataInIputFields() {
        insertDataInInputFields(utility.getFakeFirstName(), utility.getFakeLastName(), utility.getFakeZipPostalCode());
    }

    public void insterFakeFirstNameLastNameInInputFields() {
        insertDataInInputFields(utility.getFakeFirstName(), utility.getFakeLastName(), "");
    }

    public void insertFakeFirstNameZipCodeInIputFields() {
        insertDataInInputFields(utility.getFakeFirstName(), "", utility.getFakeZipPostalCode());
    }

    public void insertFakeLastNameZipCodeInIputFields() {
        insertDataInInputFields("", utility.getFakeLastName(), utility.getFakeZipPostalCode());
    }

    public void continueShopingWithAllData() {
        insertAllFakeDataInIputFields();
        clickOnWebElement(continueButton);
    }

    public void continueShopingWithFakeFirstNameLastName() {
        insterFakeFirstNameLastNameInInputFields();
        clickOnWebElement(continueButton);
    }

    public void continueShopingWithFakeFirstNameZipCode() {
        insertFakeFirstNameZipCodeInIputFields();
        clickOnWebElement(continueButton);
    }

    public void continueShoppingWithFakeLastNameZipCode() {
        insertFakeLastNameZipCodeInIputFields();
        clickOnWebElement(continueButton);
    }

    public void continueShoppingWithOnlyFakeFirstName() {
        sendKeysToElement(firstNameInputField, utility.getFakeFirstName());
        clickOnWebElement(continueButton);
    }

    public void continueShoppingWithOnlyFakeLastName() {
        sendKeysToElement(lastNameInputField, utility.getFakeLastName());
        clickOnWebElement(continueButton);
    }

    public void continueShoppingWithOnlyZipCode() {
        sendKeysToElement(zipPostalCodeInputField, utility.getFakeZipPostalCode());
        clickOnWebElement(continueButton);
    }

    public boolean checkErrorButtonListSize() {
        if (errorButtonList.size() == 3) {
            return true;
        }
        return false;
    }

    public boolean errorButtonsListButtonsAreDisplayed() {
        for (int i = 0; i < errorButtonList.size(); i++) {
            if (!webElementIsDisplayed(errorButtonList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
