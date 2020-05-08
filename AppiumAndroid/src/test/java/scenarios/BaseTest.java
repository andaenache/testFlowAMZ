package scenarios;

import framework.androidDriver.AndroidDriverBuilder;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod
    protected void setup(ITestContext context) {
        driver = new AndroidDriverBuilder().getDriver(context);
    }

    @AfterMethod
    protected void tearDown() {
        driver.quit();
    }
}
