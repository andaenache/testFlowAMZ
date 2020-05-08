package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import pageObjects.pages.*;

public class AmazonPageFactory {
    public LaunchPage getLaunchPage(AndroidDriver driver) {
        return new LaunchPage(driver);
    }

    public HomePage getHomePage(AndroidDriver driver) {
        return new HomePage(driver);
    }

    public ActionBarPage getActionBarPage(AndroidDriver driver) {
        return new ActionBarPage(driver);
    }

    public SearchResultsPage getSearchResultsPage(AndroidDriver driver) {
        return new SearchResultsPage(driver);
    }

    public SignInPage getSignInPage(AndroidDriver driver) {
        return new SignInPage(driver);
    }

    public DealPage getDealPage(AndroidDriver driver) {
        return new DealPage(driver);
    }
}
