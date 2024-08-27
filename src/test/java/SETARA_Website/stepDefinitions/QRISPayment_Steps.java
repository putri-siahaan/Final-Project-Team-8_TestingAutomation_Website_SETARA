package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.Login;
import SETARA_Website.pages.QRISPayement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QRISPayment_Steps extends BaseTest {

    Login login;
    HomePage homePage;

    QRISPayement qrisPayement;



    // @TC_WA_075 SuccessToTransferWithValidImageQRISFromLocal

    @Given("user already login and open the QRIS payment page")
    public void userOpensTheQRISPaymentPage() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        qrisPayement = new QRISPayement(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/qr");
        qrisPayement.openQRISPaymentPage();
    }



    @When("user uploads a valid jpeg")
    public void userUploadsAValidJpeg() {
        qrisPayement.uploadJpeg("C:/Users/user/Documents/QR_InputNominal.jpg");
    }

    @And("user input nominal {string}")
    public void userInputNominal(String nominalTF) {
        qrisPayement.inputNominalTransfer(nominalTF);

    }

    @And("user input catatan {string}")
    public void userInputCatatan(String catatan) {
        qrisPayement.inputCatatan(catatan);
    }

    @And("user click Lanjutkan and Konfirmasi Button")
    public void userClickLanjutkanAndKonfirmasiButton() {
        qrisPayement.clickLanjutkan();
        qrisPayement.clickKonfirmasiButton();
    }

    @And("user input valid PIN {string} and click Lanjutkan Button")
    public void userInputValidPINAndClickLanjutkanButton(String pin) {
        qrisPayement.inputPIN(pin);
        qrisPayement.clickLanjutkanPIN();
    }

    @Then("verify payment should be successful and display Message {string}")
    public void verifyPaymentShouldBeSuccessfulAndDisplayMessage(String successTransfer) {
        qrisPayement.getExpectedResultMessageSuccessTransfer(successTransfer);
        closeBrowser();
    }





    // @TC_WA_076 FailedToTransferBecauseInvalidImage

    @When("user uploads an invalid jpeg")
    public void userUploadsAnInvalidJpeg() {
        qrisPayement.uploadJpeg("C:/Users/user/Documents/kucing.jpg");
    }

    @Then("verify failed transfer and display message {string}")
    public void verifyFailedTransferAndDisplayMessage(String failedInvalidImage) {
        qrisPayement.getErrorMessageInvalidImage(failedInvalidImage);
        closeBrowser();
    }








    // TC_WA_077 sd TC_WA_078 FailedToTransferBecauseNominal0

    @Then("verify failed transfer with qris and display Message {string}")
    public void verifyFailedTransferWithQrisAndDisplayMessage(String expectedMessages) {
        qrisPayement.getErrorMessageInNominal(expectedMessages);
        closeBrowser();
    }




    // @TC_WA_079 FailedToTransferBecauseNominalEmpty

    @And("user click Lanjutkan")
    public void userClickLanjutkan() {
        qrisPayement.clickLanjutkan();
    }




    // @TC_WA_080 Success Transfer With Code QR Disable Nominal

    @When("user upload jpg code qr disable nomnial")
    public void userUploadJpgCodeQrDisableNomnial() {
        qrisPayement.uploadJpeg("C:/Users/user/Documents/QR_DisableNominal.jpg");
    }

}
