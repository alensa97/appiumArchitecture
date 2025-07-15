package com.appiumProject.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.appiumProject.base.BaseTest;
import com.appiumProject.base.DriverFactory;

public class FirstTest extends BaseTest{
    
    @Test
    public void test() throws InterruptedException {
        System.out.println("Emulator is running");
        Thread.sleep(5000);
        String text = DriverFactory.getDriver().findElement(By.id("login_screen_welcome_back_label")).getAttribute("content-desc");
        System.out.println(text);
        Assert.assertEquals(text, "Welcome Back");
    }
}
