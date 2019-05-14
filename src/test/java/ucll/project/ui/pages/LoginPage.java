/*package ucll.project.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id="pageTitle")
    private WebElement pageTitle;

    @FindBy(id="errorMessage")
    private WebElement errorMessage;

    @FindBy(id="usernameInput")
    private WebElement usernameInput;

    @FindBy(id="passwordInput")
    private WebElement passwordInput;

    @FindBy(id="submitButton")
    private WebElement submitButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(){
        driver.get("http://localhost:8080/user/login");
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    private void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    private void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    private HomePage clickLoginButton() {
        submitButton.click();
        return new HomePage(driver);
    }

    public HomePage loginAs(String username,String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new HomePage(driver);
    }

    public LoginPage loginAsExpectingError(String username,String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new LoginPage(driver);
    }



}
*/