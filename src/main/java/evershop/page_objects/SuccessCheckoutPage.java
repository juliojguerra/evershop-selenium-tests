package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessCheckoutPage extends BaseComponent {
    @FindBy(xpath = "//span[contains(text(),'Checkout success')]")
    WebElement checkoutSuccessSubtitle;
    By byCheckoutSuccessSubtitle = By.xpath("//span[contains(text(),'Checkout success')]");
    @FindBy(xpath = "//div[contains(text(),'Contact information')]/following-sibling::div")
    WebElement contactInformationEmail;

    @FindBy(xpath = "//div[contains(text(),'Payment Method')]/following-sibling::div")
    WebElement paymentInfoMethod;
    @FindBy(xpath = "(//div[@class='address-one'])[1]")
    WebElement shippingStreetAddress;
    @FindBy(xpath = "(//div[@class='city-province-postcode'])[1]/div[1]")
    WebElement shippingPostCode;
    @FindBy(xpath = "(//div[@class='address-one'])[2]")
    WebElement billingStreetAddress;
    @FindBy(xpath = "(//div[@class='city-province-postcode'])[2]/div[1]")
    WebElement billingPostCode;
    @FindBy(xpath = "//table[@class='listing items-table']//span[@class='font-semibold']")
    List<WebElement> productsElements;
    WebDriver driver;
    public SuccessCheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Map<String, String> getOrderInformation(){
        waitForElementToAppear(byCheckoutSuccessSubtitle);

        Map<String, String> orderInfo = new HashMap<>();

        // Contact Information
        orderInfo.put("Order-Email", contactInformationEmail.getText());

        // Payment Information
        orderInfo.put("Order-PaymentMethod", paymentInfoMethod.getText());

        // Shipping Address
        orderInfo.put("Order-ShippingStreetAddress", shippingStreetAddress.getText());

        orderInfo.put("Order-ShippingPostCode", shippingPostCode.getText().split(",")[0]);

        // Pending Country, Province

        // Billing Address
        orderInfo.put("Order-BillingStreetAddress", billingStreetAddress.getText());
        orderInfo.put("Order-BillingPostCode", billingPostCode.getText().split(",")[0]);

        // Add and enumerate item names
        for (int i = 0; i < productsElements.size(); i++) {
            WebElement product = productsElements.get(i);
            orderInfo.put("Order-ProductName" + (i + 1), product.getText());
        }

        return orderInfo;
    }
}
