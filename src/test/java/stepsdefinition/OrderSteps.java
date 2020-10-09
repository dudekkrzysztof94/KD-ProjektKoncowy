package stepsdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class OrderSteps {
    private static WebDriver driver;
    private String totalOrderPrice;
    private String referenceNumber;
    private static LocalDateTime now;

    @Given("User is logged into CodersLab shop")
    public void userIsLoggedIntoCodersLabShop() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski01@mymail.de", "Pass1231");
    }

    @When("User opens Hummingbird Printed Sweater product page")
    public void userOpensHummingbirdPrintedSweaterProductPage() {
        driver.get("https://prod-kurs.coderslab.pl/index.php?id_product=2&id_product_attribute=9&rewrite=brown-bear-printed-sweater&controller=product");
    }

    @And("Validates the {string} discount")
    public void validatesTheDiscount(String arg0) {
        ProductPage productPage = new ProductPage(driver);

        double discount = Double.parseDouble(arg0.substring(0, 2));
        Assert.assertEquals(discount, productPage.calculateDiscount(), 0.01);
    }

    @And("Selects {string} size")
    public void selectsSize(String arg0) {
        ProductPage productPage = new ProductPage(driver);

        productPage.selectSize(arg0);
    }

    @And("Sets product quantity to {string}")
    public void setsProductQuantityTo(String arg0) throws InterruptedException{
        ProductPage productPage = new ProductPage(driver);

        productPage.setQuantity(arg0);
    }

    @And("Adds item to the cart")
    public void addsItemToTheCart() {
        ProductPage productPage = new ProductPage(driver);

        productPage.addToCart();
    }

    @And("Proceeds to checkout")
    public void proceedsToCheckout() {
        ProductPage productPage = new ProductPage(driver);
        productPage.placeOrderAndCheckout();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
    }

    @And("Confirms address")
    public void confirmsAddress() {
        CartPage cartPage = new CartPage(driver);

        cartPage.confirmAddress();
    }

    @And("Selects pick up in-store delivery method")
    public void selectsPickUpInStoreDeliveryMethod() {
        CartPage cartPage = new CartPage(driver);

        cartPage.confirmDeliveryOption();
    }

    @And("Selects payment by check with obligation to pay")
    public void selectsPaymentByCheckWithObligationToPay() {
        CartPage cartPage = new CartPage(driver);

        cartPage.selectPayByCheck();
        cartPage.agreeToTermsOfService();
        cartPage.orderWithObligationToPay();
    }

    @Then("User takes a screenshot of confirmation page")
    public void userTakesAScreenshotOfConfirmationPage() throws IOException {
        CartPage cartPage = new CartPage(driver);

        totalOrderPrice = cartPage.returnTotalOrderPrice();
        referenceNumber = cartPage.returnOrderReferenceNumber();

        now = LocalDateTime.now();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshots/screenshot"+now));
    }

    @And("Navigates to order history")
    public void navigatesToOrderHistory() {
        CartPage cartPage = new CartPage(driver);
        cartPage.goToAccountPage();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.navigateToOrderHistory();
    }

    @And("Confirms orders is placed with {string} status and correct price")
    public void confirmsOrdersIsPlacedWithStatusAndCorrectPrice(String arg0) {
        OrdersPage ordersPage = new OrdersPage(driver);

        Assert.assertEquals(arg0, ordersPage.returnLatestOrderStatus().getText());
        Assert.assertEquals(referenceNumber, ordersPage.returnLatestReferenceNumber().getText());
        Assert.assertEquals(totalOrderPrice, ordersPage.returnLatestOrderTotalPrice().getText());
    }
}
