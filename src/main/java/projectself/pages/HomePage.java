package projectself.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private final By spinner = By.xpath("//lightning-spinner");
   private final By userMenu = By.xpath("//div[contains(@class, 'profileTrigger') or contains(@class, 'userProfileCardTriggerRoot')]");
   private final By permission = By.xpath("//span[@class='breadcrumbDetail uiOutputText' and text()='Permission Sets']");
   private final By appLauncher = By.xpath("//div[@role='navigation']//button[@title='App Launcher']");
    private final By viewAll = By.xpath("//button[text()='View All']");
    private final By searchAppInput = By.xpath("//input[@placeholder='Search apps or items...']");
    private final By salesApp = By.xpath("//p[normalize-space()='Sales']/ancestor::a");
    private final By oppTab = By.cssSelector("a[title='Opportunities']");

    public boolean isHomePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu)).isDisplayed();
    }

    public void goToSales() throws InterruptedException {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(permission)).isDisplayed();
        Thread.sleep(3000);
        driver.findElement(appLauncher).click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement viewAllButton = driver.findElement(viewAll);
        js.executeScript("arguments[0].click();", viewAllButton);

        Thread.sleep(2000);
        driver.findElement(searchAppInput).sendKeys("Sales");
        Thread.sleep(2000);
        driver.findElement(salesApp).click();
        Thread.sleep(3000);
    }

    public void goToOpportunities() {
        wait.until(ExpectedConditions.elementToBeClickable(oppTab)).click();
    }
}
