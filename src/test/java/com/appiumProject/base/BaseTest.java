package com.appiumProject.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.appiumProject.config.Platform;

public class BaseTest {
    
    //TODO: Create a flow for single YAML file and loader based on the XML file

    protected int port;

    @BeforeClass(alwaysRun = true)
    @Parameters({"port", "deviceSetup", "platform"})
    public void startAppiumServer(
        @Optional("4723") int port,
        @Optional("android-emulator.yaml") String deviceSetup,
        @Optional("ANDROID") String platform) {
        this.port = port;
        AppiumServerManager.startService(port);
        DriverFactory.setDriver(Platform.valueOf(platform.toUpperCase()), deviceSetup);
    }

    @AfterClass(alwaysRun = true)
    public void teardownAndStopAppiumServer() {
        DriverFactory.quitDriver();
        AppiumServerManager.stopService(port);
    }
}
