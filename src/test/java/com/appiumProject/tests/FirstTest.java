package com.appiumProject.tests;

import org.testng.annotations.Test;

import com.appiumProject.base.BaseTest;

public class FirstTest extends BaseTest{
    
    @Test
    public void test() throws InterruptedException {
        System.out.println("Emulator is running");
        Thread.sleep(5000);
    }
}
