package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By loginInputLocator = By.name("email");
    private final By passwordInputLocator = By.name("password");
    private final By signInButtonLocator = By.id("submit-login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginAs(String email, String password) {
        WebElement loginInput = wait.until(ExpectedConditions.visibilityOfElementLocated(loginInputLocator));
        loginInput.click();
        loginInput.clear();
        loginInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        signInButton.click();
    }
}