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
    private final By stageDropdown = By.xpath("//button[contains(@aria-label,'Stage')]");
    private final By stageValue = By.xpath("//lightning-base-combobox-item//span[@class='slds-media__body']//span[text()='Qualification']");
    private final By saveButton = By.xpath("//button[@name='SaveEdit']");
    private final By opportunityNameHeader = By.xpath("//lightning-formatted-text[contains(@class,'custom-truncate')]");
    private final By spinner = By.cssSelector("lightning-spinner");

    // Constructor
    public OpportunityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Navigate to Opportunity tab
    public void goToOpportunityTab() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        WebElement oppTabElement = wait.until(ExpectedConditions.elementToBeClickable(opportunityTab));
        try {
            oppTabElement.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", oppTabElement);
        }
        Thread.sleep(3000);
    }

    // Click 'New' button
    public void clickNewButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
    }

    // Fill in Opportunity form
    public void enterOpportunityDetails(String oppName, String closeDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(opportunityNameField)).sendKeys(oppName);
        driver.findElement(closeDateField).sendKeys(closeDate);

        WebElement stage = wait.until(ExpectedConditions.elementToBeClickable(stageDropdown));
        stage.click();

        WebElement stageOption = wait.until(ExpectedConditions.elementToBeClickable(stageValue));
        stageOption.click();
    }

    // Click Save
    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    // Verify opportunity is created
    public boolean verifyOpportunityCreated(String name) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(opportunityNameHeader, name));
    }
}
