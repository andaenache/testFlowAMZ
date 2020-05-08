package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class LaunchPage extends BasePage {

    @AndroidFindBy(id = "signin_to_yourAccount")
    protected WebElement weSignInText;

    @AndroidFindBy(id = "skip_sign_in_button")
    protected WebElement weSkipSignInButton;

    public boolean isSignInTextDisplayed() {
        return waitForElementToBeVisible(weSignInText);
    }

    public void clickSkipSignIn() {
        clickElementAfterWait(weSkipSignInButton);
    }

    public void ifSignInVisibleSkip() {
        if (isSignInTextDisplayed()) {
            clickSkipSignIn();
        }
    }

    public LaunchPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
