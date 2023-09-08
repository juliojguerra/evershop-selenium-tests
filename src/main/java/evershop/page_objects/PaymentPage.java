package evershop.page_objects;

import evershop.base_components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseComponent {

    @FindBy(css="form[id='checkoutPaymentForm']")
    WebElement paymentForm;
    @FindBy(css=".payment-method-list svg[class='feather feather-circle']")
    WebElement visaRadioButton;
    @FindBy(xpath="//span[text()='Test success']")
    WebElement testSuccessButton;
    @FindBy(xpath="//div[contains(text(),'Test card number:')]")
    WebElement cardNumElement;
    @FindBy(xpath="//div[contains(text(),'Test card expiry:')]")
    WebElement cardExpElement;

    @FindBy(xpath = "//div[contains(text(),'Test card CVC:')]")
    WebElement cardCVCElement;
    @FindBy(xpath = "//iframe[contains(@name,'privateStripeFrame')]")
    WebElement cardDetailsFrame;
    @FindBy(css="input[aria-label='Credit or debit card number']")
    WebElement cardNumberInput;
    By byCardNumberInput = By.cssSelector("input[aria-label='Credit or debit card number']");
    By byCardExpInput = By.cssSelector("input[aria-label='Credit or debit card expiration date']");
    @FindBy(css="input[name='exp-date']")
    WebElement cardExpDateInput;
    @FindBy(css="input[name='cvc']")
    WebElement cardCVCInput;
    By byCardCVCInput = By.cssSelector("input[aria-label='Credit or debit card CVC/CVV']");
    @FindBy(css="span[class='checkbox-checked']")
    WebElement shippingAndBillingSameCheckbox;
    By byShippingAndBillingSameCheckbox = By.cssSelector("span[class='checkbox-checked']");
    By byCheckoutPaymentForm = By.cssSelector("form[id='checkoutPaymentForm']");
    By byTestSuccessElement = By.xpath("//b[contains(text(),'Test success:')]");
    By byCardDetailsFrame = By.xpath("//iframe[contains(@name,'privateStripeFrame')]");
    By byVisaRadioButton = By.cssSelector("svg[class='feather feather-circle']");
    WebDriver driver;
    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectVisaPayment(){
        waitForElementToAppear(byCheckoutPaymentForm);
        waitForElementToAppear(byVisaRadioButton);
        visaRadioButton.click();
    }

    public void fillVisaCardDetails() throws InterruptedException {
        waitForElementToAppear(byTestSuccessElement);

        // Get card number
        String cardNumText = cardNumElement.getText();
        String cardExpText = cardExpElement.getText();
        String cardCVCText = cardCVCElement.getText();

        String[] cardArr = cardNumText.split("number: ");
        String[] cardExpArr = cardExpText.split("expiry: ");
        String[] cardCVCArr = cardCVCText.split("CVC: ");

        String cardNum = cardArr[1];
        String expDate = cardExpArr[1];
        String cardCVCNum = cardCVCArr[1];

        // Iframe
        waitForElementToAppear(byCardDetailsFrame);

        driver.switchTo().frame(cardDetailsFrame);

        // Thread.sleep(2000);
        waitForElementToAppear(byCardNumberInput);
        waitForElementToAppear(byCardExpInput);
        waitForElementToAppear(byCardCVCInput);

        cardNumberInput.sendKeys(cardNum);
        cardExpDateInput.sendKeys(expDate);
        cardCVCInput.sendKeys(cardCVCNum);

        driver.switchTo().defaultContent();
    }

    public void submitPaymentForm(){
        paymentForm.submit();
    }
}
