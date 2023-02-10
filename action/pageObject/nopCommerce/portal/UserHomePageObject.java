package pageObject.nopCommerce.portal;

import common.BasePage;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import nopCommerce.userpageUIs.UserHomePageUI;

public class UserHomePageObject extends BasePage {
    private WebDriver driver;

    //Hàm khởi tạo = Contructor
    // 1 - Cùng tên với class
    // 2 - Không có kiểu dữ liệu trả về
    // 3 - Có access modifier
    // 4 - Khi Class được gọi thì sẽ khởi tạo hàm này đầu tiên
    // 5 - 1 Class có thể có nhiều hàm khởi tạo
    // 6 - Nếu không khai báo hàm khởi tạo, thì trình biên dịch sẽ tự tạo hàm khởi tạo default
    // 7 - Trong TH bien global va local cung ten, dung this. de lay ra bien global
    // 8 - Trong 1 class có nhiều biến cùng tên, thì đó là tính Đa Hình
    // 9 - Cho phép cùng tên/ khác kiểu dữ liệu tham số
    // 10 - Cho phép cùng tên/ khác số lượng tham số

    // Biến toàn cục: sinh ra trong 1 class
    // Biến cục bộ: sinh ra trong 1 hàm (tham số/ phần thân/ block code)
 public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPageObject clicktoRegisterLink() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        //2
        //return new RegisterPageObject(driver);
        //3
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public UserLoginPageObject clicktoLoginLink() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }

    public UserMyAccountPageObject clickMyAccountLink () {
        waitForElementClickable(driver, UserHomePageUI.MYACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MYACCOUNT_LINK);
        return PageGeneratorManager.getMyAccountPage(driver);
    }
}
