package stepsdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Screenshooter;
import utils.WebDriverManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderSteps {

    private final WebDriver driver = WebDriverManager.getDriver();
    private final AccountPage accountPage = new AccountPage(driver);
    private final HomePage homePage = new HomePage(driver);
    private final ProductPage productPage = new ProductPage(driver);
    private final CartPage cartPage = new CartPage(driver);
    private final OrdersPage ordersPage = new OrdersPage(driver);
    private String totalOrderPrice;
    private String referenceNumber;

    @When("User navigates to the {string} product page")
    public void userOpensHummingbirdPrintedSweaterProductPage(String productName) {
        accountPage.clickLogoButton();
        homePage.clickProduct(productName);
    }

    @And("User validates that a {string} discount is applied")
    public void userValidatesThatADiscountIsApplied(String discount) {
        String regularPrice = productPage.getRegularPrice().replaceAll("[^0-9]", "");
        String discountedPrice = productPage.getDiscountedPrice().replaceAll("[^0-9]", "");

        BigDecimal regPrice = new BigDecimal(regularPrice);
        BigDecimal discPrice = new BigDecimal(discountedPrice);
        BigDecimal discountPercentage = regPrice.subtract(discPrice)
                .divide(regPrice, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));

        String actualDiscount = discountPercentage.setScale(0, RoundingMode.HALF_UP) + "%";

        assertEquals(discount, actualDiscount);
    }


    @And("User selects size {string}")
    public void userSelectsSize(String size) {
        productPage.selectSize(size);
    }

    @And("User sets the product quantity to {string}")
    public void userSetsTheProductQuantityTo(String quantity) {
        productPage.setQuantity(quantity);
    }

    @And("User adds the item to the cart")
    public void userAddsTheItemToTheCart() {
        productPage.clickAddToCartButton();
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        productPage.clickProceedToCheckoutButton();
        cartPage.clickProceedToCheckoutButton();
    }

    @And("User continues with preselected address")
    public void userContinuesWithPreselectedAddress() {
        cartPage.clickContinueAddressButton();
    }

    @And("User chooses the {string} delivery method")
    public void userChoosesTheDeliveryMethod(String deliveryMethod) {
        cartPage.selectDeliveryOption(deliveryMethod);
    }

    @And("User continues with selected delivery option")
    public void userContinuesWithSelectedDeliveryOption() {
        cartPage.clickContinueDeliveryOptionButton();
    }

    @And("User opts for {string} and confirms the order")
    public void userOptsForPaymentByCheckWithAnObligationToPay(String paymentOption) {
        cartPage.selectPaymentMethod(paymentOption);
        cartPage.clickTermsOfServiceCheckBox();
        cartPage.clickPlaceOrderButton();

        totalOrderPrice = cartPage.getTotalPrice();
        referenceNumber = cartPage.getOrderReference();
        Screenshooter.takeScreenshot(driver);
    }

    @Then("User navigates to the order history")
    public void userNavigatesToTheOrderHistory() {
        cartPage.clickAccountButton();
        accountPage.navigateToOrderHistory();
    }

    @And("User verifies the order is listed with status {string}, correct price and reference")
    public void userVerifiesTheOrderIsListedWithStatusAndTheCorrectPrice(String status) {
        assertEquals(status, ordersPage.getFirstOrderStatus());
        assertEquals(totalOrderPrice, ordersPage.getFirstOrderTotalPrice());
        assertTrue(referenceNumber.contains(ordersPage.getFirstOrderReference()));
    }
}
