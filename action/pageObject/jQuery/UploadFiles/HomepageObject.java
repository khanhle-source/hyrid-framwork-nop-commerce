package pageObject.jQuery.UploadFiles;

import common.BasePage;
import jQuery.pageUIs.UploadFiles.HomePageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomepageObject extends BasePage {
     WebDriver driver;
    public HomepageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isFileLoadedByName (String fileName) {
        waitForAllElementVisible (driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed (driver,HomePageUI.FILE_NAME_LOADED, fileName );
    }

    public boolean isFileUploadedByName (String fileName) {
        waitForAllElementVisible (driver, HomePageUI.FILE_NAME_UPLOADED, fileName);
        return isElementDisplayed (driver,HomePageUI.FILE_NAME_UPLOADED, fileName );
    }

    public boolean isFileUploadedByImage (String fileName) {
        waitForAllElementVisible (driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
        return isImageLoaded (driver,HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName );
    }

    public void clickToStartButton () {
        List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON);
        for ( WebElement startButton: startButtons) {
            startButton.click();
            sleepInSecond(2);

        }
    }


}
