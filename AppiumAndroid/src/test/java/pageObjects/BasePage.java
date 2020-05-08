package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected AndroidDriver driver;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void rotateToLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateToPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public WebElement scrollToElementWithText(String text) {
        WebElement elem = null;
        elem = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text.trim() + "\").instance(0))"));
        return elem;
    }

    public void swipeScreenUp(int noOfSwipes) {
        for (int i = 0; i < noOfSwipes; i++) {
            swipeVertical(driver, 0.75, 0.5, 0.5, 2000);
        }
    }

    public void swipeScreenDown(int noOfSwipes) {
        for (int i = 0; i < noOfSwipes; i++) {
            swipeVertical(driver, 0.25, 0.5, 0.5, 2000);
        }
    }

    public void swipeVertical(AppiumDriver driver, double startPercentage, double finalPercentage, double anchorPercentage, int duration) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * finalPercentage);
        new TouchAction(driver).press(PointOption.point(anchor, startPoint))
                .waitAction()
                .moveTo(PointOption.point(anchor, endPoint))
                .release().perform();
    }

    public static void swipeHorizontal(AppiumDriver driver, double startPercentage, double finalPercentage, int anchor) {
        Dimension size = driver.manage().window().getSize();
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * finalPercentage);
        new TouchAction(driver).press(PointOption.point(startPoint, anchor))
                .moveTo(PointOption.point(endPoint, anchor))
                .release().perform();
    }

    protected boolean waitForElementToBeVisible(WebElement element) {
        return waitForElementToBeVisible(element, testData.Constants.DEFAULT_WAIT_TIMEOUT);
    }

    protected boolean waitForElementToBeVisible(WebElement element, int timeout) {
        if (element == null) return false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isVisible(WebElement element) {
        if (element == null) return false;
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clickElementAfterWait(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }

    protected void setTextInField(WebElement inputField, String text) {
        clickElementAfterWait(inputField);
        inputField.clear();
        driver.getKeyboard().sendKeys(text);
    }
}
