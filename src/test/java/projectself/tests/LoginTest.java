package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectself.base.BaseTest;
import projectself.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify that valid login works in Salesforce")
    public void verifyValidLogin() {
        driver.get(prop.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        // Assertion - Replace this logic based on your Salesforce post-login page
        Assert.assertTrue(driver.getTitle().contains("Home") 
                || driver.getCurrentUrl().contains("home"), 
                "Login failed - User not redirected to Home");
    }
}
