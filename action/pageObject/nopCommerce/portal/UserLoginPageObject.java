package pageObject.nopCommerce.portal;

import common.BasePage;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import nopCommerce.userpageUIs.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    private WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //Action
    public UserHomePageObject clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }
    public String getErrorMessgeAtEmailTextbox () {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE );
        return getElementText (driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
    }
    public void inputToEmailTextbox (String validEmail) {
        waitForElementVisible (driver, UserLoginPageUI.EMAIL_TEXTBOX );
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, validEmail);
    }

    public String getErrorMessageUnsuccessful () {
        waitForElementVisible(driver, UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE );
        return getElementText (driver, UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
    }

    public void inputToPasswordTextbox (String password) {
        waitForElementVisible (driver, UserLoginPageUI.PASSWORD_TEXTBOX );
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public UserHomePageObject loginAsUser (String emailAddress, String password) {
        inputToEmailTextbox(emailAddress);
        inputToPasswordTextbox(password);
        return clickToLoginButton();
    }

}
