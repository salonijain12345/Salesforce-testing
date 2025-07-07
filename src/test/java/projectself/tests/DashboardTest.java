package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;
import projectself.pages.DashboardPage;

public class DashboardTest extends BaseTest {

    @Test(description = "View a dashboard and validate widgets")
    public void viewDashboardTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);

        HomePage homePage = new HomePage(driver);
        homePage.goToSales();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goToDashboardsTab();
        dashboardPage.openFirstDashboard();

        // Validate
        String title = dashboardPage.getDashboardTitle();
        System.out.print(title);
        Thread.sleep(1000);
        //Assert.assertTrue(title.contains("first"), "Dashboard title is incorrect or not visible");

//        boolean widgetsExist = dashboardPage.areWidgetsVisible();
//        Assert.assertTrue(widgetsExist, "Dashboard widgets are missing");
    }
}
