package com.appiumProject.config;

import java.time.Duration;

import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverOptionsFactory {

    public static UiAutomator2Options getAndroidOptions(String fileName) {
        CapabilitiesConfig config = ConfigLoader.load(fileName);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(config.platformName)
                .setApp(config.app)
                .setDeviceName(config.deviceName)
                .setAutomationName(config.automationName)
                .setAutoGrantPermissions(config.autoGrantPermissions)
                .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(config.uiautomator2ServerInstallTimeout))
                .setNewCommandTimeout(Duration.ofSeconds(config.setNewCommandTimeout))
                .setAutoGrantPermissions(config.autoGrantPermissions);
        options.setCapability("disableIdLocatorAutocompletion", config.disableIdLocatorAutocompletion);
        options.setCapability("hideKeyboard", config.hideKeyboard);
        return options;
    }
}
