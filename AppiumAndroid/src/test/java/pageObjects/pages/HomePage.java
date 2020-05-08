package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "mshop_webView_container")
    protected WebElement weMainView;

    @AndroidFindBy(uiAutomator = "text(\"Sign in\")")
    protected WebElement weSignInButton;

    @AndroidFindBy(id = "glow_subnav_ingress")
    protected WebElement weDeliverTo;

    @AndroidFindBy(id = "loc_ux_pin_code_button")
    protected WebElement weDeliverToUS;

    @AndroidFindBy(id = "loc_ux_pin_code_text_pt1")
    protected WebElement weUsZipCode;

    @AndroidFindBy(id = "loc_ux_update_pin_code")
    protected WebElement weUpdateZipcode;

    public void waitFor() throws Exception {
        if (!waitForElementToBeVisible(weMainView)) {
            throw new Exception("Not on Home page");
        }
    }

    public void clickSignIn() {
        clickElementAfterWait(weSignInButton);
    }

    public void changeDeliveryToUS(String zip) {
        if (waitForElementToBeVisible(weDeliverTo)) {
            clickElementAfterWait(weDeliverTo);
            clickElementAfterWait(weDeliverToUS);
            clickElementAfterWait(weUsZipCode);
            setTextInField(weUsZipCode, zip);
            clickElementAfterWait(weUpdateZipcode);
        }
    }

    public HomePage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
