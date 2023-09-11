package evershop.tests;

import evershop.page_objects.*;
import evershop.test_components.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrderTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void createOrder(Map<String, String> data) throws InterruptedException, IOException {
        SoftAssert softAssert = new SoftAssert();

        // Create Account
        LoginPage loginPage = homePage.clickLogin();
        loginPage.clickCreateAnAccount();

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.fillCreateAccountForm(data.get("firstName"), data.get("email"), data.get("password"));

        createAccountPage.submitForm();

        // Login
        homePage.clickLogin();

        loginPage.login(data.get("email"), data.get("password"));

        homePage.visitMenSection();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectProduct(data.get("productName1"));

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        // Assert popup window
        softAssert.assertTrue(productDetailPage.getProductTitle().equalsIgnoreCase(data.get("productName1")));

        productDetailPage.setQuantity(data.get("product1Qty"));
        productDetailPage.selectRandomSizeAndColor();
        productDetailPage.addProductToCart();

        softAssert.assertTrue(productDetailPage.getWindowTitle().equalsIgnoreCase("Just added to your cart"), "Error: Toast Window not present");

        productDetailPage.visitMenSection();
        productsPage.selectProduct(data.get("productName2"));

        // Assert popup window
        softAssert.assertTrue(productDetailPage.getProductTitle().equalsIgnoreCase(data.get("productName2")));

        productDetailPage.setQuantity(data.get("product2Qty"));

        productDetailPage.selectRandomSizeAndColor();
        productDetailPage.addProductToCart();

        softAssert.assertTrue(productDetailPage.getWindowTitle().equalsIgnoreCase("Just added to your cart"));

        productDetailPage.visitMenSection();

        productsPage.selectProduct(data.get("productName3"));

        softAssert.assertTrue(productDetailPage.getProductTitle().equalsIgnoreCase(data.get("productName3")));

        productDetailPage.setQuantity(data.get("product3Qty"));
        productDetailPage.selectRandomSizeAndColor();
        productDetailPage.addProductToCart();

        // Assert popup window
        softAssert.assertTrue(productDetailPage.getWindowTitle().equalsIgnoreCase("Just added to your cart"));

        productDetailPage.clickOnViewCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickOnCheckout();

        // Fill Shipping Form
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillShippingForm(
                data.get("fullName"),
                data.get("phone"),
                data.get("streetAddress"),
                data.get("country"),
                data.get("province"),
                data.get("postcode")
        );

        softAssert.assertTrue(checkoutPage.getStandardDeliveryRadioButton().isDisplayed(), "Radio button was not checked");

        checkoutPage.submitForm();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.selectVisaPayment();
        paymentPage.fillVisaCardDetails();
        paymentPage.submitPaymentForm();

        SuccessCheckoutPage successCheckoutPage = new SuccessCheckoutPage(driver);
        Map<String, String> orderInfo = successCheckoutPage.getOrderInformation();

        // Assert Billing, Shipping and Contact information
        softAssert.assertTrue(data.get("streetAddress")
                .equalsIgnoreCase(orderInfo.get("Order-ShippingStreetAddress")),
                "Order Shipping Street Address is not correct");

        softAssert.assertTrue(data.get("email")
                .equalsIgnoreCase(orderInfo.get("Order-Email")),
                "Order Email is not correct");

        softAssert.assertTrue(data.get("postcode")
                .equalsIgnoreCase(orderInfo.get("Order-ShippingPostCode")),
                "Order Postcode is not correct");

        softAssert.assertTrue(data.get("paymentMethod")
                .equalsIgnoreCase(orderInfo.get("Order-PaymentMethod")),
                "Order Payment method is not correct");

        softAssert.assertTrue(data.get("streetAddress")
                .equalsIgnoreCase(orderInfo.get("Order-BillingStreetAddress")),
                "Order Billing Street Address is not correct");

        softAssert.assertTrue(data.get("postcode")
                .equalsIgnoreCase(orderInfo.get("Order-BillingPostCode")),
                "Order Postcode is not correct");

        // Assert product items
        List<String> orderProductNames = getProductsList(orderInfo);

        softAssert.assertTrue(orderProductNames.contains(data.get("productName1")),
                data.get("productName1") + "is not present in Success page");
        softAssert.assertTrue(orderProductNames.contains(data.get("productName2")),
                data.get("productName2") + "product is not present in Success page");
        softAssert.assertTrue(orderProductNames.contains(data.get("productName3")),
                data.get("productName3") + "product is not present in Success page");

        softAssert.assertAll();
    }

    // Pending to move this test data into a JSON
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
            {
                new HashMap<String, String>() {{
                    put("email", getRandomEmail());
                    put("password", "123456");
                    put("firstName", "Julio");
                    put("productName1", "Nike react infinity run flyknit");
                    put("product1Qty", "2");
                    put("productName2", "Seasonal color chuck 70");
                    put("product2Qty", "3");
                    put("productName3", "Nizza trefoil shoes");
                    put("product3Qty", "1");
                    put("fullName", "Julio Guerra");
                    put("phone", "555 555 555");
                    put("streetAddress", "Welcome Street");
                    put("country", "United States");
                    put("province", "Colorado");
                    put("postcode", "46273");
                    put("paymentMethod", "Credit Card");
                }}
            }
        };
    }
}
