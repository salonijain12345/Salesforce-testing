package projectself.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private final By profileIcon = By.xpath("//span[@class='uiImage']"); // may vary
    private final By logoutButton = By.xpath("//a[text()='Log Out']");

    public void logout() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public boolean isLoggedOut() {
        return wait.until(ExpectedConditions.urlContains("salesforce"));
    }
}