package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.IntStream;

public class AddressesPage extends BasePage {

    private final By successAlertLocator = By.className("alert-success");
    private final By addressesLocator = By.className("address-body");
    private final By newAddressLinkLocator = By.xpath("//a[@data-link-action='add-address']");
    private final By deleteButtonsLocator = By.xpath("//a[@data-link-action='delete-address']");


    public AddressesPage(WebDriver driver) {
        super(driver);
    }

    public void clickNewAddressLink() {
        click(newAddressLinkLocator);
    }

    public String getSuccessAlertText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlertLocator)).getText();
    }

    public WebElement findAddressByAlias(String alias) {
        List<WebElement> addresses = driver.findElements(addressesLocator);
        return addresses.stream()
                .filter(address -> address.getText().startsWith(alias))
                .findFirst()
                .orElse(null);
    }

    public int findAddressIndexByAlias(String alias) {
        List<WebElement> addresses = driver.findElements(addressesLocator);
        return IntStream.range(0, addresses.size())
                .filter(i -> addresses.get(i).getText().startsWith(alias))
                .findFirst()
                .orElse(-1);
    }

    public void deleteAddressByIndex(int index) {
        List<WebElement> deleteButtons = driver.findElements(deleteButtonsLocator);
        deleteButtons.get(index).click();
    }
}