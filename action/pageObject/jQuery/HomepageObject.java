package pageObject.jQuery;

import common.BasePage;
import jQuery.pageUIs.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomepageObject extends BasePage {
    private WebDriver driver;
    public HomepageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPagingByPageNumber (String number) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, number);
        clickToElement(driver,HomePageUI.PAGINATION_PAGE_BY_NUMBER, number);
    }
    public void enterToColumn (String columnName, String value) {
        waitForAllElementVisible(driver, HomePageUI.INPUT_TEXTBOX, columnName);
        sendKeyToElement(driver,HomePageUI.INPUT_TEXTBOX, value, columnName);
        pressKeyToElement(driver, HomePageUI.INPUT_TEXTBOX, Keys.ENTER, columnName);
    }

    public boolean isPageNumberActive (String pageNumber) {
        waitForAllElementVisible(driver, HomePageUI.ACTIVE_PAGE_NUMBER, pageNumber);
        return isElementDisplayed(driver,HomePageUI.ACTIVE_PAGE_NUMBER, pageNumber);
    }
}
