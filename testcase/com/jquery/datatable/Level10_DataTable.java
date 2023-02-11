package com.jquery.datatable;

import common.BaseTest;
import pageObject.jQuery.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.jQuery.HomepageObject;
import pageObject.nopCommerce.portal.*;

public class Level10_DataTable extends BaseTest {
    WebDriver driver;
    HomepageObject homePage;
    String projectPath = System.getProperty("user.dir");

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String getURL) {
        driver = getBrowerDriver(browserName, getURL);
        homePage = PageGeneratorManager.getHomePage(driver);

    }
/*
   @Test
    public void tc_01_Paging()
    {
        //action
        homePage.openPagingByPageNumber("10");
        //verify
        homePage.isPageNumberActive("10");
        //wait
        homePage.sleepInSecond(3);
        //action
        homePage.openPagingByPageNumber("3");
        //verify
        homePage.isPageNumberActive("3");
        //wait
        homePage.sleepInSecond(3);

    }


    @Test
    public void tc_02_EnterIntoColumn()
    {
        homePage.refreshPage(driver);
        homePage.enterToColumn("Country", "Serbia");
        homePage.sleepInSecond(3);
        homePage.enterToColumn("Females", "53300");
        homePage.sleepInSecond(3);
    }
*/

    @Test
    public void tc_02_getAllData() {
        // return all country (dupplicate)
        System.out.println(homePage.getCountryEachRowOfAllPage ());

        // return all value in table
        System.out.println(homePage.getAllDataOfAllTable ());

    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}