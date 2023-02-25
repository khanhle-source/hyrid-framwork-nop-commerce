package com.jquery;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.jQuery.UploadFiles.HomepageObject;
import pageObject.jQuery.UploadFiles.PageGeneratorManager;


public class Level11_UploadFile extends BaseTest {
    String[] fileNames = {"Java.png"};
    WebDriver driver;
    private HomepageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String getURL) {
        driver = getBrowerDriver(browserName, getURL);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    // khi upload file phai nho tim dung the input co type = file, neu khong se khong upload duoc
    // the input nay co the bi an di
    @Test
    public void Upload_01_One_File_Per_Time () {
        homePage.uploadMultipleFiles(driver, "Java.png" );
        Assert.assertTrue(homePage.isFileLoadedByName("Java.png"));
        homePage.clickToStartButton();
        Assert.assertTrue(homePage.isFileUploadedByName("Java.png"));
        Assert.assertTrue(homePage.isFileUploadedByImage("Java.png"));


    }

    @Test
    public void Upload_02_Multiple_File_Per_Time () {

    }
    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}