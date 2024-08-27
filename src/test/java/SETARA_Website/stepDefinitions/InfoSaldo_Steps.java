package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.InfoSaldo;
import SETARA_Website.pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InfoSaldo_Steps extends BaseTest {

    InfoSaldo infoSaldoPage;
    Login login;
    HomePage homePage;

    //TC_WA_008 [Positive] - Verify success to See Balance In Homepage

    @Given("user has successfully logged in and is on the homepage of SETARA website")
    public void userOnHomepage() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        infoSaldoPage = new InfoSaldo(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");
    }

    @When("user click icon show balance")
    public void userClickShowBalance() {
        infoSaldoPage.clickIconUntukMelihatSaldo();
    }

    @Then("the user should see their account balance displayed on the homepage")
    public void userSeesBalanceOnHomepage() {
        String balance = infoSaldoPage.getInfoSaldoDiHomepage();
        //Assertion Success Get the Balance in the Homepage
        Assert.assertNotNull("Balance should be displayed on the homepage", balance);

        closeBrowser();
    }




    //TC_WA_009 [Positive] - Verify success to See Balance In The Menu Info Saldo

    @When("user click Info Saldo menu")
    public void userNavigatesToMenu() {
        infoSaldoPage.clickInfoSaldoMenu();
    }

    @And("user get text {string}")
    public String usergetText(String infoSaldoText) {

        String actualText = infoSaldoPage.getTitleInfoSaldo();

        // Assertion that the actual text matches the expected text
        Assert.assertEquals(infoSaldoText, actualText);

        return actualText;

    }

    @Then("user should see a detailed balance")
    public void userSeesDetailedBreakdown() {
        String detailedBalance = infoSaldoPage.getDetailedBalance();

        // Assertion Success To See Balance From Menu Info Saldo
        Assert.assertNotNull("Balance should be displayed on the homepage", detailedBalance);

        closeBrowser();
    }

}
