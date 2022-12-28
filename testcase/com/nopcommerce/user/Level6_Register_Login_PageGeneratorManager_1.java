package com.nopcommerce.user;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.HomePageObject;
import pageFactory.LoginPageObject;
import pageFactory.RegisterPageObject;

import java.util.Random;

//1 class ke thua duoc 1 class, nhung 1 class ke thua dc nhieu interface
public class Level6_Register_Login_PageGeneratorManager_1 extends BaseTest {
    WebDriver driver;
    String emailAddress;

    String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private String firstName;
    private String lastName;
    private String password;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);

        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);

        firstName = "khanh";
        lastName = "le";
        password ="123456";
        emailAddress = "afc" + random() + "@email.vn";

    }
    @Test
    public void tc_01_Register_Empty_Data() {
        System.out.println("Home Page - Step 01: Click to Register Link");
        homePage.clickToRegisterLink();

        System.out.println("Register Page - Step 02: Click to Register Button");
        registerPage.clickToRegisterButton();

        System.out.println("Register Page - Step 03: Verify error message");
        Assert.assertEquals(registerPage.getErrorMessageAtFirstName(), "First name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtLastName(), "Last name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtEmail(), "Email is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtPassword(), "Password is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPassword(), "Password is required.");

    }

    @Test
    public void tc_02_Register_Invalid_Email() {
        System.out.println("Home Page - Step 01: Click to Register Link");
        homePage.clickToRegisterLink();

        System.out.println("Register Page - Step 02: Input to required fields");
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox("123@456@#$%");
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register Page - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register Page - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtEmail(), "Wrong email");
    }

    @Test
    public void tc_03_Register_Success () {
        System.out.println("Home Page - Step 01: Click to Register link");
        homePage.clickToRegisterLink();

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
    public void tc_04_Register_Exist_Email() {
        System.out.println("Home Page - Step 01: Click to Register link");
        homePage.clickToRegisterLink();

        System.out.println("Register Page - Step 02: Input to required fields");
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register Page - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register Page - Step 04: Verify error existing message displayed");
        Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
    }

    @Test
    public void tc_05_Register_Password_Less_Than_6_Chars() {
        System.out.println("Home Page - Step 01: Click to Register link");
        homePage.clickToRegisterLink();

        System.out.println("Register Page - Step 02: Input to required fields");
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("123");
        registerPage.inputToConfirmPasswordTextbox("123");

        System.out.println("Register Page - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register Page - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtPassword(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void tc_06_Register_Invalid_Confirm_Password () {
        System.out.println("Home Page - Step 01: Click to Register link");
        homePage.clickToRegisterLink();

        System.out.println("Register Page - Step 02: Input to required fields");
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("123456");
        registerPage.inputToConfirmPasswordTextbox("1234566");

        System.out.println("Register Page - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register Page - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPassword(), "The password and confirmation password do not match.");
    }

    @Test
    public void tc_07_Login_Empty_Data () {
        homePage.clickToLoginLink();
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessgeAtEmailTextbox(), "Please enter your email");
    }


    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}