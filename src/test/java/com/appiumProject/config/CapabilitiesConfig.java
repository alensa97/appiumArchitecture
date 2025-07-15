package com.appiumProject.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CapabilitiesConfig {
    
    public String platformName;
    public String app;
    public String deviceName;
    public String automationName;
    public String udid;
    public boolean disableIdLocatorAutocompletion;
    public boolean hideKeyboard;
    public long uiautomator2ServerInstallTimeout;
    public long setNewCommandTimeout;
    public boolean autoGrantPermissions;
    public int port;

    //TODO investigate about capabilities in general and which to use in automation and YAML file
}
