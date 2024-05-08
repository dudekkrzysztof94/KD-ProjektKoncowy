package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private final By proceedToCheckoutButtonLocator = By.linkText("PROCEED TO CHECKOUT");
    private final By continueAddressButtonLocator = By.name("confirm-addresses");
    private final By deliveryOptionRadioLocator = By.className("carrier-name");
    private final By continueDeliveryOptionButtonLocator = By.name("confirmDeliveryOption");
    private final By paymentOptionLocator = By.xpath("//div[@class='payment-option clearfix']/label/span");
    private final By termsOfServiceCheckBoxLocator = By.id("conditions_to_approve[terms-and-conditions]");
    private final By placeOrderButtonLocator = By.cssSelector(".btn.btn-primary.center-block");
    private final By totalPriceLocator = By.xpath("//tr[contains(@class, 'total-value')]/td[last()]");
    private final By orderReferenceLocator = By.id("order-reference-value");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickProceedToCheckoutButton() {
        click(proceedToCheckoutButtonLocator);
    }

    public void clickContinueAddressButton() {
        click(continueAddressButtonLocator);
    }

    public void selectDeliveryOption(String deliveryOption) {
        List<WebElement> deliveryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deliveryOptionRadioLocator));
        deliveryOptions.stream()
                .filter(option -> option.getText().equals(deliveryOption))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickContinueDeliveryOptionButton() {
        click(continueDeliveryOptionButtonLocator);
    }

    public void selectPaymentMethod(String paymentMethod) {
        driver.findElements(paymentOptionLocator).stream()
                .filter(e -> e.getText().equals(paymentMethod))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickTermsOfServiceCheckBox() {
        WebElement termsOfServiceCheckBox = driver.findElement(termsOfServiceCheckBoxLocator);
        termsOfServiceCheckBox.click();
    }

    public void clickPlaceOrderButton() {
        click(placeOrderButtonLocator);
    }

    public String getTotalPrice() {
        WebElement totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceLocator));
        return totalPrice.getText();
    }

    public String getOrderReference() {
        WebElement orderReference = wait.until(ExpectedConditions.visibilityOfElementLocated(orderReferenceLocator));
        return orderReference.getText();
    }
}
