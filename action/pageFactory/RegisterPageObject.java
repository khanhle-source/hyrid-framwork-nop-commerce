package pageFactory;

import common.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePageFactory {
    private WebDriver driver;
    public RegisterPageObject (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(id = "FirstName-error")
    private WebElement firstNameErrorMessage;

    @FindBy(id = "LastName-error")
    private WebElement lastNameErrorMessage;

    @FindBy(id = "Email-error")
    private WebElement emailErrorMessage;

    @FindBy(id = "Password-error")
    private WebElement passwordErrorMessage;

    @FindBy(id = "ConfirmPassword-error")
    private WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement registerSuccessMessage;

    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[contains(@class,'message-error')]//li")
    private WebElement existingEmailErrorMessage;


    // Action
    public void clickToRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(driver, registerButton);
    }

    public String getErrorMessageAtFirstName() {
        waitForElementVisible(driver, firstNameErrorMessage);
        return getElementText(driver, firstNameErrorMessage);
    }

    public String getErrorMessageAtLastName() {
        waitForElementVisible(driver, lastNameErrorMessage);
        return getElementText(driver, lastNameErrorMessage);

    }

    public String getErrorMessageAtEmail() {
        waitForElementVisible(driver, emailErrorMessage);
        return getElementText(driver, emailErrorMessage);

    }

    public String getErrorMessageAtPassword() {
        waitForElementVisible(driver, passwordErrorMessage);
        return getElementText(driver, passwordErrorMessage);
    }

    public String getErrorMessageAtConfirmPassword() {
        waitForElementVisible(driver, confirmPasswordErrorMessage);
        return getElementText(driver, confirmPasswordErrorMessage);
    }

    public void inputToFirstNameTextbox(String firstname) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(driver, firstNameTextbox, firstname);
    }

    public void inputToLastNameTextbox(String lastname) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(driver, lastNameTextbox, lastname);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(driver, emailTextbox, email);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(driver, passwordTextbox, password);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToElement(driver, confirmPasswordTextbox, confirmPassword);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getElementText(driver, registerSuccessMessage);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driver, logoutLink);
        clickToElement(driver, logoutLink);
    }

    public String getErrorExistingEmailMessage() {
        waitForElementVisible(driver, existingEmailErrorMessage);
        return getElementText(driver, existingEmailErrorMessage);
    }

}