package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import common.GlobalConstants;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.PROJECT_PATH + "/extentV5/ExtentReport.html");
        reporter.config().setReportName("NopCommerce HTML Report");
        reporter.config().setDocumentTitle("NopCommerce HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Khanh Company");
        extentReports.setSystemInfo("Project", "NopCommerce");
        extentReports.setSystemInfo("Author", "Khanh QA VN");
        extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);

        return extentReports;
    }
}