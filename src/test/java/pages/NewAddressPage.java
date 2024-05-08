package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewAddressPage extends BasePage {

    private final By aliasLocator = By.name("alias");
    private final By firstNameLocator = By.name("firstname");
    private final By lastNameLocator = By.name("lastname");
    private final By addressLocator = By.name("address1");
    private final By cityLocator = By.name("city");
    private final By postcodeLocator = By.name("postcode");
    private final By phoneLocator = By.name("phone");
    private final By countryLocator = By.name("id_country");
    private final By saveButtonLocator = By.className("form-control-submit");


    public NewAddressPage(WebDriver driver) {
        super(driver);
    }

    private void fillInput(By locator, String input) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
        element.clear();
        element.sendKeys(input);
    }

    public void setAlias(String alias) {
        fillInput(aliasLocator, alias);
    }

    public void setFirstName(String firstName) {
        fillInput(firstNameLocator, firstName);
    }

    public void setLastName(String lastName) {
        fillInput(lastNameLocator, lastName);
    }

    public void setAddress(String address1) {
        fillInput(addressLocator, address1);
    }

    public void setCity(String city) {
        fillInput(cityLocator, city);
    }

    public void setPostcode(String postcode) {
        fillInput(postcodeLocator, postcode);
    }

    public void setPhone(String phone) {
        fillInput(phoneLocator, phone);
    }

    public void setCountry(String country) {
        WebElement countryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(countryLocator));
        new Select(countryElement).selectByVisibleText(country);
    }

    public void clickSaveButton() {
        click(saveButtonLocator);
    }
}
