package common;

import org.openqa.selenium.WebDriver;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.portal.*;

public class PageGeneratorManager {
    public static UserHomePageObject getHomePage (WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserRegisterPageObject getRegisterPage (WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserLoginPageObject getLoginPage (WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserMyAccountPageObject getMyAccountPage (WebDriver driver) {
        return new UserMyAccountPageObject(driver);
    }

    public static UserAddressPageObject getAddressPage (WebDriver driver) {
        return new UserAddressPageObject(driver);
    }

    public static UserMyProductReviewPageObject getMyProductReview (WebDriver driver) {
        return new UserMyProductReviewPageObject(driver);
    }

    public static UserMyRewardPointPageObject getMyRewardPoint (WebDriver driver) {
        return new UserMyRewardPointPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }
    public static AdminDashboardPageObject getAdminDashboard (WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }
}
