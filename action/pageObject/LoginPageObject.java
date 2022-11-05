package pageObject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject (WebDriver driver) {
        this.driver = driver;
    }

    //Action
    public HomePageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }
    public String getErrorMessgeAtEmailTextbox () {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE );
        return getElementText (driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
    }
    public void inputToEmailTextbox (String invalidEmail) {
        waitForElementVisible (driver,LoginPageUI.EMAIL_TEXTBOX );
        sendKeyToElement(driver,LoginPageUI.EMAIL_TEXTBOX, invalidEmail);
    }

    public String getErrorMessageUnsuccessful () {
        waitForElementVisible(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE );
        return getElementText (driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
    }

    public void inputToPasswordTextbox (String password) {
        waitForElementVisible (driver, LoginPageUI.PASSWORD_TEXTBOX );
        sendKeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX,password);
    }


}
