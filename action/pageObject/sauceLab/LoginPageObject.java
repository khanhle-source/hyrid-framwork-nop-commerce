package pageObject.sauceLab;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sauceLab.pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void entertoUsernameTextbox (String username) {
        waitForElementVisible(driver,LoginPageUI.USERNAME_TEXTBOX );
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);

    }

    public void entertoPasswordTextbox (String password) {
        waitForElementVisible(driver,LoginPageUI.PASSWORD_TEXTBOX );
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public ProductPageObject clickToLoginButton () {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getProductPage(driver);
    }
}
