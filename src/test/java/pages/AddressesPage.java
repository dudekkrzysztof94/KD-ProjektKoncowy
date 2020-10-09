package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressesPage extends BasePage{
    public AddressesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".alert.alert-success")
    private WebElement successAlert;

    @FindBy(xpath = "//a[@data-link-action='add-address']")
    private WebElement newAddressLink;

    @FindAll(@FindBy(css = ".address-body"))
    private List<WebElement> addresses;

    @FindAll(@FindBy(xpath = "//a[@data-link-action='delete-address']"))
    private List<WebElement> deleteButtons;

    public String isSuccessful() {
        return successAlert.getText();
    }

    public void createNewAddress() {
        newAddressLink.click();
    }

    public WebElement returnAddressByAlias(String alias) {
        WebElement foundAddress = addresses.get(0);
        for (int i=0; i<addresses.size(); i++) {
            if(addresses.get(i).getText().startsWith(alias)) {
                foundAddress = addresses.get(i);
            }
        }
        return foundAddress;
    }

    public int returnIndexOfAddressByAlias(String alias) {
        int index = -1;
        for (int i=0; i<addresses.size(); i++) {
            if(addresses.get(i).getText().startsWith(alias)) {
                index = i;
            }
        }
        return index;
    }

    public void deleteAddressByIndex(int index) {
        deleteButtons.get(index).click();
    }
}