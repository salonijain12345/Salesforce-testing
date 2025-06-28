package projectself.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;
import projectself.pages.AccountPage;

public class AccountTest extends BaseTest {

    @Test(description = "Create a new account and validate the header",enabled = false)
    public void createAccountTest() throws InterruptedException {
    	// Login first
   	 driver.get(prop.getProperty("url"));

        // Step 1: Login
        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(5000);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Update XPath if needed after inspecting the button
            By allowButton = By.xpath("//button[contains(text(),'Allow')]");
            
            wait.until(ExpectedConditions.elementToBeClickable(allowButton)).click();
            System.out.println("Clicked the Allow button.");
        } catch (TimeoutException e) {
            System.out.println("Allow button not found — no pop-up appeared.");
        }
       // Go to Opportunity Page
       HomePage homePage = new HomePage(driver);
       homePage.goToSales(); 

        AccountPage accountPage = new AccountPage(driver);
        accountPage.goToAccountTab();

        String accName = "TestCompany" + System.currentTimeMillis();
        accountPage.createAccount(accName);
        Thread.sleep(3000);

        String headerText = accountPage.getAccountHeaderText();
        Assert.assertTrue(headerText.contains("TestCompany"), "Account creation failed or header mismatch");
    }
    
    @Test(description = "Add contact to an existing account",enabled = false)
    public void addContactToAccountTest() throws InterruptedException {
    	// Login first
      	 driver.get(prop.getProperty("url"));
    	LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(5000);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Update XPath if needed after inspecting the button
            By allowButton = By.xpath("//button[contains(text(),'Allow')]");
            
            wait.until(ExpectedConditions.elementToBeClickable(allowButton)).click();
            System.out.println("Clicked the Allow button.");
        } catch (TimeoutException e) {
            System.out.println("Allow button not found — no pop-up appeared.");
        }
       // Go to Opportunity Page
       HomePage homePage = new HomePage(driver);
       homePage.goToSales(); 
    	
    	AccountPage accountPage = new AccountPage(driver); // Pass driver if constructor needs it
    	
        String accName = "TestCompany" + System.currentTimeMillis();
        accountPage.goToAccountTab();
       // accountPage.createAccount(accName);

        accountPage.addContactToAccount("John Doe", "john@example.com", "9876543210");
        // Optionally, add assertions here based on UI confirmation or toast message
    }
    
    @Test(description = "Edit an existing account name")
    public void editAccountTest() throws InterruptedException {
    	// Login first
     	 driver.get(prop.getProperty("url"));
   	LoginPage login = new LoginPage(driver);
       login.login(prop.getProperty("username"), prop.getProperty("password"));
       Thread.sleep(5000);
       try {
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
           // Update XPath if needed after inspecting the button
           By allowButton = By.xpath("//button[contains(text(),'Allow')]");
           
           wait.until(ExpectedConditions.elementToBeClickable(allowButton)).click();
           System.out.println("Clicked the Allow button.");
       } catch (TimeoutException e) {
           System.out.println("Allow button not found — no pop-up appeared.");
       }
      // Go to Opportunity Page
      HomePage homePage = new HomePage(driver);
      homePage.goToSales(); 
   	
   	AccountPage accountPage = new AccountPage(driver);
    	
        String accName = "EditTestCompany" + System.currentTimeMillis();
        accountPage.goToAccountTab();
        accountPage.createAccount(accName);

        String updatedName = "UpdatedCompany" + System.currentTimeMillis();
        accountPage.editAccount(updatedName);

//        String header = accountPage.getAccountHeaderText();
//        Assert.assertTrue(header.contains("UpdatedCompany"), "Account edit failed");
    }

}
