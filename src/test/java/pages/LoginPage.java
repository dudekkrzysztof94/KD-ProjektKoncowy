package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="email")
    private WebElement loginInput;

    @FindBy(name="password")
    private WebElement passwordInput;

    @FindBy(id="submit-login")
    private WebElement signInButton;

    @FindBy(xpath="//a[@class='account']")
    private WebElement userName;

    public void loginAs(String email, String password) {
        loginInput.click();
        loginInput.clear();
        loginInput.sendKeys(email);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        signInButton.click();
    }

    public String getLoggedUsername() {
        return userName.getText();
    }
}