package com.nopcommerce.user;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.sauceLab.LoginPageObject;
import pageObject.sauceLab.PageGeneratorManager;
import pageObject.sauceLab.ProductPageObject;

public class Topic20_Sort_Data_Ascending_Descending extends BaseTest {

    WebDriver driver;
    LoginPageObject loginPage;
    ProductPageObject productPage;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void BeforeClass (String browserName, String environmentName) {
        driver = getBrowerDriver(browserName, environmentName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.entertoUsernameTextbox ("standard_user");
        loginPage.entertoPasswordTextbox ("secret_sauce");
        productPage = loginPage.clickToLoginButton();

    }

    @Test
    public void TC_01_Sort_Name () {
        //Asc
        productPage.selectItemInProductSortDropdown ("az");
        productPage.sleepInSecond(3);
        // ham verify sort
        Assert.assertTrue(productPage.isProductNameSortByAsc());
        //ham verify sort by lamda
        productPage.isProductNameSortByAscLamda();

        //Des
        productPage.selectItemInProductSortDropdown ("za");
        productPage.sleepInSecond(3);
        Assert.assertTrue(productPage.isProductNameSortByDes());
        productPage.isProductNameSortByDesLamda();
    }

    @Test
    public void TC_02_Sort_Price () {
        //Asc
        productPage.selectItemInProductSortDropdown ("lohi");
        productPage.sleepInSecond(3);
        Assert.assertTrue(productPage.isProductPriceSortByAsc());

        //Des
        productPage.selectItemInProductSortDropdown ("hilo");
        productPage.sleepInSecond(3);
        Assert.assertTrue(productPage.isProductPriceSortByDes());
    }

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver(driver);
    }


}
