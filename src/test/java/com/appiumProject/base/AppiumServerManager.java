package com.appiumProject.base;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.appiumProject.utils.Util;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumServerManager {

  private static final Map<Integer, AppiumDriverLocalService> services = new ConcurrentHashMap<>();

  private static AppiumDriverLocalService createInstance(String logFileName, int port) {
    Map<String, String> env = System.getenv();
    String nodeHome = env.get("NODE_HOME");
    String appiumHome = env.get("APPIUM_HOME");

    String os = System.getProperty("os.name").toLowerCase();
    String pathSeparator = os.contains("win") ? "\\" : "/";

    if (nodeHome == null || appiumHome == null) {
      StringBuilder errorMessage = new StringBuilder("Environment variables not set:\n");
      if (nodeHome == null) {
        errorMessage.append("NODE_HOME is not set. Please ensure that Node.js is installed and NODE_HOME is correctly set in your system environment variables.\n")
            .append("For example, on Windows: set NODE_HOME=C:\\path\\to\\node\n")
            .append("On Unix/Mac: export NODE_HOME=/path/to/node\n\n");
      }
      if (appiumHome == null) {
        errorMessage.append("APPIUM_HOME is not set. Please ensure that Appium is installed and APPIUM_HOME is correctly set in your system environment variables.\n")
            .append("For example, on Windows: set APPIUM_HOME=C:\\path\\to\\appium\n")
            .append("On Unix/Mac: export APPIUM_HOME=/path/to/appium\n");
      }
      throw new IllegalStateException(errorMessage.toString());
    }

    AppiumServiceBuilder builder = new AppiumServiceBuilder()
            .withAppiumJS(new File(appiumHome + pathSeparator + "appium"))
            .usingDriverExecutable(new File(nodeHome + pathSeparator + "node"))
            .usingPort(port)
            .withLogFile(new File(logFileName))
            .withIPAddress("127.0.0.1");

    return AppiumDriverLocalService.buildService(builder);
  }

  public static void startService(int port) {
    String logDirPath = "./appiumLogs";
    File logDir = new File(logDirPath);
    if (!logDir.exists()) {
      logDir.mkdirs();
    }

    String logFileName = logDir + "/" + "Appium." +  ".PORT=" + port + "-" + Util.getCurrentTimeStamp() + ".txt";    
    AppiumDriverLocalService service = createInstance(logFileName, port);
    service.start();
    services.put(port, service);
  }

  public static void stopService(int port) {
    AppiumDriverLocalService service = services.get(port);
    if (service != null && service.isRunning()) {
      service.stop();
      services.remove(port);
    }
  }

  public static void stopAllServices() {
    for (Map.Entry<Integer, AppiumDriverLocalService> entry : services.entrySet()) {
      AppiumDriverLocalService service = entry.getValue();
      if (service.isRunning()) {
        service.stop();
      }
    }
    services.clear();
  }
}
