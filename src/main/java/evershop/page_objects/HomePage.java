package evershop.page_objects;

import evershop.base_components.BaseComponent;
import evershop.resources.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseComponent {

    @FindBy(css="a[href='/account/login'] svg")
    WebElement loginButton;

    WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLogin(){
        loginButton.click();
        return new LoginPage(driver);
    }

    public void goTo(){
        String baseURL = ConfigurationReader.getProperty("baseURL");
        driver.get(baseURL);
    }


}
