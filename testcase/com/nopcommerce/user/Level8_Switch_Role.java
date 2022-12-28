package com.nopcommerce.user;

import common.BaseTest;
import common.GlobalConstants;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.portal.*;

public class Level8_Switch_Role extends BaseTest {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserMyAccountPageObject myAccountPage;
    private UserAddressPageObject myAddressPage;
    private UserMyProductReviewPageObject myProductReviewPage;
    private UserMyRewardPointPageObject myRewardPointPage;

    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;

    String emailAddress;
    private String firstName;
    private String lastName;
    private String password;

    private String adminEmailAdress;
    private String adminPassword;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowerDriver(browserName,environmentName);

        homePage = PageGeneratorManager.getHomePage(driver);

        firstName = "khanh";
        lastName = "le";
        password ="123456";
        emailAddress = "afc" + random() + "@email.vn";
        adminEmailAdress = "admin@yourstore.com";
        adminPassword = "admin";
    }



    @Test
    public void TC_01_Register_Success () {
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

        System.out.println("Register Page - Step 05: Click to Logout link");
        registerPage.clickToLogoutLink();
    }


    @Test
    public void TC_02_Role_User_To_Admin () {

        // login as user
        loginPage = homePage.clicktoLoginLink();
        homePage = loginPage.loginAsUser(emailAddress,password);
        myAccountPage = homePage.clickMyAccountLink();
        myAccountPage.clickCustomerInfoLink();

        //user logout
        homePage = myAccountPage.clickToLogoutLinkAtUserPage(driver);

        // login as admin role
        homePage.getURl(driver, GlobalConstants.ADM_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAdress,adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardDisplay());

        //admin logout
        adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
    }

    @Test
    public void TC_03_Role_Admin_To_User() {
        // login as admin role
        adminLoginPage.getURl(driver, GlobalConstants.ADM_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAdress,adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardDisplay());


        // login as user
        loginPage.getURl(driver, GlobalConstants.PORTAL_PAGE_URL);
        loginPage = homePage.clicktoLoginLink();
       homePage = loginPage.loginAsUser(emailAddress,password);

    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}