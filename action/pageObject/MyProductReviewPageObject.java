package pageObject;

import common.BasePage;
import org.openqa.selenium.WebDriver;

public class MyProductReviewPageObject extends BasePage {
    private WebDriver driver;

    public MyProductReviewPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
