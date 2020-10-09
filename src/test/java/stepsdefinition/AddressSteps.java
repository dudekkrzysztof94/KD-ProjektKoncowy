package stepsdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.NewAddressPage;
import pages.AddressesPage;
import pages.LoginPage;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class AddressSteps {
    private static WebDriver driver;
    private static LocalDateTime now;
    private static String signedAlias;
    private static String storedAddress;

    @Given("User logs into CodersLab shop")
    public void userLogsIntoCodersLabShop() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski01@mymail.de", "Pass1231");
    }

    @When("User navigates to AddressesPage")
    public void userNavigatesToAddressPage() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.navigateToAddresses();
    }

    @And("User creates new address")
    public void userAddsANewAddress() {
        AddressesPage addressesPage = new AddressesPage(driver);
        addressesPage.createNewAddress();
    }

    @And("User fills in address form with {string}, {string}, {string}, {string}, {string}, {string}")
    public void userFillsInAddressFormWith(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        NewAddressPage newAddressPage = new NewAddressPage(driver);

        newAddressPage.setFirstName("Jazimierz");
        newAddressPage.setLastName("Kowalski");

        signAliasWithTime(arg0);

        newAddressPage.setAlias(signedAlias);
        newAddressPage.setAddress1(arg1);
        newAddressPage.setCity(arg2);
        newAddressPage.setPostcode(arg3);
        newAddressPage.setCountry(arg4);
        newAddressPage.setPhone(arg5);

        storedAddress = signedAlias+"\nJazimierz Kowalski\n"+arg1+"\n"+arg2+"\n"+arg3+"\n"+arg4+"\n"+arg5;
    }

    @And("User submits new address")
    public void userSubmitsNewAddress() {
        NewAddressPage newAddressPage = new NewAddressPage(driver);
        newAddressPage.submitAddress();
    }

    @Then("Message {string} is displayed")
    public void messageIsDisplayed(String arg0) {
        AddressesPage addressesPage = new AddressesPage(driver);
        Assert.assertEquals(arg0, addressesPage.isSuccessful());
    }

    @And("Address is verified")
    public void addressIsVerified() {
        AddressesPage addressesPage = new AddressesPage(driver);

        Assert.assertEquals(storedAddress, addressesPage.returnAddressByAlias(signedAlias).getText());
    }

    @And("User deletes that address")
    public void userDeletesThatAddress() {
        AddressesPage addressesPage = new AddressesPage(driver);

        int indexOfAddressToDelete = addressesPage.returnIndexOfAddressByAlias(signedAlias);
        addressesPage.deleteAddressByIndex(indexOfAddressToDelete);
    }

    @And("Address deletion is verified")
    public void addressDeletionIsVerified() {
    }

    private void signAliasWithTime(String baseAddress) {
        now = LocalDateTime.now();
        signedAlias = (now + baseAddress).substring(11, 43);
    }
}