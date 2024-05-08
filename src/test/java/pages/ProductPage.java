package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    private final By regularPriceLocator = By.cssSelector(".regular-price");
    private final By discountPriceLocator = By.cssSelector(".current-price-value");
    private final By sizeSelectLocator = By.id("group_1");
    private final By quantityInputLocator = By.id("quantity_wanted");
    private final By addToCartButtonLocator = By.cssSelector(".add-to-cart");
    private final By proceedToCheckoutButtonLocator = By.xpath("//div[@class='cart-content-btn']/a");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getRegularPrice() {
        WebElement regularPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(regularPriceLocator));
        return regularPrice.getText();
    }

    public String getDiscountedPrice() {
        WebElement discountedPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(discountPriceLocator));
        return discountedPrice.getText();
    }

    public void selectSize(String size) {
        WebElement sizeSelect = wait.until(ExpectedConditions.elementToBeClickable(sizeSelectLocator));
        new Select(sizeSelect).selectByVisibleText(size);
    }

    public void setQuantity(String quantity) {
        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInputLocator));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public void clickAddToCartButton() {
        click(addToCartButtonLocator);
    }

    public void clickProceedToCheckoutButton() {
        click(proceedToCheckoutButtonLocator);
    }
}
