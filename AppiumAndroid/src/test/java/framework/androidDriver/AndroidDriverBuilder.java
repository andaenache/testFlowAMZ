package framework.androidDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

import java.io.File;


public class AndroidDriverBuilder {
    public AndroidDriver getDriver(ITestContext context) {
        String appPath = context.getCurrentXmlTest().getParameter("appPath");
        File appLocation = new File(appPath);

        String appName = context.getCurrentXmlTest().getParameter("appName");
        File app = new File(appLocation, appName);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String device = context.getCurrentXmlTest().getParameter("device");
        capabilities.setCapability("device", device);

        String deviceName = context.getCurrentXmlTest().getParameter("deviceName");
        capabilities.setCapability("deviceName", deviceName);

        String platformName = context.getCurrentXmlTest().getParameter("platformName");
        capabilities.setCapability("platformName", platformName);

        String appWaitActivity = context.getCurrentXmlTest().getParameter("appWaitActivity");
        capabilities.setCapability("appWaitActivity", appWaitActivity);

        capabilities.setCapability("ignoreUnimportantViews", false);
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("newSessionWaitTimeout", testData.Constants.NEW_SESSION_WAIT_TIMEOUT);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, testData.Constants.NEW_SESSION_WAIT_TIMEOUT);
        capabilities.setCapability("app", app.getAbsolutePath());

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        return new AndroidDriver(service.getUrl(), capabilities);
    }
}
