package pageObject.sauceLab;

import org.openqa.selenium.WebDriver;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.portal.*;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPage (WebDriver driver) {
        return new LoginPageObject(driver);
    }
    public static ProductPageObject getProductPage (WebDriver driver) {
        return new ProductPageObject(driver);
    }

}
