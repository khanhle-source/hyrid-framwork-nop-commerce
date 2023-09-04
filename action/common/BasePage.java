package common;

import jQuery.pageUIs.UploadFiles.BasePageUI;
import nopCommerce.userpageUIs.UserRegisterPageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.portal.UserAddressPageObject;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserMyProductReviewPageObject;
import pageObject.nopCommerce.portal.UserMyRewardPointPageObject;
import nopCommerce.userpageUIs.UserBasePageUI;

import java.util.List;
import java.util.Set;

public class BasePage {
    //chua cac ham dung chung cho page (package)
    private long longtimeout = GlobalConstants.LONG_TIMEOUT;
    private long shorttimeout = GlobalConstants.SHORT_TIMEOUT;

    public static BasePage getBasePageObject () {
        return new BasePage();
    }

    // get url
    public void getURl (WebDriver driver, String url) {
        driver.get(url);
    }

    //get title
    protected String getTitle (WebDriver driver) {
        return driver.getTitle();
    }

    //get page URL
    protected String getPageURL (WebDriver driver) {
        return driver.getCurrentUrl();
    }

    //get page source
    protected String getPageSource (WebDriver driver) {
        return driver.getPageSource();
    }

    //back to page
    protected void backToPage (WebDriver driver) {
        driver.navigate().back();
    }

    //forward to page
    protected void forwardToPage (WebDriver driver) {
        driver.navigate().forward();
    }

    // refesh page
    public void refreshPage (WebDriver driver) {
        driver.navigate().refresh();
    }

    //wait for alert
    protected Alert waitForAlert (WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    //accept alert
    protected void acceptAlert (WebDriver driver) {
        waitForAlert(driver).accept();
    }

    //dismiss alert
    protected void dismissAlert (WebDriver driver) {
        waitForAlert(driver).dismiss();
    }

    //get alert text
    protected String getAlertText (WebDriver driver) {
        return waitForAlert(driver).getText();
    }

    //sendkey to alert
    protected void sendKeyToAlert (WebDriver driver, String keyword) {
          waitForAlert(driver).sendKeys(keyword);
    }

    //switch to window by ID
    protected void switchToWindowByID (WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id:allWindowIDs) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    //switch to window by title
    protected void switchToWindowByTitle (WebDriver driver, String tabTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String id:allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if(actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    protected void closeAllTabwithoutParent (WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id:allWindowIDs) {
            if(!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }


    //get web element
    protected WebElement getWebElement (WebDriver driver, String locator) {
        WebElement element = driver.findElement(getByLocator(locator));
        return element;
    }

    //get web element (Dynamic)
    // locator=//table[@class='qgrd']//tr[%s]/td
    // dynamic value = 1
    protected WebElement getDynamicWebElement (WebDriver driver, String locator, String... DynamicValues) {
        WebElement element = driver.findElement(getByLocator( getDynamicLocator(locator,DynamicValues)));
        return element;
    }

    //get list web element
    protected List<WebElement> getListWebElement (WebDriver driver, String locator, String... DynamicValues) {
        List<WebElement> elements = driver.findElements(getByLocator( getDynamicLocator(locator,DynamicValues)));
        return elements;
    }

    //click to Element
    protected void clickToElement (WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    protected void clickToElement (WebDriver driver, String locator, String... dynamicValues) {
        getWebElement(driver, getDynamicLocator(locator, dynamicValues)).click();
    }

    //sendkey to Element
    protected void sendKeyToElement (WebDriver driver, String locator, String textValue) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(textValue);
    }

    //sendkey to Element (dynamic)
    protected void sendKeyToElement (WebDriver driver, String locator, String textValue, String... dynamicValues) {
        getWebElement(driver, getDynamicLocator(locator, dynamicValues)).clear();
        getWebElement(driver, getDynamicLocator(locator, dynamicValues)).sendKeys(textValue);
    }

    //select item in dropdown
    protected void selectItemInDefaultDropdown (WebDriver driver, String locator, String textItem) {
        Select select = new Select(getWebElement(driver,locator));
        select.selectByValue(textItem);
    }

    //select item in dropdown (dynamic)
    protected void selectItemInDefaultDropdown (WebDriver driver, String locator, String textItem, String... dynamicValues) {
        Select select = new Select(getDynamicWebElement(driver,locator, dynamicValues));
        select.selectByValue(textItem);
    }

    //get first selected value in dropdown list
    protected String getSelectedItemDefaultDropdown (WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver,locator));
        return select.getFirstSelectedOption().getText();
    }

    //check dropdown is multiple or not
    protected boolean isDropdownMultiple (WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver,locator));
        return select.isMultiple();
    }

    protected void selectItemIsCustomDropdown (WebDriver driver, String parentXpath, String childXpath, String expectedText) {
        getWebElement(driver, parentXpath).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for(WebElement item: allItems) {
            if(item.getText().trim().equals(expectedText)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("argument[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                break;
            }
        }
    }

    //sleep in second
    public void sleepInSecond (long time) {
        try {
            Thread.sleep(time *1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get element attribute
    protected String getElementAttribute (WebDriver driver, String locator, String attribute) {
        return getWebElement(driver, locator).getAttribute(attribute);
    }

    protected  String getElementAttribute (WebDriver driver, String locator, String attribute, String ... dynamicValues) {
        return getWebElement(driver, getDynamicLocator(locator, dynamicValues)).getAttribute(attribute);
    }
    //get element text
    protected String getElementText (WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    //get element text (dynamic)
    // locator=//table[@class='qgrd']//tr[%s]/td
    // dynamic value = 1
    protected String getElementText (WebDriver driver, String locator, String... dynamicValues) {
        return getDynamicWebElement(driver,locator, dynamicValues).getText();
    }
    //get element css value
    protected String getElementCssValue (WebDriver driver, String locator, String prop) {
        return getWebElement(driver, locator).getCssValue(prop);
    }

    //change RGBA color to HEXA
    protected String getHexaColorFromRGBA (String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    //get elements size
    protected int getElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    //get elements size (Dynamic)
    protected int getElementSize(WebDriver driver, String locator, String... dynamicValues) {
        return getListWebElement(driver, locator,dynamicValues).size();
    }


    //check dropdown list
    protected void checkToDefaultCheckbox (WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if(!element.isSelected()) {
            element.click();
        }
    }

    //check dropdown list (dynamic)
    protected void checkToDefaultCheckbox (WebDriver driver, String locator, String... dynamicValues) {
        WebElement element = getDynamicWebElement(driver,locator, dynamicValues);
        if(!element.isSelected()) {
            element.click();
        }
    }

    //uncheck dropdown list
    protected void uncheckToDefaultCheckbox (WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if(element.isSelected()) {
            element.click();
        }
    }

    //uncheck dropdown list (dynamic)
    protected void uncheckToDefaultCheckbox (WebDriver driver, String locator, String... dynamicValues) {
        WebElement element = getDynamicWebElement(driver,locator, dynamicValues);
        if(element.isSelected()) {
            element.click();
        }
    }

    //element display or not
    protected boolean isElementDisplayed (WebDriver driver, String locator) {
        return  getWebElement(driver, locator).isDisplayed();
    }

    //element display or not (Dynamic value)
    protected boolean isElementDisplayed (WebDriver driver, String locator, String... dynamicValues) {
       return getDynamicWebElement(driver,locator, dynamicValues).isDisplayed();

    }

    //element enable or not
    protected boolean isElementEnable (WebDriver driver, String locator) {
        return  getWebElement(driver, locator).isEnabled();
    }

    //element selected or not
    protected boolean isElementSelected (WebDriver driver, String locator) {
        return  getWebElement(driver, locator).isSelected();
    }

    //switch to frame iframe
    protected void switchToFrameIFrame (WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver,locator));
    }

    //switch to default content
    protected void switchToDefaultContent (WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    //hover mouse
    protected void  hoverMouseToElement (WebDriver driver, String locator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }

    //send key from keyboard
    public void pressKeyToElement (WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver,locatorType), key).perform();
    }

    //send key from keyboard (Dynamic)
    public void pressKeyToElement (WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.sendKeys(getDynamicLocator(locatorType, dynamicValues), key).perform();
    }

    //scroll to bottom page using js
    protected void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    //navigate to url by js
    protected void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    //highlight element by js
    protected void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    //click to element by js
    protected void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    //scroll to element on top
    protected void scrollToElementOnTop(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    //scroll to element on down
    protected void scrollToElementOnDown(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    //sendkey to element
    protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    //remove attribute from dom
    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    //check page load successful
    protected boolean areJQueryAndJSLoadSuccess (WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
              return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    //get element validation message
    protected String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    //get domain
    protected String getDomain (WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.domain");
    }

    //check image loaded
    protected boolean isImageLoaded(WebDriver driver,String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
        return status;
    }

    //check image loaded (dynamic)
    protected boolean isImageLoaded(WebDriver driver,String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getDynamicWebElement(driver, locator,dynamicValues ));
        return status;
    }

    //wait for element visible
    protected void waitForElementVisible (WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    //wait for element visible (dynamic value)
    protected void waitForElementVisible (WebDriver driver, String locator, String ... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator( getDynamicLocator(locator,dynamicValue))));
    }
    //wait for all element visible
    protected void waitForAllElementVisible (WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    //wait for all element visible (Dynamic value)
    protected void waitForAllElementVisible (WebDriver driver, String locator, String ... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator( getDynamicLocator(locator,dynamicValue))));
    }

    //wait for element invisible
    protected void waitForElementInvisible (WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    //wait for all element invisible
    protected void waitForAllElementInvisible (WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locator)));
    }

    //wait for element clickable
    protected void waitForElementClickable (WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    //wait for element clickable (dynamic)
    protected void waitForElementClickable (WebDriver driver, String locator, String ...dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, getDynamicLocator(locator, dynamicValue))));
        //getByLocator(locator, dynamicValue)
    }

    public void uploadMultipleFiles (WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_FILE;
        String fullFileName = "";
        for (String file: fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getDynamicWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
    }
    // Level 7: Switch Page
    //open address page
    public UserAddressPageObject openAddressPage (WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.ADDRESS_LINK);
        clickToElement(driver, UserBasePageUI.ADDRESS_LINK);
        return PageGeneratorManager.getAddressPage(driver);
    }

    // Level 7: Switch Page
    //open my reward points page
    public UserMyRewardPointPageObject openMyRewardPointPage (WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserBasePageUI.REWARD_POINT_LINK);
        return PageGeneratorManager.getMyRewardPoint(driver);
    }

    // Level 7: Switch Page
    //open my product review page
    public UserMyProductReviewPageObject openMyProductReviewPage (WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.PRODUCT_REVIEW_LINK);
        clickToElement(driver, UserBasePageUI.PRODUCT_REVIEW_LINK);
        return PageGeneratorManager.getMyProductReview(driver);
    }

    // Level 8: Switch Role
    // click logout at user page
    public UserHomePageObject clickToLogoutLinkAtUserPage (WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK_AT_USER);
        clickToElement(driver, UserBasePageUI.LOGOUT_LINK_AT_USER);
        return PageGeneratorManager.getHomePage(driver);
    }

    // Level 8: Swith Role
    // click logout at admin page
    public AdminLoginPageObject clickToLogoutLinkAtAdminPage (WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK_AT_ADMIN);
        clickToElement(driver,UserBasePageUI.LOGOUT_LINK_AT_ADMIN);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }

    public WebElement getShadowDOM (WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getWebElement(driver,locator ));
        return element;
    }

    // Level 9: Dynamic Locator
    // return By element
    private By getByLocator (String locatorType, String... values) {
        By by = null;

      if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        }
        else if  (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        }
        else if  (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        }
        else if  (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        }
        else if  (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        }
        else
            throw new RuntimeException("Locator type is not supported");
;
        return by;
    }

    private String getDynamicLocator (String locatorType, String... values) {
        if  (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            locatorType = String.format(locatorType, (Object[]) values);
        }
        return locatorType;
    }


    public BasePage openPagesAtMyAccountByName (WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserBasePageUI.DYNAMIC_LINK, pageName);
        clickToElement(driver, UserBasePageUI.DYNAMIC_LINK, pageName);
        switch (pageName) {
            case "Addresses" :
                return PageGeneratorManager.getAddressPage(driver);
            case "My product reviews" :
                return PageGeneratorManager.getMyProductReview(driver);
            case "Reward points":
                return PageGeneratorManager.getMyRewardPoint(driver);
            default:
                throw new RuntimeException("Invalid page name at my account area");

        }
    }

    public Set<Cookie> getAllCookies (WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies (WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie: cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }


    // Pattern Object
    public void openPagesAtMyAccountByPageName (WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
        clickToElement(driver,UserBasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName );
    }
    public void inputToTextboxByID (WebDriver driver, String textboxID, String value) {
        waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value);
        sendKeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID, value);
    }
    public void clickToButtonByText (WebDriver driver, String buttonText) {
        waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(driver,UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText );
    }

    public void selectToDropdownByName (WebDriver driver, String dropdownAttributeName, String itemValue) {
        waitForElementClickable(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
        selectItemInDefaultDropdown(driver,UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
    }

    public void selectRadioButtonByName (WebDriver driver, String radioButtonName) {
        waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_NAME, radioButtonName);
        checkToDefaultCheckbox(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_NAME, radioButtonName );

    }

    public String getTextboxValueByID (WebDriver driver, String textboxID) {
        waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        return getElementAttribute(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);

    }

}
