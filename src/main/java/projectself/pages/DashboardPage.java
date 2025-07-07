package projectself.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By dashboardsTab = By.xpath("//a[@title='Dashboards']");
    private By firstDashboard = By.xpath("//a[@title='first']");
    private By iframeLocator = By.cssSelector("iframe[title='dashboard']");
    private By dashboardHeader = By.xpath("//span[@class='slds-page-header__title slds-truncate']");
    private By widgetContainer = By.cssSelector("div.dashboardComponent");

    public void goToDashboardsTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(dashboardsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("lightning-spinner")));
    }

    public void openFirstDashboard() {
    	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("lightning-spinner")));

    	
    	WebElement link = wait.until(ExpectedConditions.elementToBeClickable(firstDashboard));

    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
    }

    public void switchToDashboardIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframe);
    }

    public String getDashboardTitle() {
        switchToDashboardIframe();
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        return header.getText();
    }

    public boolean areWidgetsVisible() {
        switchToDashboardIframe();
        return !driver.findElements(widgetContainer).isEmpty();
    }
}
