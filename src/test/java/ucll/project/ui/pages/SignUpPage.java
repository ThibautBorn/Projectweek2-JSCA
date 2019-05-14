/*package ucll.project.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ucll.project.domain.user.Gender;
import ucll.project.domain.user.Role;

public class SignUpPage {

    private WebDriver driver;

    @FindBy(id="emailInput")
    private WebElement emailInput;
    @FindBy(id="usernameInput")
    private WebElement usernameInput;
    @FindBy(id="firstNameInput")
    private WebElement firstNameInput;
    @FindBy(id="lastNameInput")
    private WebElement lastNameInput;
    @FindBy(id="genderDropdown")
    private WebElement genderDropdown;
    @FindBy(id="roleDropdown")
    private WebElement roleDropdown;
    @FindBy(id="dateOfBirthInput")
    private WebElement dateOfBirthInput;
    @FindBy(id="passwordInput")
    private WebElement passwordInput;
    @FindBy(id="repeatPasswordInput")
    private WebElement repeatPasswordInput;
    @FindBy(id="submitButton")
    private WebElement submitButton;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(){
        driver.get("http://localhost:8080/user/signup");
    }

    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }

    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    public void enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }

    public void selectGender(Gender gender){
        Select dropdown = new Select(genderDropdown);
        dropdown.selectByValue(gender.toString());
    }

    public void selectGender(int index){
        Select dropdown = new Select(genderDropdown);
        dropdown.selectByIndex(index);
    }

    public void selectRole(Role role){
        Select dropDown = new Select(roleDropdown);
        dropDown.selectByValue(role.toString());
    }

    public void selectRole(int index){
        Select dropDown = new Select(roleDropdown);
        roleDropdown.click();
        dropDown.selectByIndex(index);
    }

    public void enterDateOfBirth(String dateOfBirth){
        dateOfBirthInput.sendKeys(dateOfBirth);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void enterRepeatPassword(String password){
        repeatPasswordInput.sendKeys(password);
    }

    public LoginPage clickSubmitButton(){
        submitButton.click();
        return new LoginPage(driver);
    }

    public String getPageTitle(){
        return driver.findElement(By.id("pageTitle")).getText();
    }

}
*/