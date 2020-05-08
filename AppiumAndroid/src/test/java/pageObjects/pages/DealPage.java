package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class DealPage extends BasePage {

    public boolean validateTitle(String expectedTitle) {
        WebElement weExpected = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + expectedTitle.trim() + "\")");
        return waitForElementToBeVisible(weExpected);
    }

    public DealPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
