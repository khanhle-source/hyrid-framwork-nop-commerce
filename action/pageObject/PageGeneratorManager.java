package pageObject;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static HomePageObject getHomePage (WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static RegisterPageObject getRegisterPage (WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static LoginPageObject getLoginPage (WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static MyAccountPageObject getMyAccountPage (WebDriver driver) {
        return new MyAccountPageObject(driver);
    }

    public static AddressPageObject getAddressPage (WebDriver driver) {
        return new AddressPageObject(driver);
    }

    public static MyProductReviewPageObject getMyProductReview (WebDriver driver) {
        return new MyProductReviewPageObject(driver);
    }

    public static MyRewardPointPageObject getMyRewardPoint (WebDriver driver) {
        return new MyRewardPointPageObject(driver);
    }
}
