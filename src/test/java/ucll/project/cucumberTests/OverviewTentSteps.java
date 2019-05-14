package ucll.project.cucumberTests;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ucll.project.db.DatabaseService;

import java.util.Properties;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class OverviewTentSteps
{
    private DatabaseService service = new DatabaseService(new Properties(), false);
    protected static WebDriver driver;
    private String url;

    @Before
    public void setUp() {
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

        url = "http://localhost:8080/";

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Given("no tents are registered in the app")
    public void no_tents_are_registered_in_the_app() {
        service.removeAllTenten();
        assertEquals(service.getNrOfTenten(), 0);
    }

    @When("the student opens the app")
    public void the_student_opens_the_app() {
        driver.get(url);
    }

    @Then("he can see the message (.*)")
    public void he_can_see_the_message(String string) {
        assertTrue(driver.getPageSource().contains(string));
    }

    @Given("the tent with the title (.*) is registered in the app")
    public void the_tent_with_the_title_Problem_Solving_is_registered_in_the_app(String title) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(driver.getPageSource().contains(title));
    }

    @Then("he can see the tent (.*) with the description (.*)")
    public void he_can_see_tent_title_with_description(String title, String description) {
        assertTrue(driver.getPageSource().contains(title));
        assertTrue(driver.getPageSource().contains(description));
    }

}
