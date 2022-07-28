package org.example.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

//    @Attachment(value = "Browser console log", type = "text/plain")
//    private static String addConsoleLogToReport() {
//        StringBuilder sb = new StringBuilder();
//        for (Object line : logList) {
//            sb.append(line);
//            sb.append("\t");
//        }
//        return sb.toString();
//    }
}
