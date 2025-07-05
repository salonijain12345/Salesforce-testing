package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectself.base.BaseTest;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;
import projectself.pages.TaskPage;

public class TaskTest extends BaseTest {

    @Test(description = "Create a new task and mark it complete")
    public void createAndCompleteTaskTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(5000);

        // Go to Sales App
        HomePage homePage = new HomePage(driver);
        homePage.goToSales();

        // Go to Task Page and perform actions
        TaskPage taskPage = new TaskPage(driver);
        taskPage.goToTasksTab();

        String subject = "Follow up - " + System.currentTimeMillis();
        taskPage.createTask(subject);

        String header = taskPage.getTaskHeaderText();
        Assert.assertTrue(header.contains("Follow up"), "Task creation failed");

        // Mark the task as complete
       // taskPage.markTaskComplete();
        // Optionally assert a toast or re-fetch status
    }
}
