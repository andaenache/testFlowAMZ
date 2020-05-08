package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class ActionBarPage extends BasePage {

    @AndroidFindBy(id = "chrome_action_bar_home_logo")
    protected WebElement weAmazonLogo;

    @AndroidFindBy(id = "rs_search_src_text")
    protected WebElement weSearchBar;

    @AndroidFindBy(uiAutomator = "text(\"What are you looking for?\")")
    protected WebElement weSearchPlaceholderText;

    public void clearEnterTextAndClickEnter(String test) {
        setTextInField(weSearchBar, test);
        driver.getKeyboard().sendKeys(Keys.ENTER);
    }

    public boolean isAmazonLogoVisible() {
        return waitForElementToBeVisible(weAmazonLogo);
    }

    public void clickSearchBar() {
        clickElementAfterWait(weSearchBar);
    }

    public boolean isSearchPlaceholderTextVisible() {
        return waitForElementToBeVisible(weSearchPlaceholderText);
    }

    public void searchFor(String searchTerm){
        clickSearchBar();
        clearEnterTextAndClickEnter(searchTerm);
    }

    public ActionBarPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
