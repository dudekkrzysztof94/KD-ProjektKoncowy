package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrdersPage extends BasePage {
    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    @FindAll(@FindBy(xpath = "//th[@scope='row']"))
    private List<WebElement> referenceNumbers;

    @FindAll(@FindBy(css = ".label.label-pill.bright"))
    private List<WebElement> orderStatus;

    @FindAll(@FindBy(css = ".text-xs-right"))
    private List<WebElement> orderTotalPrices;

    public WebElement returnLatestReferenceNumber() {
        return referenceNumbers.get(0);
    }

    public WebElement returnLatestOrderStatus() {
        return orderStatus.get(0);
    }

    public WebElement returnLatestOrderTotalPrice() {
        return orderTotalPrices.get(0);
    }
}
