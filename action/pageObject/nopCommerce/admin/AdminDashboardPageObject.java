package pageObject.nopCommerce.admin;

import adminpageUIs.AdminDashboardPageUI;
import common.BasePage;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageObject.nopCommerce.portal.UserHomePageObject;
import userpageUIs.UserLoginPageUI;

public class AdminDashboardPageObject extends BasePage {
    private WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardDisplay () {
        waitForElementVisible(driver,AdminDashboardPageUI.DASHBOARD_HEADER );
        return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
    }
}
