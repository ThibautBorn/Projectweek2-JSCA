package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FillInTestTest {

    protected static WebDriver driver;
    @BeforeClass
    public static void SetupChromeDriver() {
        // Setup the Chrome driver for the whole class
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // The following arguments are needed for the tests to run successfully in jenkins
        String jenkinsHome = System.getenv("JENKINS_HOME");
        if (jenkinsHome != null && !jenkinsHome.isEmpty()) {
            options.addArguments("--headless", "--no-sandbox", "--window-size=1200,1100");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void VisitHomePageTest() {



    }

    @AfterClass
    public static void CloseChrome() {
        // close it in the end, comment this away to keep chrome open
        driver.close();
    }
}
