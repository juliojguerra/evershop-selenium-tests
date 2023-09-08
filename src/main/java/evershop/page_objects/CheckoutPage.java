package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BaseComponent {
    @FindBy(css="form[id='checkoutShippingAddressForm']")
    WebElement shippingForm;
    @FindBy(css="input[name='address[full_name]']")
    WebElement fullNameInput;
    By byFullNameInput = By.cssSelector("input[name='address[full_name]']");
    @FindBy(css="input[name='address[telephone]']")
    WebElement telephoneInput;
    By byTelephoneInput = By.cssSelector("input[name='address[telephone]']");
    @FindBy(css="input[name='address[address_1]']")
    WebElement streetAddressInput;
    By byStreetAddressInput = By.cssSelector("input[name='address[address_1]']");
    @FindBy(css="select[name='address[country]']")
    WebElement countrySelect;
    @FindBy(css="select[name='address[province]']")
    WebElement provinceSelect;
    @FindBy(css="input[name='address[postcode]']")
    WebElement postcodeInput;
    @FindBy(css="label[for='method0']")
    WebElement standardDeliveryRadioButton;
    @FindBy(css="label[for='method0'] .radio-checked")
    WebElement standardDeliveryRadioChecked;
    By byShippingAddressForm = By.cssSelector("form[id='checkoutShippingAddressForm']");
    By byStandardDeliveryRadioButton = By.cssSelector("label[for='method0']");
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillShippingForm(String fullName, String phone, String streetAddress, String country, String province, String postcode){
        waitForElementToAppear(byShippingAddressForm);

        waitForElementToAppear(byFullNameInput);
        fullNameInput.sendKeys(fullName);
        waitForElementToAppear(byTelephoneInput);
        telephoneInput.sendKeys(phone);
        waitForElementToAppear(byStreetAddressInput);
        streetAddressInput.sendKeys(streetAddress);

        Select countryDropdown = new Select(countrySelect);
        countryDropdown.selectByVisibleText(country);

        Select provinceDropdown = new Select(provinceSelect);
        provinceDropdown.selectByVisibleText(province);
        postcodeInput.sendKeys(postcode);

        // Shipping Method
        waitForElementToAppear(byStandardDeliveryRadioButton);
        standardDeliveryRadioButton.click();
    }

    public WebElement getStandardDeliveryRadioButton(){
        return standardDeliveryRadioChecked;
    }

    public void submitForm(){
        shippingForm.submit();
    }
}
