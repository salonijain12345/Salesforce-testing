package projectself.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ReportsPage {
    WebDriver driver;
    WebDriverWait wait;

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By reportsTab = By.xpath("//a[@title='Reports']");
    private By newReportBtn = By.xpath("//div[@title='New Report' and text()='New Report']");
    private By searchReportTypeInput = By.xpath("//input[@placeholder='Search Report Types...']");
    private By contactsReportType = By.xpath("//p[contains(@class,'slds-truncate') and text()='Accounts']");
    private By continueBtn = By.xpath("//button[@title='Continue']");
  
    private By startBtn = By.xpath("//button[@id='start-report-btn']");
    private By saveBtn = By.xpath("//button[@title='Save']");
    private By reportNameField = By.xpath("//div[@class='panelItemGroup leftPanelItems']");
    private By saveAndRunBtn = By.xpath("//button[text()='Save & Run']");
    private By reportHeader = By.cssSelector("h1[class*='reportName']");
    private By reportname= By.xpath("//div[@class='header-renderables']");
    private By close = By.xpath("//button[@title='Close' and contains(@class,'slds-modal__close')][2]");
    public void goToReportsTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(reportsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("lightning-spinner")));
        //wait.until(ExpectedConditions.elementToBeClickable(close)).click();
      

    }

    public void createAndSaveReport(String reportName) throws InterruptedException {
    	Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(newReportBtn)).click();
        Thread.sleep(4000);
        
        WebElement reportIframe = wait.until(ExpectedConditions.presenceOfElementLocated(
        	    By.xpath("//iframe[contains(@title, 'Report')]") // or modify according to actual title or ID
        	));

        	// Then switch
        	driver.switchTo().frame(reportIframe);
        	
       //wait.until(ExpectedConditions.elementToBeClickable(close)).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(searchReportTypeInput)).sendKeys("Accounts");
         wait.until(ExpectedConditions.elementToBeClickable(contactsReportType)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(startBtn)).click();
       // wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        Thread.sleep(3000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(reportNameField)).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(reportname)).sendKeys(reportName);
        wait.until(ExpectedConditions.elementToBeClickable(saveAndRunBtn)).click();
    }

    public String getReportHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(reportHeader)).getText();
    }
}
