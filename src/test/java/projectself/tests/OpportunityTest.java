package projectself.tests;
import org.testng.annotations.Test;
import projectself.base.BaseTest;
import projectself.pages.LoginPage;
import projectself.pages.HomePage;
import projectself.pages.OpportunityPage;

public class OpportunityTest extends BaseTest {

    @Test(description = "Create a new Opportunity in Salesforce")
    public void createOpportunityTest() throws InterruptedException {
        // Login first
    	 driver.get(prop.getProperty("url"));

         // Step 1: Login
         LoginPage login = new LoginPage(driver);
         login.login(prop.getProperty("username"), prop.getProperty("password"));
         Thread.sleep(4000);

        // Go to Opportunity Page
        HomePage homePage = new HomePage(driver);
        homePage.goToSales(); // if needed
        
        OpportunityPage opportunityPage = new OpportunityPage(driver);

        opportunityPage.goToOpportunityTab();
        opportunityPage.clickNewButton();
        opportunityPage.enterOpportunityDetails("QA Automation Opportunity", "06/30/2025");
        Thread.sleep(1000);
        opportunityPage.clickSave();
    }
}
