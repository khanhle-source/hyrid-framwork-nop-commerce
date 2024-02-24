package com.nopcommerce.user;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic19_ParallelClass_04 extends BaseTest {
    WebDriver driver;
    @Parameters({"browser", "environment"})
    @BeforeClass
    public void BeforeClass (String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);
    }

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


}
