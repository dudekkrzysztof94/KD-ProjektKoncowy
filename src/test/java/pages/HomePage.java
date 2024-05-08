package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By signInButtonLocator = By.xpath("//a[@title='Log in to your customer account']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignInButton() {
        click(signInButtonLocator);
    }

    public void clickProduct(String productName) {
        By productLocator = By.linkText(productName);
        click(productLocator);
    }
}
