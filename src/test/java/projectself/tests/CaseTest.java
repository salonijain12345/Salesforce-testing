package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;
import projectself.pages.CasePage;

public class CaseTest extends BaseTest {

    @Test(description = "Create a new case and validate header")
    public void createCaseTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(5000);

        // Go to Sales App
        HomePage homePage = new HomePage(driver);
        homePage.goToSales();

        // Create Case
        CasePage casePage = new CasePage(driver);
        casePage.goToCasesTab();

        String subject = "Issue - " + System.currentTimeMillis();
        casePage.createCase(subject);

        String header = casePage.getCaseHeaderText();
        Assert.assertTrue(header.contains("Issue"), "Case creation failed or header mismatch");
    }
}

