package pageObject.jQuery.datatable;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
    public static HomepageObject getHomePage (WebDriver driver) {
        return new HomepageObject(driver);
    }
}
