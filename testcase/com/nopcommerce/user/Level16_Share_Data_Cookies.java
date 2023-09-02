package com.nopcommerce.user;

import common.BaseTest;
import common.Common_Register_Cookie;
import common.Common_Register_User;
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
public class Level16_Share_Data_Cookies extends BaseTest {
    WebDriver driver;


    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;

    private String password;
    private String emailAddress;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);

        homePage = new UserHomePageObject(driver);
        loginPage = new UserLoginPageObject(driver);

        emailAddress = Common_Register_User.emailAddress;
        password = Common_Register_User.password;

        loginPage = homePage.clicktoLoginLink();
        loginPage.setCookies(driver, Common_Register_Cookie.LoggedCookies);

        loginPage.refreshPage(driver);
    }

    @Test
    public void tc_01_Search () {

    }

    @Test
    public void tc_02_Add_To_Cart () {

    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}