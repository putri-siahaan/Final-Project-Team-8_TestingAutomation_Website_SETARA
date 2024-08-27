package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.Login;
import SETARA_Website.pages.TransferAntarBCA;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TransferAntarBCA_Steps extends BaseTest {
    Login login;
    HomePage homePage;
    TransferAntarBCA transferAntarBCA;

    private boolean isErrorDisplayed = false;



    //TC_WA_010 SavedAccountRekeningAfterSuccessTransfer

    @And("user checklist the box to add to saved list")
    public void userChecklistTheBoxToAddToSavedList() {
        transferAntarBCA.checklistTheCheckBox();
    }

    @And("user click Kembali Ke Homepage Button")
    public void userClickKembaliKeHomepageButton() {
        transferAntarBCA.clickKembaliKeHomepageButton();
    }

    @And("user click transfer Menu and select menu Transfer Antar BCA")
    public void userClickTransferMenuAndSelectMenuTransferAntarBCA() {
        driver.navigate().to("https://setara.vercel.app/bca");
        transferAntarBCA.goToTransferAntarBCAMenu();

    }

    @Then("validate success to saved Account Rekening with account {string}")
    public void validateSuccessToSavedAccountRekening(String nomorRekening) {
        transferAntarBCA.validateSuccessSavedAccountRekening(nomorRekening);
        closeBrowser();
    }



    //TC_WA_011 ToFavoriteAccountRekening

    @When("user click love from Saved List with account 1122334455")
    public void userClickLoveFromSavedListWithAccount1122334455() {
        transferAntarBCA.clickLoveToAddToFavoriteList();
    }

    @Then("validate success to favorite Account Rekening with account {string}")
    public void validateSuccessToFavoriteAccountRekening(String nomorRekening) {
        transferAntarBCA.validateSuccessFavoriteAccountRekening(nomorRekening);
        closeBrowser();
    }




    //TC_WA_012 sd TC_WA_018 TF To New Account Rekening

    @Given("user already login and is on the menu Transfer Antar BCA")
    public void userAlreadyLoginAndIsOnTheTransferMenu() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        transferAntarBCA = new TransferAntarBCA(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/bca");
        transferAntarBCA.goToTransferAntarBCAMenu();
    }

    @When("user click TransferKeTujuanBaru button")
    public void userClickButton() {
        transferAntarBCA.clickTransferKeTujuanBaru();
    }

    @And("user input nomor transfer baru {string} in the field")
    public void userInputNomorTransferInTheField(String NomorTransferBaru) {
        transferAntarBCA.inputNomorTransferBaru(NomorTransferBaru);
    }

    @And("user click CariNomor button")
    public void userClickCariNomorButton() {
        transferAntarBCA.clickCariNomor();
        isErrorDisplayed = transferAntarBCA.isErrorDestinationDisplayed(); // Update isErrorDisplayed here
    }

    @And("user choose SumberRekening")
    public void userChooseSumberRekening() {
        if (!isErrorDisplayed) {
            transferAntarBCA.chooseSumberRekening();
        }
    }

    @And("user input nominal transfer {string} in the field")
    public void userInputNominalInTheField(String NominalTransfer) {
        if (!isErrorDisplayed) {
            transferAntarBCA.inputNominalTransfer(NominalTransfer);
        }
    }

    @And("user input catatan {string} in the field")
    public void userInputCatatanInTheField(String Catatan) {
        if (!isErrorDisplayed) {
            transferAntarBCA.inputCatatan(Catatan);
        }
    }

    @And("user click the Lanjutkan button after finished input the data")
    public void userClickLanjutkanButton() {
        if (!isErrorDisplayed) {
            transferAntarBCA.clickLanjutkan();
            isErrorDisplayed = transferAntarBCA.isErrorNominalDisplayed(); // Update isErrorDisplayed after Lanjutkan
        }
    }

    @And("user click the Transfer button")
    public void userClickTransferButton() {
        if (!isErrorDisplayed) {
            transferAntarBCA.clickTransfer();
        }
    }

    @And("user input pin {string} in the field")
    public void userInputPINInTheField(String PIN) {
        if (!isErrorDisplayed) {
            transferAntarBCA.inputPIN(PIN);
        }
    }

    @And("user click the Lanjutkan button")
    public void userClickLanjutkanButtonAfterPIN() {
        if (!isErrorDisplayed) {
            transferAntarBCA.clickLanjutkanPIN();
        }
    }

    @Then("validate the transfer is successful or failed and display the message {string}")
    public void validateTheTransferIs(String expectedResult) {
        if (expectedResult.equalsIgnoreCase("Transaksi Berhasil")) {
            // Panggil validasi untuk transfer sukses
            transferAntarBCA.getExpectedResultMessageSuccessTransfer(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("NOMOR TIDAK TERDAFTAR")) {
            // Panggil validasi untuk error pada nomor tujuan
            transferAntarBCA.getErrorMessageInDestination(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("Nominal Tidak Boleh Kosong") ||
                expectedResult.equalsIgnoreCase("Minimum transfer adalah 1, mohon isikan kembali") ||
                expectedResult.equalsIgnoreCase("Saldo Tidak Cukup, mohon isikan kembali")) {
            // Panggil validasi untuk error pada nominal
            transferAntarBCA.getErrorMessageInNominal(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("PIN Anda Salah")) {
            // Panggil validasi untuk error pada PIN
            transferAntarBCA.getErrorMessageInPIN(expectedResult);
        } else {
            // Jika tidak ada kondisi yang cocok, lempar error
            throw new AssertionError("Validation failed! Expected result is not recognized: " + expectedResult);
        }
        closeBrowser();
    }






    //TC_WA_019_sd_024 TransferByChoosingFromSavedList
    @When("user chooses from a saved list to transfer money")
    public void userChoosesFromASavedListToTransferMoney() {
        transferAntarBCA.chooseAccountRekeningFromSavedList();
    }

    @And("user click Lanjutkan button after finished input the data")
    public void userClickLanjutkanButtonAfterInputData() {
        if (!isErrorDisplayed) {
            transferAntarBCA.clickLanjutkanAfterInputData();
            isErrorDisplayed = transferAntarBCA.isErrorNominalDisplayed();
        }
    }






    //TC_WA_025_sd_030 TransferByChoosingFromSavedList
    @When("user chooses from a favorite list to transfer money")
    public void userChoosesFromAFavoriteListToTransferMoney() {
        transferAntarBCA.chooseAccountRekeningFromFavoriteList();
    }

}
