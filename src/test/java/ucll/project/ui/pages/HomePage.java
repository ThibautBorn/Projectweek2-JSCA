package ucll.project.ui.pages;

/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    @FindBy(id="pageTitle")
    private WebElement pageTitle;

    @FindBy(id="loginLink")
    private WebElement loginLink;

    @FindBy(id="signUpLink")
    private WebElement signUpLink;

    @FindBy(id="logoutLink")
    private WebElement logoutLink;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public HomePage navigateTo(){
        driver.get("http://localhost:8080/");
        return this;
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public LoginPage clickLogin(){
        loginLink.click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUp(){
        signUpLink.click();
        return new SignUpPage(driver);
    }

    public HomePage clickLogoutLink(){
        logoutLink.click();
        return new HomePage(driver);
    }

    public String getLogoutLinkText(){
        return logoutLink.getText();
    }

}
*/
