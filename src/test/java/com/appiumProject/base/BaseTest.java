package com.appiumProject.base;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.appiumProject.config.Platform;
import com.appiumProject.utils.Util;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer(@Optional("4723") int port) {
        AppiumServerManager.startService(port);
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"deviceSetup", "platform"})
    public void startAppiumServer(

        @Optional("android-emulator.yaml") String deviceSetup,
        @Optional("ANDROID") String platform) {


        DriverFactory.setDriver(Platform.valueOf(platform.toUpperCase()), deviceSetup);
    }

    @AfterClass(alwaysRun = true)
    public void closeApp(ITestContext context) {
        for(ITestResult result : context.getFailedTests().getAllResults()) {
            Util.saveScreenshot(result.getTestClass().getName());
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer(@Optional("4723") int port) {
        AppiumServerManager.stopService(port);
    }
}