package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement discountPrice;

    @FindBy(id = "group_1")
    private WebElement sizeSelect;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='cart-content-btn']/a")
    private WebElement proceedToCheckoutButton;

    public double calculateDiscount() {
        double newPrice, oldPrice;
        newPrice = Double.parseDouble(discountPrice.getText().substring(1));
        oldPrice = Double.parseDouble(regularPrice.getText().substring(1));
        return (oldPrice-newPrice)/oldPrice*100;
    }

    public void selectSize(String size) {
        Select s = new Select(sizeSelect);
        s.selectByVisibleText(size);
    }

    public void setQuantity(String quantity) throws InterruptedException {
        Thread.sleep(500);
        /*
        without driver sleeping here browser engine while reconstructing this input
        overwrites 5 with 1 at around 300 millisecond of elapsed time; hence Thread.sleep
         */
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void placeOrderAndCheckout() {
//        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }
}
