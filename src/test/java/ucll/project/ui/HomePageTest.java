/*package ucll.project.ui;

import org.junit.Test;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;
import ucll.project.ui.pages.SignUpPage;

import static org.junit.Assert.assertEquals;

public class HomePageTest extends BaseUITest {

    @Test
    public void VisitHomePageTest() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        assertEquals("Hello world!", homePage.getPageTitle());

    }

    @Test
    public void visitLoginPageFromHomePageTest() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        LoginPage loginPage = homePage.clickLogin();
        assertEquals("Please sign in", loginPage.getPageTitle());

    }

    @Test
    public void visitSignUpPageFromHomePageTest() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        SignUpPage signUpPage = homePage.clickSignUp();
        assertEquals("Create an account", signUpPage.getPageTitle());

    }

}
*/