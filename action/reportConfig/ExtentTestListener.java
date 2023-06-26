package reportConfig;

import static reportConfig.ExtentTestManager.getTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import common.BaseTest;

public class ExtentTestListener extends BaseTest implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.getDriverInstant());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriverInstant();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        getTest().log(Status.FAIL, "Test Failed with percentage" + getTestMethodName(iTestResult));
    }
}