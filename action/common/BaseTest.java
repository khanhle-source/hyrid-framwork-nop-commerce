package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    //chua cac ham dung chung cho tcs

    protected WebDriver driverBaseTest;
    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowerDriver (String browserName) {
        if (browserName.equals("firefox")) {
            driverBaseTest =  WebDriverManager.firefoxdriver().create();
            //System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
          //  driverBaseTest = new FirefoxDriver();
        }
        else if (browserName.equals("chrome")) {
            driverBaseTest = WebDriverManager.chromedriver().create();
            //System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
           // driverBaseTest = new ChromeDriver();
        }
        else {
            throw new RuntimeException("Browser name invalid");
        }
        driverBaseTest.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverBaseTest.get(GlobalConstants.PORTAL_PAGE_URL);
        return driverBaseTest;
    }

    protected int random () {
        Random rnd = new Random();
        return rnd.nextInt(999);
    }
}
