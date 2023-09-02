package common;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLoginPageObject;
import pageObject.nopCommerce.portal.UserMyAccountPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;

import java.util.Random;

//1 class ke thua duoc 1 class, nhung 1 class ke thua dc nhieu interface
public class Common_Register_User extends BaseTest {
    WebDriver driver;
    public static String emailAddress;
    public static String password;

    private String firstName;
    private String lastName;


    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);

        homePage = new UserHomePageObject(driver);
        registerPage = new UserRegisterPageObject(driver);

        firstName = "khanh";
        lastName = "le";
        password ="123456";
        emailAddress = "afc" + random() + "@email.vn";

    }

    @Test
    public void tc_01_Register_Success () {
        homePage.clicktoRegisterLink();
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}