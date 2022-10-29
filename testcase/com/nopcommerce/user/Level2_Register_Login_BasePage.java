package com.nopcommerce.user;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level2_Register_Login_BasePage {
    /*
    WebDriver driver;
    String emailAddress;

    //Declare
    BasePage basePage;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
        driver = new FirefoxDriver();

        //Initial
        basePage = BasePage.getBasePageObject();

        emailAddress = "afc" + random() + "@email.vn";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void tc_01_Register_Empty_Data() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");

    }

    @Test
    public void tc_02_Register_Successful () {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Khanh");
        basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Le");
        basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");

        basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
        basePage.clickToElement(driver, "//a[@class='ico-logout']");
        
    }

    public int random () {
        Random rnd = new Random();
        return rnd.nextInt(999);
    }
    @AfterClass
    public void afterClass () {
        driver.quit();
    }

     */
}
