package pageObject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;
import pageUIs.MyAccountPageUI;
import pageUIs.RegisterPageUI;

//chua nhung ham thao tac voi page do
public class MyAccountPageObject extends BasePage {
    private WebDriver driver;

    public MyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCustomerInfoLink() {
        waitForElementClickable(driver, MyAccountPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, MyAccountPageUI.CUSTOMER_INFO_LINK);
    }
}
