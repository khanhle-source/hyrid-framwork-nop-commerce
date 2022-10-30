package pageFactory;

import common.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends BasePageFactory {
    private WebDriver driver;
    public HomePageObject (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    //Page UI
    @FindBy(how = How.XPATH, using = "//a[@class='ico-register']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[@class='ico-login']")
    private WebElement loginLink;

    @FindBy(css = "a[class='ico-account']")
    private WebElement myAccountLink;

    // Page Object / Action
    public void clickToRegisterLink () {
        waitForElementClickable (driver, registerLink);
        clickToElement(driver, registerLink);
    }

    public void clickToLoginLink () {
        waitForElementClickable (driver, loginLink);
        clickToElement(driver, loginLink);
    }

    public boolean isMyAccountLinkDisplay () {
        waitForElementClickable (driver, myAccountLink);
        return isElementDisplayed(driver,myAccountLink);
    }
}
