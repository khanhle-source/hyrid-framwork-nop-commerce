package common;

import java.io.File;

public class GlobalConstants {
    //PRODUCTION
    public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String ADM_PAGE_URL = "https://admin-demo.nopcommerce.com/";

    //STAGING
    public static final String STAGE_PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String STAGE_ADM_PAGE_URL = "https://admin-demo.nopcommerce.com/";

    //TESTING
    public static final String TESTING_PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String TESTING_ADM_PAGE_URL = "https://admin-demo.nopcommerce.com/";


    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles/";
    public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOGS = PROJECT_PATH + File.separator + "browserLogs";

    public static final String DB_DEV_URL = "32.18.252.185:9860";
    public static final String DB_DEV_USER = "admin";
    public static final String DB_DEV_PASS = "password";

    public static final String DB_TEST_URL = "32.18.252.185:9860";
    public static final String DB_TEST_USER = "admin";
    public static final String DB_TEST_PASS = "password";

    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 10;
    public static final long RETRY_TEST_FAIL = 3;

    public static final String REPORTING_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenShots" + File.separator;
    public static final String JAVA_VERSION = System.getProperty("java.version");
    //note: bien static la bien co the truy cap tai bat cu cho nao trong project

}
