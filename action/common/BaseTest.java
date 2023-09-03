package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    //chua cac ham dung chung cho tcs

    protected WebDriver driverBaseTest;
    private String projectPath = System.getProperty("user.dir");

    public WebDriver getDriverInstant () {
        return this.driverBaseTest;
    }
    protected WebDriver getBrowerDriver (String browserName, String getURL) {
        if (browserName.equals("firefox")) {
            driverBaseTest =  WebDriverManager.firefoxdriver().create();
            //System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
          //  driverBaseTest = new FirefoxDriver();
        }
        else if (browserName.equals("chrome")) {
            //driverBaseTest = WebDriverManager.chromedriver().create();
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
            driverBaseTest = new ChromeDriver();
        }
        else {
            throw new RuntimeException("Browser name invalid");
        }
        driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driverBaseTest.get(getURL);
        return driverBaseTest;

    }

    protected String getEnvNameURL (String environmentName) {
        String url = null;
        switch (environmentName ) {
            case "prod":
                url = GlobalConstants.PORTAL_PAGE_URL;
                break;
            case "test":
                url = GlobalConstants.TESTING_PORTAL_PAGE_URL;
                break;
            default:
                break;
        }
        return url;
    }

    public void closeBrowserDriver(WebDriver driver) {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            String driverInstanceName = driver.toString().toLowerCase();

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    protected int random () {
        Random rnd = new Random();
        return rnd.nextInt(999);
    }
}
