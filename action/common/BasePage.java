package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class BasePage {
    //chua cac ham dung chung cho page (package)
    private long longtimeout = 30;
    private long shorttimeout = 5;

    public static BasePage getBasePageObject () {
        return new BasePage();
    }

    // get url
    public void getURl (WebDriver driver, String url) {
        driver.get(url);
    }

    //get title
    public String getTitle (WebDriver driver) {
        return driver.getTitle();
    }

    //get page URL
    public String getPageURL (WebDriver driver) {
        return driver.getCurrentUrl();
    }

    //get page source
    public String getPageSource (WebDriver driver) {
        return driver.getPageSource();
    }

    //back to page
    public void backToPage (WebDriver driver) {
        driver.navigate().back();
    }

    //forward to page
    public void forwardToPage (WebDriver driver) {
        driver.navigate().forward();
    }

    // refesh page
    public void refreshPage (WebDriver driver) {
        driver.navigate().refresh();
    }

    //wait for alert
    public Alert waitForAlert (WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    //accept alert
    public void acceptAlert (WebDriver driver) {
        waitForAlert(driver).accept();
    }

    //dismiss alert
    public void dismissAlert (WebDriver driver) {
        waitForAlert(driver).dismiss();
    }

    //get alert text
    public String getAlertText (WebDriver driver) {
        return waitForAlert(driver).getText();
    }

    //sendkey to alert
    public void sendKeyToAlert (WebDriver driver, String keyword) {
          waitForAlert(driver).sendKeys(keyword);
    }

    //switch to window by ID
    public void switchToWindowByID (WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id:allWindowIDs) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    //switch to window by title
    public void switchToWindowByTitle (WebDriver driver, String tabTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String id:allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if(actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    public void closeAllTabwithoutParent (WebDriver driver, String parentID) {
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
    public By getByXpath (String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    //get web element
    public WebElement getWebElement (WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return element;
    }

    //get list web element
    public List<WebElement> getListWebElement (WebDriver driver, String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return elements;
    }

    //click to Element
    public void clickToElement (WebDriver driver, String xpath) {
        getWebElement(driver, xpath).click();
    }

    //sendkey to Element
    public void sendKeyToElement (WebDriver driver, String xpath, String textValue) {
        getWebElement(driver, xpath).clear();
        getWebElement(driver, xpath).sendKeys(textValue);
    }


    //select item in dropdown
    public void selectItemInDefaultDropdown (WebDriver driver, String xpath, String textItem) {
        Select select = new Select(getWebElement(driver,xpath));
        select.selectByValue(textItem);
    }

    //get first selected value in dropdown list
    public String getSelectedItemDefaultDropdown (WebDriver driver, String xpath) {
        Select select = new Select(getWebElement(driver,xpath));
        return select.getFirstSelectedOption().getText();
    }

    //check dropdown is multiple or not
    public boolean isDropdownMultiple (WebDriver driver, String xpath) {
        Select select = new Select(getWebElement(driver,xpath));
        return select.isMultiple();
    }

    public void selectItemIsCustomDropdown (WebDriver driver, String parentXpath, String childXpath, String expectedText) {
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
    public void sleepInSecond (long time) {
        try {
            Thread.sleep(time *1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get element attribute
    public String getElementAttribute (WebDriver driver, String xpath, String attribute) {
        return getWebElement(driver, xpath).getAttribute(attribute);
    }

    //get element text
    public String getElementText (WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).getText();
    }

    //get element css value
    public String getElementCssValue (WebDriver driver, String xpath, String prop) {
        return getWebElement(driver, xpath).getCssValue(prop);
    }

    //change RGBA color to HEXA
    public String getHexaColorFromRGBA (String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    //get elements size
    public int getElementSize(WebDriver driver, String xpath) {
        return getListWebElement(driver, xpath).size();
    }

    //check dropdown list
    public void checkToDefaultCheckbox (WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if(!element.isSelected()) {
            element.click();
        }
    }

    //uncheck dropdown list
    public void uncheckToDefaultCheckbox (WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if(element.isSelected()) {
            element.click();
        }
    }

    //element display or not
    public boolean isElementDisplayed (WebDriver driver, String xpath) {
        return  getWebElement(driver, xpath).isDisplayed();
    }

    //element enable or not
    public boolean isElementEnable (WebDriver driver, String xpath) {
        return  getWebElement(driver, xpath).isEnabled();
    }

    //element selected or not
    public boolean isElementSelected (WebDriver driver, String xpath) {
        return  getWebElement(driver, xpath).isSelected();
    }

    //switch to frame iframe
    public void switchToFrameIFrame (WebDriver driver, String xpath) {
        driver.switchTo().frame(getWebElement(driver,xpath));
    }

    //switch to default content
    public void switchToDefaultContent (WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    //hover mouse
    public void  hoverMouseToElement (WebDriver driver, String xpath) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpath)).perform();
    }


    //scroll to bottom page using js
    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    //navigate to url by js
    public void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    //highlight element by js
    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    //click to element by js
    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    //scroll to element on top
    public void scrollToElementOnTop(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    //scroll to element on down
    public void scrollToElementOnDown(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    //sendkey to element
    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    //remove attribute from dom
    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    //check page load successful
    public boolean areJQueryAndJSLoadSuccess (WebDriver driver) {
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
    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    //get domain
    public String getDomain (WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.domain");
    }

    //check image loaded
    public boolean isImageLoaded(WebDriver driver,String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
        return status;
    }

    //wait for element visible
    public void waitForElementVisible (WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpath)));
    }

    //wait for all element visible
    public void waitForAllElementVisible (WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpath)));
    }

    //wait for element invisible
    public void waitForElementInvisible (WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpath)));
    }

    //wait for all element invisible
    public void waitForAllElementInvisible (WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,xpath)));
    }

    //wait for element clickable
    public void waitForElementClickable (WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpath)));
    }
}
