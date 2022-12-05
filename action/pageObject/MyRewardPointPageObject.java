package pageObject;

import common.BasePage;
import org.openqa.selenium.WebDriver;

public class MyRewardPointPageObject extends BasePage {
    private WebDriver driver;

    public MyRewardPointPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
