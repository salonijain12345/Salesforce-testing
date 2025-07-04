package projectself.pages;

import org.openqa.selenium.By;
<<<<<<< HEAD
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

=======
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

>>>>>>> ef976e4 (Initial Salesforce CRM automation framework setup with login test)
public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

<<<<<<< HEAD
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Use Duration
    }
    private final By spinner = By.xpath("//lightning-spinner");
    private By userMenu = By.xpath("//div[contains(@class, 'profileTrigger') or contains(@class, 'userProfileCardTriggerRoot')]");

    //private By appLauncher = By.xpath("//div[@role='navigation']//button[@title='App Launcher']");
    private By oppTab = By.cssSelector("a[title='Opportunities']");
    private final By appLauncher = By.xpath("//div[@role='navigation']//button[@title='App Launcher']");
    private final By viewAll = By.xpath("//button[text()='View All']");
    private final By searchAppInput = By.xpath("//input[@placeholder='Search apps or items...']");
    private final By salesApp = By.xpath("//p[normalize-space()='Sales']/ancestor::a");
=======
    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By userMenu = By.xpath("//span[@class='userProfileCardTriggerRoot']");
    private By appLauncher = By.cssSelector("div.slds-icon-waffle");
    private By oppTab = By.cssSelector("a[title='Opportunities']");

>>>>>>> ef976e4 (Initial Salesforce CRM automation framework setup with login test)
    public boolean isHomePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu)).isDisplayed();
    }

<<<<<<< HEAD
    public void goToSales() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.findElement(appLauncher).click();
    	    Thread.sleep(2000);

    	    

    	 // Click using JavaScript
    	    JavascriptExecutor js = (JavascriptExecutor) driver;
    	    WebElement viewAllButton = driver.findElement(By.xpath("//button[text()='View All']"));
    	    js.executeScript("arguments[0].click();", viewAllButton);
    	    Thread.sleep(2000);
    	    //driver.findElement(searchAppInput).click();
    	    driver.findElement(searchAppInput).sendKeys("Sales");
    	    
    	    Thread.sleep(2000);

    	    driver.findElement(salesApp).click();
    	    Thread.sleep(3000);    }

   
}
=======
    public void goToOpportunities() {
        wait.until(ExpectedConditions.elementToBeClickable(oppTab)).click();
    }

    public void openAppLauncher() {
        wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
    }
}

>>>>>>> ef976e4 (Initial Salesforce CRM automation framework setup with login test)
