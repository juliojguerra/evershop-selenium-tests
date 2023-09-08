package evershop.test_components;

import com.github.javafaker.Faker;
import evershop.page_objects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public WebDriver initializeDriver() throws IOException {
        String browserName = getProp().getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            // Make it run headless
            // ChromeOptions options = new ChromeOptions();
            // WebDriverManager.chromedriver().setup();
            // options.addArguments("headless");

            System.setProperty("webdriver.chrome.driver", "/Users/julioguerra/Documents/chromedriver/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();

        // Add implicit wait
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    public Properties getProp() throws IOException {
        Properties prop = new Properties();

        // Send properties file path (dinamically get the user dir path)
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/evershop/resources/config.properties");
        // Load file
        prop.load(fis);

        return prop;
    }

    public String getRandomEmail() {
        Faker faker = new Faker();

        return faker.internet().emailAddress();
    }

    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication() throws IOException {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        driver.get(getProp().getProperty("baseURL"));

        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
