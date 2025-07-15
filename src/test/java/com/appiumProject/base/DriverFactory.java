package com.appiumProject.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriverException;

import com.appiumProject.config.DriverOptionsFactory;
import com.appiumProject.config.Platform;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverFactory {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    
    public static void setDriver(Platform platform, String deviceSetup) {
        try {
            switch (platform) {
                case ANDROID -> {
                    UiAutomator2Options options = DriverOptionsFactory.getAndroidOptions(deviceSetup);
                    AppiumDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
                    driver.set(androidDriver);
                }
                case IOS -> throw new UnsupportedOperationException("iOS setup not implemented yet");
                default -> throw new IllegalArgumentException("Invalid platform passed:" + platform);
            }
        } catch (MalformedURLException | WebDriverException e) {
            throw new RuntimeException("Failed to initalize Appium driver", e);
        }
    }

        public static AppiumDriver getDriver() {
            return driver.get();
        }

        public static void quitDriver() {
            AppiumDriver drv = getDriver();
            if (drv != null) {
                drv.quit();
                driver.remove();
            }
        }
}
