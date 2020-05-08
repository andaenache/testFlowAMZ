package scenarios.login;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AmazonPageFactory;
import pageObjects.pages.*;
import scenarios.BaseTest;

public class SearchAndAddToCart extends BaseTest {

    @Test
    @Parameters({"email", "password", "zipCode", "searchTerm", "relevantTerm"})
    public void loadApp(String email, String pass, String zip, String searchTerm, String relevantTerm) throws Exception {
        LaunchPage launchPage = new AmazonPageFactory().getLaunchPage(driver);
        launchPage.ifSignInVisibleSkip();

        ActionBarPage actionBar = new AmazonPageFactory().getActionBarPage(driver);
        actionBar.isAmazonLogoVisible();

        HomePage homePage = new AmazonPageFactory().getHomePage(driver);
        homePage.waitFor();

        //Disabling SignIn flow as in absence of appropriate test account behavior after login can vary and trying to cover
        // all possible flows is outside of scope
        /*
        homePage.clickSignIn();
        SignInPage signIn = new AmazonPageFactory().getSignInPage(driver);
        signIn.signInE2E(email, pass);
        homePage.waitFor();
        */

        actionBar.searchFor(searchTerm);
        homePage.changeDeliveryToUS(zip);

        SearchResultsPage searchResults = new AmazonPageFactory().getSearchResultsPage(driver);
        if (!searchResults.isResultsVisible()) {
            actionBar.searchFor(searchTerm);
        }

        Assert.assertTrue(searchResults.isResultsVisible(), "Search results is not visible");
        Assert.assertTrue(searchResults.getNumberOfResults() > 0, "There should be a number of results found");

        String titleOfSelectedResult = searchResults.identifyAndSelectResultsEntry(relevantTerm);
        DealPage dealPage = new AmazonPageFactory().getDealPage(driver);

        Assert.assertTrue(dealPage.validateTitle(titleOfSelectedResult), "Deal title found is not deal title expected: " + titleOfSelectedResult);
    }
}
