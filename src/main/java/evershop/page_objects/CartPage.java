package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseComponent {
    By byCheckout = By.cssSelector("a[href='/checkout']");

    @FindBy(css="a[href='/checkout']")
    WebElement checkoutButton;
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCheckout(){
        waitForElementToAppear(byCheckout);
        checkoutButton.click();
    }
}
