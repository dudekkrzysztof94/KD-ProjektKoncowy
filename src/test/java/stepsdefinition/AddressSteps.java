package stepsdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.AddressesPage;
import pages.NewAddressPage;
import utils.Screenshooter;
import utils.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressSteps {

    private final ChromeDriver driver = WebDriverManager.getDriver();
    private NewAddressPage newAddressPage;
    private AddressesPage addressesPage;
    private String storedAddress;

    @When("User navigates to the Addresses Page")
    public void userNavigatesToAddressPage() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.navigateToAddresses();
    }

    @And("User clicks on the new address link")
    public void userAddsANewAddress() {
        addressesPage = new AddressesPage(driver);
        addressesPage.clickNewAddressLink();
    }

    @And("User fills in the address form with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userFillsInAddressFormWith(String name, String surname, String alias, String address, String city, String code, String country, String phone) {
        newAddressPage = new NewAddressPage(driver);

        newAddressPage.setFirstName(name);
        newAddressPage.setLastName(surname);
        newAddressPage.setAlias(alias);
        newAddressPage.setAddress(address);
        newAddressPage.setCity(city);
        newAddressPage.setPostcode(code);
        newAddressPage.setCountry(country);
        newAddressPage.setPhone(phone);

        storedAddress = String.format("%s\n%s %s\n%s\n%s\n%s\n%s\n%s", alias, name, surname, address, city, code, country, phone);
    }

    @And("User submits the new address")
    public void userSubmitsNewAddress() {
        newAddressPage.clickSaveButton();
    }

    @Then("Message {string} should be displayed")
    public void messageIsDisplayed(String message) {
        addressesPage = new AddressesPage(driver);
        Screenshooter.takeScreenshot(driver);
        assertEquals(message, addressesPage.getSuccessAlertText());
    }

    @And("Address with {string} should be visible in the address list")
    public void addressIsVerified(String alias) {
        assertEquals(storedAddress, addressesPage.findAddressByAlias(alias).getText());
    }

    @And("User deletes the address with {string}")
    public void userDeletesLastAddedAddress(String alias) {
        int indexOfAddressToDelete = addressesPage.findAddressIndexByAlias(alias);
        addressesPage.deleteAddressByIndex(indexOfAddressToDelete);
    }
}