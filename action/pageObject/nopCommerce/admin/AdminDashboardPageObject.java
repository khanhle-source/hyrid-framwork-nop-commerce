package pageObject.nopCommerce.admin;

import nopCommerce.adminpageUIs.AdminDashboardPageUI;
import common.BasePage;
import org.openqa.selenium.WebDriver;

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
