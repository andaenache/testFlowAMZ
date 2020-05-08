package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.List;


public class SearchResultsPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\" Results\")")
    protected WebElement weSearchResults;

    public List<WebElement> resultsList(String searchString) {
        return driver.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"" + searchString + "\")");
    }

    public String identifyAndSelectResultsEntry(String searchString) {
        boolean resultFound = false;
        List<WebElement> results = new ArrayList<WebElement>();
        for (int i = 0; i < testData.Constants.RETRY_LIMIT; i++) {
            swipeScreenUp(testData.Constants.SCROLL_STEPS);
            results = resultsList(searchString);
            for (int j = 0; j < results.size(); j++) {
                String text = results.get(j).getText();
                Point location = results.get(j).getLocation();
                if (text.contains(testData.Constants.NOISE_TOKEN) || location.getY() > testData.Constants.MAX_ALLOWED_Y) {
                    results.set(j, null);
                }
            }
            while (results.remove(null)) ;
            resultFound = results.size() > 0;
            if (resultFound) break;
        }
        if (resultFound) {
            String title = results.get(results.size() - 1).getText();
            WebElement entry = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + title.trim() + "\")");
            clickElementAfterWait(entry);
            return title.trim();
        } else {
            System.out.println("Unable to find results for given search");
            return null;
        }
    }

    public boolean isResultsVisible() {
        return waitForElementToBeVisible(weSearchResults);
    }

    public int getNumberOfResults() {
        String searchResultsText = weSearchResults.getText();
        return Integer.valueOf(searchResultsText.substring(0, searchResultsText.indexOf(" ")));
    }

    public SearchResultsPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }
}
