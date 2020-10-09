package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='text-sm-center']/a")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".btn.btn-primary.continue.float-xs-right")
    private WebElement confirmAddressButton;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryOptionButton;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckRadio;

    @FindBy(name = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsOfServiceCheckBox;

    @FindBy(css = ".btn.btn-primary.center-block")
    private WebElement orderWithObligationToPayButton;

    @FindBy(xpath = "//tr[@class='font-weight-bold']/td[last()]")
    private WebElement totalPrice;

    @FindBy(css = ".account")
    private WebElement accountPageLink;

    @FindBy(xpath = "//div[@id='order-details']/ul/li")
    private WebElement orderReference;

    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public void confirmAddress() {
        confirmAddressButton.click();
    }

    public void confirmDeliveryOption() {
        confirmDeliveryOptionButton.click();
    }

    public void selectPayByCheck() {
        payByCheckRadio.click();
    }

    public void agreeToTermsOfService() {
        termsOfServiceCheckBox.click();
    }

    public void orderWithObligationToPay() {
        orderWithObligationToPayButton.click();
    }

    public String returnTotalOrderPrice() {
        return totalPrice.getText();
    }

    public void goToAccountPage() {
        accountPageLink.click();
    }

    public String returnOrderReferenceNumber() {
        return  orderReference.getText().substring(17);
    }
}
