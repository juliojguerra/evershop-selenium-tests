package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseComponent {

    @FindBy(css="a[href='/account/register']")
    WebElement createAccountLink;

    @FindBy(css="input[name='email']")
    WebElement emailInput;

    @FindBy(css="input[name='password']")
    WebElement passwordInput;

    @FindBy(css=".button.primary")
    WebElement loginButton;

    By byMenShoesCollectionTitle = By.xpath("(//h3[contains(text(),'Men shoes collection')])[2]");

    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAnAccount(){
        createAccountLink.click();
    }

    public void login(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        waitForElementToAppear(byMenShoesCollectionTitle);
    }
}
