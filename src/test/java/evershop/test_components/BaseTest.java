package evershop.test_components;

import com.github.javafaker.Faker;
import evershop.page_objects.HomePage;
import evershop.resources.ConfigurationReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public WebDriver initializeDriver() throws IOException {

        String browserName = ConfigurationReader.getProperty("browser");
        WebDriver driver;

        // Use WebDriverManager to manage WebDriver executable
        switch (browserName.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                // Uncomment for headless mode
                // chromeOptions.addArguments("headless");
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();

        return driver;
    }

    public String getRandomEmail() {
        Faker faker = new Faker();

        return faker.internet().emailAddress();
    }

    public List<String> getProductsList(Map<String, String> orderInfo){
        List<String> orderProductNames = new ArrayList<String>();

        orderProductNames.add(orderInfo.get("Order-ProductName1"));
        orderProductNames.add(orderInfo.get("Order-ProductName2"));
        orderProductNames.add(orderInfo.get("Order-ProductName3"));

        return orderProductNames;
    }

    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication() throws IOException {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        homePage.goTo();

        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("driver.close() error");
        }

        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("driver.quit() error");
        }
    }
}
