package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectself.base.BaseTest;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;
import projectself.pages.ReportsPage;

public class ReportsTest extends BaseTest {

    @Test(description = "Create and validate a report view")
    public void createReportTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);

        HomePage home = new HomePage(driver);
        home.goToSales();

        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.goToReportsTab();

        String reportName = "TestReport_" + System.currentTimeMillis();
        reportsPage.createAndSaveReport(reportName);

        String header = reportsPage.getReportHeader();
        Assert.assertTrue(header.contains("TestReport"), "Report creation failed");
    }
}
