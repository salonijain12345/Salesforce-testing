package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;
import projectself.pages.LogoutPage;

public class LogoutTest extends BaseTest {

    @Test(description = "Logout from Salesforce and verify redirection to login page")
    public void logoutTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);

        HomePage home = new HomePage(driver);
        Thread.sleep(3000);
        Assert.assertTrue(home.isHomePageDisplayed(), "Login failed or home page not visible");

        LogoutPage logout = new LogoutPage(driver);
        logout.logout();
        Thread.sleep(3000);
        Assert.assertTrue(logout.isLoggedOut());
    }
}