package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends BaseComponent {

    @FindBy(css=".grid .listing-tem")
    List<WebElement> productCards;
    WebDriver driver;
    public ProductsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectProduct(String productName){
        // Find product card with corresponding productName
        WebElement productElement = productCards.stream().filter(product ->
                product.findElement(By.cssSelector("a span")).getText().equalsIgnoreCase(productName)
        ).findFirst().orElse(null);

        productElement.findElement(By.cssSelector("a span")).click();
    }
}
