package projectself.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpportunityPage {
    WebDriver driver;
    WebDriverWait wait;

    public OpportunityPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void goToOpportunities() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Opportunities']"))).click();
    }

    public void createOpportunity(String name, String stage, String date) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']"))).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(date);
        driver.findElement(By.xpath("//button[@aria-label='Stage, --None--']")).click();
        driver.findElement(By.xpath("//span[@title='" + stage + "']")).click();
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
    }

    public boolean verifyOpportunityCreated(String name) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//lightning-formatted-text[contains(@class,'custom-truncate')]"), name));
    }
}
