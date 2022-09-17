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

//1 class ke thua duoc 1 class, nhung 1 class ke thua dc nhieu interface
public class Level3_Register_Login_BasePage extends BasePage {
    WebDriver driver;
    String emailAddress;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
        driver = new FirefoxDriver();


        emailAddress = "afc" + random() + "@email.vn";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void tc_01_Register_Empty_Data() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");

    }

    @Test
    public void tc_02_Register_Successful () {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        sendKeyToElement(driver, "//input[@id='FirstName']", "Khanh");
        sendKeyToElement(driver, "//input[@id='LastName']", "Le");
        sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

        waitForElementClickable(driver, "//a[@class='ico-logout']");
        clickToElement(driver, "//a[@class='ico-logout']");
        
    }

    public int random () {
        Random rnd = new Random();
        return rnd.nextInt(999);
    }
    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
