package pageObject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    //trong TH bien global va local cung ten, dung this. de lay ra bien global
    public HomePageObject (WebDriver driver) {
        this.driver = driver;
    }
    public void clicktoRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);

    }
}
