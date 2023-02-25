package javaBasic;

import common.GlobalConstants;
import org.testng.annotations.Test;

import java.io.File;

public class Topic_16_System_Properties {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    //windows/mac/linux
    public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles";

    public static void main(String[] args) {
        System.out.println(PROJECT_PATH);
        System.out.println(UPLOAD_FILE_FOLDER);
    }

    @Test
    public void TC_07 () {
        String filePath = GlobalConstants.UPLOAD_FILE;
        String fullFileName = "";
        String[] fileNames = {"Csharp.png", "Java.png", "Python.png"};

        for (String file: fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();

        System.out.println(fullFileName);
    }

}
