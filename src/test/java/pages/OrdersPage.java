package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrdersPage extends BasePage {

    private final By orderStatusLocator = By.cssSelector(".label.label-pill.bright");
    private final By orderReferenceLocator = By.xpath("//th[@scope='row']");
    private final By orderTotalPriceLocator = By.cssSelector(".text-xs-right");

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstOrderStatus() {
        List<WebElement> orderStatuses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderStatusLocator));
        return orderStatuses.getFirst().getText();
    }

    public String getFirstOrderReference() {
        List<WebElement> orderReferences = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderReferenceLocator));
        return orderReferences.getFirst().getText();
    }

    public String getFirstOrderTotalPrice() {
        List<WebElement> orderTotalPrices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderTotalPriceLocator));
        return orderTotalPrices.getFirst().getText();
    }
}
