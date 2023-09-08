package evershop.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {
    @FindBy(css="input[name='full_name']")
    WebElement fullNameInput;

    @FindBy(css="input[name='email']")
    WebElement emailInput;

    @FindBy(css="input[name='password']")
    WebElement passwordInput;

    @FindBy(css=".form-submit-button button")
    WebElement submitButton;

    WebDriver driver;

    public CreateAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillCreateAccountForm(String firstName, String email, String password){
        fullNameInput.sendKeys(firstName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public void submitForm(){
        submitButton.click();
    }
}
