package com.appiumProject.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appiumProject.base.DriverFactory;

public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    
    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
    }

    public static String saveScreenshot(String imageName) { 
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new WebDriverException("WebDriver is not initialized");
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File f = ts.getScreenshotAs(OutputType.FILE);
        String dirPath = "./screenshotFailed";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        String filePath = dirPath + "/" + imageName + "-" + Util.getCurrentTimeStamp() + ".png";
        try {
            FileUtils.copyFile(f, new File(filePath));
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", filePath, e);
            throw new RuntimeException("Failed to save screenshot", e);
        }
    }
}