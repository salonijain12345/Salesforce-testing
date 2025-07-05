package projectself.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class TaskPage {
    WebDriver driver;
    WebDriverWait wait;

    public TaskPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By tasksTab = By.xpath("//a[@title='Tasks']");
    private By newTaskButton = By.xpath("//a[@title='New Task']");
    private By subjectField = By.xpath("//input[@class='slds-combobox__input slds-input']");
    private By statusDropdown = By.xpath("//a[@class='select']");
    private By InProgressStatusOption= By.xpath("//a[@title='In Progress']");
    private By completedStatusOption = By.xpath("//a[@title='Completed']");
    private By saveButton = By.xpath("//span[text()='Save']/ancestor::button");
    private By headerText = By.xpath("//*[@id=\"brandBand_2\"]/div/div/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[1]/div/header/div[2]/div/div[1]/div[2]/h1/div[2]");
    private By moreButton = By.xpath("//a[@title='Show 2 more actions']");
    public void goToTasksTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By tasksTabLocator = By.xpath("//a[@title='Tasks']");

        // Wait until the Tasks tab is visible
        WebElement tasksTab = wait.until(ExpectedConditions.visibilityOfElementLocated(tasksTabLocator));

        // Use JavaScript to click to handle potential overlay issues
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", tasksTab);

        // Wait until the spinner disappears (if any)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("lightning-spinner")));
    }

    public void createTask(String subject) {
    	wait.until(ExpectedConditions.elementToBeClickable(moreButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(newTaskButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(InProgressStatusOption)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subjectField)).sendKeys(subject);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void markTaskComplete() {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(completedStatusOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getTaskHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerText)).getText();
    }
}
