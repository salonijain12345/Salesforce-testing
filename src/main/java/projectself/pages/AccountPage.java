package projectself.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AccountPage {
    WebDriver driver;
    WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By accountTab = By.xpath("//a[@title='Accounts']");
    private By newButton = By.xpath("//div[@title='New']");
    private By accountNameField = By.xpath("//input[@name='Name']");
    private By saveButton = By.xpath("//button[@name='SaveEdit']");
    private By header = By.xpath("//slot[@name='primaryField']/lightning-formatted-text");
    private By account=By.xpath("//span[contains(text(),'TestCompany')][2]");
    public void goToAccountTab() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for any spinner to disappear (Salesforce Lightning loading)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//lightning-spinner")));
        } catch (TimeoutException e) {
            System.out.println("No spinner found or took too long to disappear.");
        }

        // Now wait for the Accounts tab to be present and clickable
        By accountTabXPath = By.xpath("//a[@title='Accounts']");

        WebElement accountTab = wait.until(ExpectedConditions.elementToBeClickable(accountTabXPath));

        // Scroll into view and click using JS
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accountTab);
            wait.until(ExpectedConditions.elementToBeClickable(accountTab)).click();
        } catch (Exception e) {
            // Fallback JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accountTab);
        }
    
    }

    public void createAccount(String accountName) {
        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountNameField)).sendKeys(accountName);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getAccountHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }
    
    public void addContactToAccount(String contactName, String email, String phone) {
    	//open account (//a[contains(text(), 'TestCompany')])[1]
    	driver.findElement(account).click();
    	
    	
        // Click on "Related" tab
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-label='Related']"))).click();

        // Click on "New Contact"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='NewContact']"))).click();

        // Fill in contact details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']")))
            .sendKeys(contactName);

        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(phone);

        // Save
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
    }
    
    public void editAccount(String newAccountName) {
    	//*[@id="brandBand_2"]/div/div/div/div/div/one-record-home-flexipage2/forcegenerated-flexipage_account_rec_l_account__view_js___lmt___1750956660000/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/flexipage-component2/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_account___012000000000000aaa___compact___view___recordlayout2/records-highlights2/div[1]/div[1]/div[3]/div/runtime_platform_actions-actions-ribbon/ul/li[4]/lightning-button-menu/button/lightning-primitive-icon/svg
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_icon-border-filled fix-slds-button_icon-border-filled slds-button_last']"))).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[text()='Edit']"))).click();
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']")));
        nameField.clear();
        nameField.sendKeys(newAccountName);
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
    }

}
