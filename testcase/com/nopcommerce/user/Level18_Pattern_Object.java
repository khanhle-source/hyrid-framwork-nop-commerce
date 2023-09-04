package com.nopcommerce.user;

import common.BaseTest;
import common.PageGeneratorManager;
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


public class Level18_Pattern_Object extends BaseTest {
    WebDriver driver;
    String emailAddress;
    String date, month, year;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserMyAccountPageObject myAccountPage;
    private String firstName;
    private String lastName;
    private String password;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);

        homePage = new UserHomePageObject(driver);
        registerPage = new UserRegisterPageObject(driver);
        loginPage = new UserLoginPageObject(driver);

        firstName = "khanh";
        lastName = "le";
        password ="123456";
        emailAddress = "afc" + random() + "@email.vn";
        date = "9";
        month = "3";
        year = "1994";

    }

    @Test
    public void tc_01_Register_Success () {
        System.out.println("Home Page - Step 01: Click to Register link");
        homePage.clicktoRegisterLink();

        System.out.println("Register Page - Step 02: Input to required fields");

        registerPage.selectRadioButtonByName(driver, "Male");
        registerPage.inputToTextboxByID(driver, firstName, "FirstName");
        registerPage.inputToTextboxByID(driver, lastName, "LastName");

        registerPage.selectToDropdownByName(driver, "DateOfBirthDay", date);
        registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
        registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);
        registerPage.inputToTextboxByID(driver, emailAddress, "Email");
        registerPage.inputToTextboxByID(driver, password, "Password");
        registerPage.inputToTextboxByID(driver, password, "ConfirmPassword");

        System.out.println("Register Page - Step 03: Click to Register button");
        registerPage.clickToButtonByText(driver, "Register");

        System.out.println("Register Page - Step 04: Verify success message displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        System.out.println("Register Page - Step 05: Click to Logout link");
        homePage = registerPage.clickToLogoutLink();
    }

    @Test
    public void tc_02_Login_Successful () {
        loginPage = homePage.clicktoLoginLink();

        loginPage.inputToTextboxByID(driver, emailAddress, "Email");
        loginPage.inputToTextboxByID(driver, password, "Password");
        loginPage.clickToButtonByText(driver, "Log in");
        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Test
    public void tc_03_My_Account () {
        System.out.println("My account - Step 01: Navigate to My Account page");
        myAccountPage = homePage.clickMyAccountLink();

        System.out.println("My Account - Step 02: Verify Customer Infor page is displayed");

        System.out.println("My Account - Step 03: Verify Firstname info is correctly");
        Assert.assertEquals(myAccountPage.getTextboxValueByID(driver, "FirstName"), firstName);

        System.out.println("My Account - Step 04: Verify Lastname info is correctly");
        Assert.assertEquals(myAccountPage.getTextboxValueByID(driver, "LastName"), lastName);

        System.out.println("My Account - Step 05: Verify Email info is correctly");
        Assert.assertEquals(myAccountPage.getTextboxValueByID(driver, "Email"), emailAddress);

    }
    @AfterClass (alwaysRun = true)
    public void afterClass () {
        closeBrowserDriver(driver);
    }
}