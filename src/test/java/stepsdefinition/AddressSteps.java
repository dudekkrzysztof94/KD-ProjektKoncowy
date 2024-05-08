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
    private final AccountPage accountPage = new AccountPage(driver);
    private final NewAddressPage newAddressPage = new NewAddressPage(driver);
    private final AddressesPage addressesPage = new AddressesPage(driver);
    private String storedAddress;

    @When("User navigates to the Addresses Page")
    public void userNavigatesToTheAddressesPage() {
        accountPage.navigateToAddresses();
    }

    @And("User clicks on the new address link")
    public void userClicksOnTheNewAddressLink() {
        addressesPage.clickNewAddressLink();
    }

    @And("User fills in the address form with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userFillsInTheAddressFormWith(String name, String surname, String alias, String address, String city, String code, String country, String phone) {
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
    public void userSubmitsTheNewAddress() {
        newAddressPage.clickSaveButton();
    }

    @Then("Message {string} should be displayed")
    public void messageShouldBeDisplayed(String message) {
        Screenshooter.takeScreenshot(driver);
        assertEquals(message, addressesPage.getSuccessAlertText());
    }

    @And("Address with {string} should be visible in the address list")
    public void addressWithShouldBeVisibleInTheAddressList(String alias) {
        assertEquals(storedAddress, addressesPage.findAddressByAlias(alias).getText());
    }

    @And("User deletes the address with {string}")
    public void userDeletesTheAddressWith(String alias) {
        int indexOfAddressToDelete = addressesPage.findAddressIndexByAlias(alias);
        addressesPage.deleteAddressByIndex(indexOfAddressToDelete);
    }
}