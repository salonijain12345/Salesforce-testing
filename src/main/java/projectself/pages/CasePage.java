package projectself.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CasePage {
    WebDriver driver;
    WebDriverWait wait;

    public CasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By casesTab = By.xpath("//a[@title='Cases']");
    private By newButton = By.xpath("//div[@title='New']");
    private By subjectField = By.xpath("//input[@name='Subject']");
    private By statusDropdown = By.xpath("//button[@data-value='New']");
    private By caseOriginDropdown = By.xpath("//button[@data-value='--None--']");
    private By statusOption = By.xpath("//span[@title='New']");
    private By CaseOption = By.xpath("//*[@data-value='Web']");
    private By saveButton = By.xpath("//button[@name='SaveEdit']");
    private By header = By.xpath("//div[@class='slds-align-middle fade-text']");

    public void goToCasesTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By moreTab = By.xpath("//a/span[@class='slds-p-right_small']");
        By casesOption = By.xpath("//a[@role='menuitem']//span[text()='Cases']");
        By spinner = By.cssSelector("lightning-spinner");

        // Wait for More tab and click it
        WebElement moreTabElement = wait.until(ExpectedConditions.elementToBeClickable(moreTab));
        js.executeScript("arguments[0].click();", moreTabElement);

        // Wait for Cases to appear in the dropdown
        WebElement casesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(casesOption));
        js.executeScript("arguments[0].click();", casesTab);

        // Wait for any loading spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }


    public void createCase(String subject) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
       

        // Select Status
//        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
//        WebElement newButton = wait.until(ExpectedConditions.elementToBeClickable(statusOption));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newButton);
        // Select Priority
        wait.until(ExpectedConditions.elementToBeClickable(caseOriginDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(CaseOption)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subjectField)).sendKeys(subject);
        // Save the case
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getCaseHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }
    
    
    
}
