package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Login_Steps extends BaseTest{


    Login login;
    HomePage homePage;


    //TC_WA_001 [Positive] - Verify success login using valid username id and valid password

    @Given("user already on the login page of SETARA website")
    public void userAlreadyOnTheLoginPageOfSETARAWebsite() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
    }

    @When("user input valid username id")
    public void userInputValidUsernameId() {
        login.inputUsernameId("ADTP604T");
    }

    @And("user input valid password")
    public void userInputValidPassword() {
        login.inputPassword("andika12345");
    }

    @And("user click icon show password to see password is correct or not")
    public void userClickIconShowPasswordToSeePasswordIsCorrectOrNot() {
        login.clickEyesIcon();
    }

    @And("user click Masuk button")
    public void userClickMasukButton() {
        login.clickMasukButton();
    }

    @Then("validate success login and i am on the dashboard Destimate and display message {string}")
    public void validateSuccessLoginAndIAmOnTheDashboardDestimateAndDisplayMessage(String successLogin) {
        //Validation Success get Allert "Authentication successful"
        login.validateSuccessLogin(successLogin);

        //Assertion Get Current URL HomePage
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        //Assertion Get Menu in HomePage
        //Assert.assertEquals(homePage.getMenuTransactionInHomepage(),"Menu");

        closeBrowser();
    }







    //TC_WA_002 [Negative 1 s/d 3] - Verify failed to login because input invalid username id

    @When("user input username {string}")
    public void userInputUsername(String usernameCaseNegative) {
        login.inputUsernameId(usernameCaseNegative);
    }

    @When("user input password {string}")
    public void userInputPassword(String passwordCaseNegative) {
        login.inputPassword(passwordCaseNegative);
    }

    @Then("validate failed login and display result with message {string}")
    public void validateFailedLoginBecauseCaseNegative(String expectedMessage) {
        // Split the expected message in case there are multiple error messages
        String[] expectedMessages = expectedMessage.split("\n");

        for (String message : expectedMessages) {
            if (message.equals("Email atau Password Salah")) {
                // Validation for incorrect username or password
                login.getErrorMessageInvalidUsernameOrPassword(message);
            } else if (message.equals("Mohon Masukan Username!")) {
                // Validation for empty username
                login.validateFailedLoginBecauseEmptyUsername(message);
            } else if (message.equals("Mohon Masukan Password!")) {
                // Validation for empty password
                login.validateFailedLoginBecauseEmptyKataSandi(message);
            }
        }

        // Assertion: Get Current URL Stay In Login Page
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/login"));
        Assert.assertEquals(homePage.getCurrentURL(), "https://setara.vercel.app/login");

        // Assertion: Ensure the login button is still present
        Assert.assertEquals(login.getTextMasukInLoginPage(), "Masuk");

        closeBrowser();
    }

}
