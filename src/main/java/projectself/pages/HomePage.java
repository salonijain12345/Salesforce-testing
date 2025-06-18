package projectself.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By userMenu = By.xpath("//span[@class='userProfileCardTriggerRoot']");
    private By appLauncher = By.cssSelector("div.slds-icon-waffle");
    private By oppTab = By.cssSelector("a[title='Opportunities']");

    public boolean isHomePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu)).isDisplayed();
    }

    public void goToOpportunities() {
        wait.until(ExpectedConditions.elementToBeClickable(oppTab)).click();
    }

    public void openAppLauncher() {
        wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
    }
}

