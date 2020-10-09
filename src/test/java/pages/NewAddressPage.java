package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewAddressPage extends BasePage{

    public NewAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "alias")
    private WebElement aliasInput;

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "company")
    private WebElement companyInput;

    @FindBy(name = "vat_number")
    private WebElement vatNumberInput;

    @FindBy(name = "address1")
    private WebElement address1Input;

    @FindBy(name = "address2")
    private WebElement address2Input;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "postcode")
    private WebElement postcodeInput;

    @FindBy(name = "id_country")
    private WebElement countrySelect;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(css = ".btn.btn-primary.float-xs-right")
    private WebElement saveButton;

    private void fillInput(WebElement element, String input) {
        element.click();
        element.clear();
        element.sendKeys(input);
    }

    public void setAlias(String alias) {
        fillInput(aliasInput,  alias);
    }

    public void setFirstName(String firstName) {
        fillInput(firstNameInput,  firstName);
    }

    public void setLastName(String lastName) {
        fillInput(lastNameInput,  lastName);
    }

    public void setVatNumber(String vatNumber) {
        fillInput(vatNumberInput,  vatNumber);
    }

    public void setAddress1(String address1) {
        fillInput(address1Input,  address1);
    }

    public void setAddress2(String address2) {
        fillInput(address2Input,  address2);
    }

    public void setCity(String city) {
        fillInput(cityInput,  city);
    }

    public void setPostcode(String postcode) {
        fillInput(postcodeInput,  postcode);
    }

    public void setPhone(String phone) {
        fillInput(phoneInput,  phone);
    }

    public void setCountry(String country) {
        Select s = new Select(countrySelect);
        s.selectByVisibleText(country);
    }

    public void submitAddress() {
        saveButton.click();
    }
}
