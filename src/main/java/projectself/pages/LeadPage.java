package projectself.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadPage {
	WebDriver driver;

// Locators for navigation
private final By appLauncher = By.xpath("//div[@role='navigation']//button[@title='App Launcher']");
private final By viewAll = By.xpath("//button[text()='View All']");
private final By searchAppInput = By.xpath("//input[@placeholder='Search apps or items...']");
private final By salesApp = By.xpath("//p[normalize-space()='Sales']/ancestor::a");
private final By leadsTab = By.xpath("//a[@title='Leads']");
// Locators for creating a lead
private final By newButton = By.xpath("//button[@class='slds-button slds-button_neutral slds-button_first'][@name='New']");
private final By lastNameInput = By.xpath("//input[@placeholder='Last Name']");
private final By companyInput = By.xpath("//input[@name='Company']");
private final By saveButton = By.xpath("//button[@name='SaveEdit']");

// Constructor
public LeadPage(WebDriver driver) {
    this.driver = driver;
}

// Method to navigate to Leads tab
public void goToLeadsTab() throws InterruptedException {
    driver.findElement(appLauncher).click();
    Thread.sleep(2000);

    driver.findElement(viewAll).click();
    Thread.sleep(2000);
    //driver.findElement(searchAppInput).click();
    driver.findElement(searchAppInput).sendKeys("Sales");
    
    Thread.sleep(1000);

    driver.findElement(salesApp).click();
    Thread.sleep(2000);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

 // Wait for the element to be clickable using the correct locator
 WebElement leadsTabElement = wait.until(ExpectedConditions.elementToBeClickable(leadsTab));

 // Click using JavaScriptExecutor in case normal click fails
 try {
     leadsTabElement.click();
 } catch (Exception e) {
     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", leadsTabElement);
 }
    Thread.sleep(3000);
}

// Method to create a new lead
public void createNewLead(String lastName, String company) throws InterruptedException {
    driver.findElement(newButton).click();
    Thread.sleep(2000);

    driver.findElement(lastNameInput).sendKeys(lastName);
    driver.findElement(companyInput).sendKeys(company);
    Thread.sleep(1000);

    driver.findElement(saveButton).click();
    Thread.sleep(3000);
}

}
