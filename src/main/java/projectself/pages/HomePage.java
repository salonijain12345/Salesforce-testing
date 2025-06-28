package projectself.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

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
    public boolean isHomePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu)).isDisplayed();
    }

    public void goToSales() throws InterruptedException {
    	 driver.findElement(appLauncher).click();
    	    Thread.sleep(2000);

    	    driver.findElement(viewAll).click();
    	    Thread.sleep(2000);
    	    //driver.findElement(searchAppInput).click();
    	    driver.findElement(searchAppInput).sendKeys("Sales");
    	    
    	    Thread.sleep(2000);

    	    driver.findElement(salesApp).click();
    	    Thread.sleep(3000);    }

   
}
