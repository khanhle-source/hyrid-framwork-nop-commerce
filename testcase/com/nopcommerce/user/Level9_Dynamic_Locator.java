package com.nopcommerce.user;

import common.BaseTest;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.nopCommerce.portal.*;

public class Level9_Dynamic_Locator extends BaseTest {
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
    public void tc_03_Register_Success () {
        System.out.println("Home Page - Step 01: Click to Register link");
        registerPage = homePage.clicktoRegisterLink();

        System.out.println("Register Page - Step 02: Input to required fields");
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register Page - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register Page - Step 04: Verify success message displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        //System.out.println("Register Page - Step 05: Click to Logout link");
        //registerPage.clickToLogoutLink();
    }


    @Test
    public void tc_08_Login_Successful () {
        loginPage = homePage.clicktoLoginLink();

        loginPage.inputToEmailTextbox(emailAddress);
        loginPage.inputToPasswordTextbox(password);
        homePage = loginPage.clickToLoginButton();

        myAccountPage = homePage.clickMyAccountLink();
        myAccountPage.clickCustomerInfoLink();
    }

    @Test
    public void tc_09_Switch_Page () {
        //tu my account page sang my address page
        myAddressPage = myAccountPage.openAddressPage(driver);

        // tu my address page sang my product review page
        myProductReviewPage = myAddressPage.openMyProductReviewPage(driver);

        //tu my product review page sang my reward point page
        myRewardPointPage = myProductReviewPage.openMyRewardPointPage(driver);

        // tu my reward point page sang my product review page
        myProductReviewPage = myRewardPointPage.openMyProductReviewPage(driver);

    }

    // Level 09: Dynamic Locator (lay tu level 07: Switch page)
    @Test
    public void tc_10_Dynamic_Locator () {
        // My Product review > reward Point
        myRewardPointPage = (UserMyRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");

        // Reward point > Address
        myAddressPage = (UserAddressPageObject) myRewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");

        // Address > My Product Review
        myProductReviewPage = (UserMyProductReviewPageObject) myAddressPage.openPagesAtMyAccountByName(driver, "My product reviews");


    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}