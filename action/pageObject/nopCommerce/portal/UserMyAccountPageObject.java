package pageObject.nopCommerce.portal;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import nopCommerce.userpageUIs.UserMyAccountPageUI;

//chua nhung ham thao tac voi page do
public class UserMyAccountPageObject extends BasePage {
    private WebDriver driver;

    public UserMyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCustomerInfoLink() {
        waitForElementClickable(driver, UserMyAccountPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, UserMyAccountPageUI.CUSTOMER_INFO_LINK);
    }
}
