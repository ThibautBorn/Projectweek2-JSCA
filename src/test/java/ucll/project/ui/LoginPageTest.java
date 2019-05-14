/*package ucll.project.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;

import static org.junit.Assert.assertEquals;

public class LoginPageTest extends BaseUITest {

    @Test
    public void LoginTestExpectSuccessV1() {

        driver.get("http://localhost:8080/user/login");
        driver.findElement(By.id("usernameInput")).sendKeys("user");
        driver.findElement(By.id("passwordInput")).sendKeys("user");
        driver.findElement(By.id("submitButton")).click();

        String pageTitle = driver.findElement(By.id("pageTitle")).getText();
        assertEquals("Hello world!", pageTitle);

    }

    @Test
    public void LoginTestExpectSuccessV2() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        HomePage homePage = loginPage.loginAs("user", "user");
        assertEquals("Hello world!", homePage.getPageTitle());

    }

    @Test
    public void loginTestWithInvalidUsernameExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("invalid", "user");
        assertEquals("Please sign in", loginPage.getPageTitle());
        assertEquals("Invalid username", loginPage.getErrorMessage());

    }

    @Test
    public void loginTestWithoutUsernameExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("", "user");
        assertEquals("Please sign in", loginPage.getPageTitle());

    }

    @Test
    public void loginTestWithInvalidPasswordExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("user", "invalid");
        assertEquals("Please sign in", loginPage.getPageTitle());
        assertEquals("Invalid password", loginPage.getErrorMessage());

    }

    @Test
    public void loginTestWithoutPasswordExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("user", "");
        assertEquals("Please sign in", loginPage.getPageTitle());

    }

}
*/
