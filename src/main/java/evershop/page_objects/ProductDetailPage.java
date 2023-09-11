package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ProductDetailPage extends BaseComponent {
    @FindBy(css="h1[class='product-single-name']")
    WebElement productTitle;

    @FindBy(css="input[name='qty']")
    WebElement quantityInput;

    @FindBy(xpath = "(//ul[contains(@class, 'variant-option-list')])[1]/li/a")
    List<WebElement> sizeOptions;

    @FindBy(xpath = "(//ul[contains(@class, 'variant-option-list')])[2]/li/a")
    List<WebElement> colorOptions;

    @FindBy(css="button[class*='primary']")
    WebElement addToCartButton;
    @FindBy(css=".toast-mini-cart .top-head div:first-child")
    WebElement toastTitle;
    @FindBy(css=".toast-mini-cart .add-cart-popup-button")
    WebElement viewCartButton;

    By byProductSKU = By.cssSelector(".product-single-sku");
    By bySelected = By.cssSelector(".selected");
    By byProductTitle = By.cssSelector("h1[class='product-single-name']");

    By byToastTitle = By.cssSelector(".toast-mini-cart .top-head div:first-child");
    WebDriver driver;
    public ProductDetailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle(){
        waitForElementToAppear(byProductTitle);
        return productTitle.getText();
    }

    public void setQuantity(String qty){
        quantityInput.clear();
        quantityInput.sendKeys(qty);
    }

    public void selectRandomSizeAndColor(){
        // Create a random number generator
        Random random = new Random();

        // Generate random indices for size and color
        int randomSizeIndex = random.nextInt(sizeOptions.size());
        int randomColorIndex = random.nextInt(colorOptions.size());

        // Click on the random size and color
        sizeOptions.get(randomSizeIndex).click();
        colorOptions.get(randomColorIndex).click();
    }

    public void addProductToCart(){
        waitForElementToAppear(byProductSKU);
        waitForElementToAppear(bySelected);
        addToCartButton.click();
    }

    public String getWindowTitle(){
        waitForElementToAppear(byToastTitle);
        return toastTitle.getText();
    }

    public void clickOnViewCart(){
        viewCartButton.click();
    }
}
