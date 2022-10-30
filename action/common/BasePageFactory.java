package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePageFactory {
    //chua cac ham dung chung cho page (package)
    private long longtimeout = 30;
    private long shorttimeout = 5;

    public static BasePageFactory getBasePageObject () {
        return new BasePageFactory();
    }

    // get url
    protected void getURl (WebDriver driver, String url) {
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
    protected void refreshPage (WebDriver driver) {
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

    //get by xpath
    protected By getByXpath (String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    //get web element
    protected WebElement getWebElement (WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return element;
    }

    //get list web element
    protected List<WebElement> getListWebElement (WebDriver driver, String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return elements;
    }

    //click to Element
    protected void clickToElement (WebDriver driver, WebElement e) {
        e.click();
    }

    //sendkey to Element
    protected void sendKeyToElement (WebDriver driver, WebElement e, String textValue) {
        e.clear();
        e.sendKeys(textValue);
    }


    //select item in dropdown
    protected void selectItemInDefaultDropdown (WebDriver driver, String xpath, String textItem) {
        Select select = new Select(getWebElement(driver,xpath));
        select.selectByValue(textItem);
    }

    //get first selected value in dropdown list
    protected String getSelectedItemDefaultDropdown (WebDriver driver, String xpath) {
        Select select = new Select(getWebElement(driver,xpath));
        return select.getFirstSelectedOption().getText();
    }

    //check dropdown is multiple or not
    protected boolean isDropdownMultiple (WebDriver driver, String xpath) {
        Select select = new Select(getWebElement(driver,xpath));
        return select.isMultiple();
    }

    protected void selectItemIsCustomDropdown (WebDriver driver, String parentXpath, String childXpath, String expectedText) {
        getWebElement(driver, parentXpath).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
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
    protected void sleepInSecond (long time) {
        try {
            Thread.sleep(time *1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get element attribute
    protected String getElementAttribute (WebDriver driver, String xpath, String attribute) {
        return getWebElement(driver, xpath).getAttribute(attribute);
    }

    //get element text
    protected String getElementText (WebDriver driver, WebElement e) {
        return e.getText();
    }

    //get element css value
    protected String getElementCssValue (WebDriver driver, String xpath, String prop) {
        return getWebElement(driver, xpath).getCssValue(prop);
    }

    //change RGBA color to HEXA
    protected String getHexaColorFromRGBA (String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    //get elements size
    protected int getElementSize(WebDriver driver, String xpath) {
        return getListWebElement(driver, xpath).size();
    }

    //check dropdown list
    protected void checkToDefaultCheckbox (WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if(!element.isSelected()) {
            element.click();
        }
    }

    //uncheck dropdown list
    protected void uncheckToDefaultCheckbox (WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if(element.isSelected()) {
            element.click();
        }
    }

    //element display or not
    protected boolean isElementDisplayed (WebDriver driver, WebElement e) {
        return  e.isDisplayed();
    }

    //element enable or not
    protected boolean isElementEnable (WebDriver driver, String xpath) {
        return  getWebElement(driver, xpath).isEnabled();
    }

    //element selected or not
    protected boolean isElementSelected (WebDriver driver, String xpath) {
        return  getWebElement(driver, xpath).isSelected();
    }

    //switch to frame iframe
    protected void switchToFrameIFrame (WebDriver driver, String xpath) {
        driver.switchTo().frame(getWebElement(driver,xpath));
    }

    //switch to default content
    protected void switchToDefaultContent (WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    //hover mouse
    protected void  hoverMouseToElement (WebDriver driver, String xpath) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpath)).perform();
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

    //wait for element visible
    protected void waitForElementVisible (WebDriver driver, WebElement e) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOf(e));
    }

    //wait for all element visible
    protected void waitForAllElementVisible (WebDriver driver, List <WebElement> elements) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    //wait for element invisible
    protected void waitForElementInvisible (WebDriver driver, WebElement e) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.invisibilityOf(e));
    }

    //wait for all element invisible
    protected void waitForAllElementInvisible (WebDriver driver, List<WebElement> e) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(e));
    }

    //wait for element clickable
    protected void waitForElementClickable (WebDriver driver, WebElement e) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(e));
    }
}
