package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.Login;
import SETARA_Website.pages.Logout;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Logout_Steps extends BaseTest {
    Login login;
    HomePage homePage;

    Logout logout;

    //TC_WA_081 [Positive] - Verify success logout

    @Given("user is logged in and on the SETARA dashboard")
    public void userIsLoggedInAndOnTheSETARADashboard() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
    }

    @When("user clicks the Logout menu")
    public void userClicksTheLogoutMenu() {
        logout.clickLogoutMenu();
    }

    @When("user selects {string} on the Logout Confirmation")
    public void userSelectsOnTheLogoutConfirmation(String option) {
        if (option.equals("Iya")) {
            logout.confirmLogout();
        } else if (option.equals("Tidak")) {
            logout.cancelLogout();
        }
    }

    @Then("user should be successfully logged out of the website")
    public void userShouldBeSuccessfullyLoggedOutOfTheWebsite() {
        logout.successLogout();
        closeBrowser();
    }

    @Then("user should remain on the SETARA dashboard")
    public void userShouldRemainOnTheSETARADashboard() {
        // Assertion: Get Current URL Stay In Dashboard
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(), "https://setara.vercel.app/");
        closeBrowser();
    }


}
