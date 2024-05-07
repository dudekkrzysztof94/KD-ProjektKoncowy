package utils;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static ChromeDriver driver;

    public static ChromeDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
