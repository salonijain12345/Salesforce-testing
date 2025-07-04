package projectself.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
>>>>>>> ef976e4 (Initial Salesforce CRM automation framework setup with login test)
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties prop;

    @BeforeMethod
    public WebDriver setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "\\src\\main\\java\\resources\\globaldata.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : prop.getProperty("browser");

        System.out.println("Launching browser: " + browserName);

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
<<<<<<< HEAD
            
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 1);
            options.setExperimentalOption("prefs", prefs);
            
=======
>>>>>>> ef976e4 (Initial Salesforce CRM automation framework setup with login test)
            WebDriverManager.chromedriver().setup();

            if (browserName.contains("headless")) {
                options.addArguments("--headless=new");
            }
            if (browserName.contains("incognito")) {
                options.addArguments("--incognito");
            }

            driver = new ChromeDriver(options);
<<<<<<< HEAD
            WebDriverManager.chromedriver().browserVersion("137.0.7151.120").setup();
=======
>>>>>>> ef976e4 (Initial Salesforce CRM automation framework setup with login test)
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();  // changed from close() to quit() for full cleanup
        }
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, destination);
        return destination.getAbsolutePath();
    }
}
