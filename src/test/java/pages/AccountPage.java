package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "identity-link")
    private WebElement infoButton;

    @FindBy(id = "addresses-link")
    private WebElement addressButton;

    @FindBy(id = "history-link")
    private WebElement ordersButton;

    @FindBy(id = "order-slips-link")
    private WebElement slipsButton;

    public void navigateToInfo() {
        infoButton.click();
    }

    public void navigateToAddresses() {
        addressButton.click();
    }

    public void navigateToOrderHistory() {
        ordersButton.click();
    }

    public void navigateToCreditSlips() {
        slipsButton.click();
    }
}
