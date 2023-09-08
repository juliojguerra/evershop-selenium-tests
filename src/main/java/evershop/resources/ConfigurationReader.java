package evershop.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/evershop/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

