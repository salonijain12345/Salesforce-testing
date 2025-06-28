package projectself.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.LeadPage;
import projectself.pages.LoginPage;

public class LeadTest extends BaseTest {

    @Test(priority = 2, description = "Create a new Lead in Salesforce")
    public void createLeadTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        // Step 1: Login
        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);
        
        // Step 2: Navigate to Leads tab
        LeadPage leadPage = new LeadPage(driver);
        leadPage.goToLeadsTab();

        // Step 3: Create a new lead
        String testLastName = "Automation";
        String testCompany = "Way2Salesforce";
        leadPage.createNewLead(testLastName, testCompany);

        // Step 4: Basic assertion — check if lead name is displayed in header
        // Note: Adjust this locator based on your Salesforce instance
        String expectedName = testLastName;
        String actualHeader = driver.findElement(By.xpath("//lightning-formatted-name")).getText();

        Assert.assertTrue(actualHeader.contains(expectedName),
                "Lead creation failed. Expected: " + expectedName + ", but got: " + actualHeader);
    }
}