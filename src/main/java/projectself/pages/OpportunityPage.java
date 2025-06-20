package projectself.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OpportunityPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By opportunityTab = By.xpath("//a[@title='Opportunities']");
    private final By newButton = By.xpath("//div[@title='New']");
    private final By opportunityNameField = By.xpath("//input[@name='Name']");
    private final By closeDateField = By.xpath("//input[@name='CloseDate']");
    private final By stageDropdown = By.xpath("//button[@aria-label='Stage, --None--']");
    private final By stageValue = By.xpath("//span[@title='Qualification']");
    private final By saveButton = By.xpath("//button[@name='SaveEdit']");
    By spinner = By.cssSelector("lightning-spinner");
    // Constructor
    public OpportunityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Actions
    public void goToOpportunityTab() throws InterruptedException {
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    	//wait.until(ExpectedConditions.elementToBeClickable(opportunityTab)).click();
    	WebElement leadsTabElement = wait.until(ExpectedConditions.elementToBeClickable(opportunityTab));
    	try {
    	     leadsTabElement.click();
    	 } catch (Exception e) {
    	     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", leadsTabElement);
    	 }
    	    Thread.sleep(3000);
    	}
    

    public void clickNewButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
    }

    public void enterOpportunityDetails(String oppName, String closeDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(opportunityNameField)).sendKeys(oppName);
        driver.findElement(closeDateField).sendKeys(closeDate);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

     // Step 1: Wait for the Stage dropdown to be clickable
     By stageDropdown = By.xpath("//button[contains(@aria-label,'Stage')]");
     WebElement stage = wait.until(ExpectedConditions.elementToBeClickable(stageDropdown));
     stage.click();

     // Step 2: Wait for the specific Stage value to be clickable
     By stageValue = By.xpath("//lightning-base-combobox-item//span[@class='slds-media__body']//span[text()='Qualification']");  // Example value
     WebElement stageOption = wait.until(ExpectedConditions.elementToBeClickable(stageValue));
     stageOption.click();

    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }
}
