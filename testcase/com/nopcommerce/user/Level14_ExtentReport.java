package com.nopcommerce.user;

import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.nopCommerce.portal.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

// apply reportNG listener chi cho class nay
// con neu muon apply cho nhieu class thi config o file xml


public class Level14_ExtentReport extends BaseTest {
    WebDriver driver;
    String emailAddress;

    String projectPath = System.getProperty("user.dir");
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserMyAccountPageObject myAccountPage;
    private UserAddressPageObject myAddressPage;
    private UserMyProductReviewPageObject myProductReviewPage;
    private UserMyRewardPointPageObject myRewardPointPage;

    private String firstName;
    private String lastName;
    private String password;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);

        homePage = PageGeneratorManager.getHomePage(driver);

        firstName = "khanh";
        lastName = "le";
        password ="123456";
        emailAddress = "afc" + random() + "@email.vn";
    }

    @Test
    public void User_01_Register(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
        registerPage = homePage.clicktoRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
        registerPage.inputToFirstNameTextbox(firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
        registerPage.inputToLastNameTextbox(lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
        registerPage.inputToEmailTextbox(emailAddress);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
        registerPage.inputToPasswordTextbox(password);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
        registerPage.inputToConfirmPasswordTextbox(password);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
        registerPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login to system successfully");
        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.clicktoLoginLink();
    }


    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}