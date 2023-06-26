package reportConfig;
import common.BaseTest;
import common.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener extends BaseTest implements ITestListener  {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        // Get driver from class test
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriverInstant();

        String screenshotPath = captureScreenshot(webDriver, iTestResult.getName());
        Reporter.getCurrentTestResult();
        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
        Reporter.setCurrentTestResult(null);
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenPath = GlobalConstants.REPORTING_SCREENSHOT + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(screenPath));
            return screenPath;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
