package evershop.base_components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComponent {
    @FindBy(css="li[class='nav-item'] a[href='/men']")
    WebElement menLink;
    protected WebDriver driver;
    protected WebDriverWait wait;
    final int WAIT_TIME = 10;

    public BaseComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }
    public void waitForURL(String url){
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitForElementToAppear(By findBy){
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void clickOnMenSection(){
        menLink.click();
    }
}
