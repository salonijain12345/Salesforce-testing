package projectself.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Valid login with correct credentials")
    public void validLoginTest() {
        driver.get(prop.getProperty("url"));
        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("home")
                || driver.getCurrentUrl().contains("lightning"),
                "Login failed: Home page not loaded.");
    }

    @Test(priority = 2, description = "Invalid login with wrong password")
    public void invalidPasswordTest() {
        driver.get(prop.getProperty("url"));
        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), "wrongpass123");

        String errorMsg = driver.findElement(By.id("error")).getText();
        Assert.assertTrue(errorMsg.contains("Please check your username and password")
                || errorMsg.contains("incorrect"),
                "Expected error message not displayed.");
    }

    @Test(priority = 3, description = "Invalid login with wrong username")
    public void invalidUsernameTest() {
        driver.get(prop.getProperty("url"));
        LoginPage login = new LoginPage(driver);
        login.login("wronguser@example.com", prop.getProperty("password"));

        String errorMsg = driver.findElement(By.id("error")).getText();
        Assert.assertTrue(errorMsg.contains("Please check your username and password"),
                "Expected error message not displayed.");
    }

    @Test(priority = 4, description = "Login with blank username and password")
    public void blankCredentialsTest() {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login("", "");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Login")));
    }
}
