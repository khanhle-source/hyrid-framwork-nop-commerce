package pageFactory;

import common.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;
    public LoginPageObject (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    //page UI
    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//button[contains(@class,'login-button')]")
    private WebElement loginButton;

    @FindBy(id = "Email-error")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[contains(@class, 'valida-summary-errors')]")
    private WebElement unsuccessfulErrorMessage;

    //Action
    public void clickToLoginButton() {
        waitForElementClickable(driver,loginButton);
        clickToElement(driver, loginButton);
    }
    public String getErrorMessgeAtEmailTextbox () {
        waitForElementVisible(driver, emailErrorMessage );
        return getElementText (driver, emailErrorMessage);
    }
    public void inputToEmailTextbox (String invalidEmail) {
        waitForElementVisible (driver,emailTextbox );
        sendKeyToElement(driver,emailTextbox, invalidEmail);
    }

    public String getErrorMessageUnsuccessful () {
        waitForElementVisible(driver, unsuccessfulErrorMessage );
        return getElementText (driver, unsuccessfulErrorMessage);
    }

    public void inputToPasswordTextbox (String password) {
        waitForElementVisible (driver,passwordTextbox );
        sendKeyToElement(driver,passwordTextbox,password);
    }

}
