package com.nopcommerce.user;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import common.BaseTest;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic19_ParallelClass_01 extends BaseTest {
    WebDriver driver;
    @Parameters({"browser", "environment"})

    // parallel class thi dung BeforeClass > se chay doc lap tung class
    // parallel method (tung test case) thi dung BeforeMethod > se chay doc lap tung method (test case)
    @BeforeClass
    public void BeforeClass (String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);
    }
// NOTE: neu 2 test case doc lap (eg. Verify login fail with missing email and verify login fail with missingg password
// Thi phai khoi tao lai page o tcs 2 (loginPage = homePage.clickToMyAccLink...) boi vi co the tc1 bi fail thi tc2 van chay duoc
    @Test
    public void TC_01_New_Account () {

    }

    @Test
    public void TC_02_Edit_Account () {

    }

    @Test
    public void TC_03_View_Account () {

    }

    @Test
    public void TC_04_Search_Account () {

    }

    @Test
    public void TC_05_Delete_Account () {

    }

    // parallel class thi dung AfterClass > se chay doc lap tung class > nghia la se khoi tao driver roi quit driver  cho tung class
    // parallel method (tung test case) thi dung AfterMethod > se chay doc lap tung method (test case) > nghia la se khoi tao driver roi quit driver  cho tung method
    @AfterClass
    public void afterClass () {
        driver.quit();
    }


}
