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

import java.util.ArrayList;
import java.util.List;

public class Level10_DataTable extends BaseTest {
    WebDriver driver;
    HomepageObject homePage;
    String projectPath = System.getProperty("user.dir");
    List<String> actualAllCountryValues = new ArrayList<String>();
    List<String> expectedAllCountryValues = new ArrayList<String>();


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


    @Test
    public void tc_02_getAllData() {
        // return all country (dupplicate)
        System.out.println(homePage.getCountryEachRowOfAllPage ());

        // return all value in table
        System.out.println(homePage.getAllDataOfAllTable ());

        //verify 2 list
        actualAllCountryValues = homePage.getAllDataOfAllTable();
        Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
    }
*/
    @Test
    public void Table_04_Enter_To_Textbox_At_Any_Row () {
        // Value de nhap lieu - tham so 1
        // Row number: tai row nao
        // Ex: nhap vao textbox tai dong so 3, 5, 2
        // Column name: Album / Artist/ Year/ Price
        homePage.enterToTextboxByColumnNameAtRowNumber ("Company", "1", "Hubble Ltd");

        homePage.enterToTextboxByColumnNameAtRowNumber ("Company", "2", "Net Company");
    }
    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}