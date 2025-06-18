package projectself.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    // Locators
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.id("Login");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    // Optional: Add a method to verify login success/failure
    public boolean isLoginSuccessful() {
        // Replace with a valid post-login element
        return driver.getTitle().contains("Home") || driver.getCurrentUrl().contains("home");
    }
}
