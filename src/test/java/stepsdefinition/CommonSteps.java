package stepsdefinition;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverManager;

public class CommonSteps {

    @Given("User is logged into Presta shop")
    public void userIsLoggedIntoPrestaShop() {
        ChromeDriver driver = WebDriverManager.getDriver();
        driver.get("https://prod-kurs.coderslab.pl/");
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski01@mymail.de", "Pass1231");
    }

    @After
    public void tearDown() {
        ChromeDriver driver = WebDriverManager.getDriver();
        AccountPage accountPage = new AccountPage(driver);
        try {
            accountPage.logout();
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
        } finally {
            WebDriverManager.closeDriver();
        }
    }
}
