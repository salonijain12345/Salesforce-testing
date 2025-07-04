package projectself.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {
    WebDriver driver;
    WebDriverWait wait;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By contactTab = By.xpath("//a[@title='Contacts']");
    By newButton = By.xpath("//button[@name='NewContact']");
    By lastName = By.xpath("//input[@name='lastName']");
    By accName = By.xpath("//input[@placeholder='Search Accounts...']");
    By save = By.xpath("//button[@name='SaveEdit']");
    By header = By.xpath("//slot[@name='primaryField']//lightning-formatted-name");
    By salutationDropdown = By.xpath("//button[@name='salutation']");
    By salutationOptionMr = By.xpath("//lightning-base-combobox-item[@data-value='Ms.']");
   
    public void goToContactsTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By contactsTabLocator = By.xpath("//a[@title='Contacts']");

        // Wait until the tab is visible
        WebElement contactsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(contactsTabLocator));

        // Use JavaScript to click (bypasses some Salesforce UI issues)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", contactsTab);

        // Optionally wait for spinner or tab load to complete
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("lightning-spinner")));
    }
   
    public void createContactFromContactsTab(String lastNameVal, String accountNameVal) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("lightning-spinner")));

        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(salutationDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(salutationOptionMr)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lastNameVal);

        WebElement accInput = wait.until(ExpectedConditions.visibilityOfElementLocated(accName));
        accInput.sendKeys(accountNameVal);

        String accountName = "TestCompany1751134963107";

     // 1. Dynamic XPath to find the suggestion with given account name
     By accountOptionBy = By.xpath("//lightning-base-combobox-item[.//lightning-base-combobox-formatted-text[@title='" + accountName + "']]");

     // 2. Wait for the suggestion to be present
  
     WebElement accountOption = wait.until(ExpectedConditions.presenceOfElementLocated(accountOptionBy));

     // 3. Click using JavaScriptExecutor for reliability
     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("arguments[0].scrollIntoView(true);", accountOption);
     js.executeScript("arguments[0].click();", accountOption);

        driver.findElement(save).click();
    }


    public String getContactHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }
    
    public void editContact(String oldName, String newName) {
        searchAndOpenContact(oldName);
        
      
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(text(),'Show more actions')]]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='menuitem']/span[text()='Edit']"))).click();
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']")));
        nameField.clear();
        nameField.sendKeys(newName);
        wait.until(ExpectedConditions.elementToBeClickable(save)).click();
    }
    
    public void deleteContact(String contactName) {
        searchAndOpenContact(contactName);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(text(),'Show more actions')]]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='menuitem']/span[text()='Delete']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Delete' and .//span[text()='Delete']]"))).click();

    }
    
    
    // ====== Search Contact ======
    public boolean searchContact(String contactName) {
        try {
            // Step 1: Click the global search button
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'search-button') and @aria-label='Search']")));
            searchBtn.click();

            // Step 2: Enter contact name and hit Enter
            WebElement inputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='search' and @placeholder='Search...']")));
            inputBox.clear();  // Optional: Clears previous search input
            inputBox.sendKeys(contactName, Keys.ENTER);

            // Step 3: Wait for result link and return success
            By result = By.xpath("//a[contains(text(),'" + contactName + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(result));

            System.out.println("✅ Contact found: " + contactName);
            return true;

        } catch (TimeoutException e) {
            System.out.println("❌ Timeout: Contact not found within time: " + contactName);
            return false;

        } catch (Exception e) {
            System.out.println("❌ Error while searching for contact: " + contactName);
            e.printStackTrace();
            return false;
        }
    }


    
    private void searchAndOpenContact(String contactName) {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'search-button') and @aria-label='Search']")));
        searchBtn.click();

        WebElement inputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='search' and @placeholder='Search...']")));
        inputBox.sendKeys(contactName, Keys.ENTER);

        By result = By.xpath("//a[contains(text(),'" + contactName + "')]");
        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(result));
        contactLink.click();
    }
    
    
}
