package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private final By addressButtonLocator = By.id("addresses-link");
    private final By ordersButtonLocator = By.id("history-link");
    private final By logoutButtonLocator = By.cssSelector(".logout");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToAddresses() {
        click(addressButtonLocator);
    }

    public void navigateToOrderHistory() {
        click(ordersButtonLocator);
    }

    public void logout() {
        click(logoutButtonLocator);
    }
}
