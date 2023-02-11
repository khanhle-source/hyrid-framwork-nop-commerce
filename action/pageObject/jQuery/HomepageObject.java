package pageObject.jQuery;

import common.BasePage;
import jQuery.pageUIs.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getCountryEachRowOfAllPage () {
        int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
        List<String> allRowValueAllPage = new ArrayList<String>();

        for (int i = 1; i<=totalPage; i++) {
            openPagingByPageNumber(String.valueOf(i));
            for (int j=1; j<= (getElementSize(driver,HomePageUI.EACH_ROW_OF_EACH_PAGE)); j++) {
               // System.out.println(getElementText(driver,HomePageUI.COUNTRY_OF_EACH_ROW, String.valueOf(j)));
                allRowValueAllPage.add(getElementText(driver,HomePageUI.COUNTRY_OF_EACH_ROW, String.valueOf(j)));
            }

        }
        return allRowValueAllPage;
    }

    public List<String> getAllDataOfAllTable () {
        int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
        List<String> allRowValueAllPage = new ArrayList<String>();

        //duyet qua tung trang
        for (int i = 1; i<=totalPage; i++) {
            openPagingByPageNumber(String.valueOf(i));

            // duyet qua tung dong
            for (int j=1; j<= (getElementSize(driver,HomePageUI.EACH_ROW_OF_EACH_PAGE)); j++) {
                //lay ra cac elements trong tung dong
                List<WebElement> elements = getListWebElement(driver,HomePageUI.ALL_DATA_OF_EACH_ROW, String.valueOf(j));
                // duyet qua tung cell
                for (WebElement eachElement :elements) {
                    allRowValueAllPage.add(eachElement.getText());
                }
               }
        }
        return allRowValueAllPage;
    }

}
