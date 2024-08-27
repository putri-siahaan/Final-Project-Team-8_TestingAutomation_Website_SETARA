package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.EWallet;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EWallet_Steps extends BaseTest {
    Login login;
    HomePage homePage;
    EWallet eWallet;

    private boolean isErrorDisplayed = false;


    //TC_WA_031 sd TC_WA_038 Top Up To New Account Number

    @Given("user already login and is on the menu Top Up EWallet")
    public void userAlreadyLoginAndIsOnTheMenuTopUpEWallet() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        eWallet = new EWallet(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToOvoMenu();
    }

    @When("user click the TransferKeTujuanBaru button")
    public void userClickTransferKeTujuanButton() {
        eWallet.clickTransferKeTujuanBaru();
    }

    @And("user input nomor ewallet baru {string} in the field")
    public void userInputNomorEWalletInTheField(String nomorEWalletBaru) {
        eWallet.inputNomorEWalletBaru(nomorEWalletBaru);
    }

    @And("user click the CariNomor button")
    public void userClickCariNomorButton() {
        eWallet.clickCariNomor();
        isErrorDisplayed = eWallet.isErrorDestinationDisplayed();
    }

    @And("user choose the SumberRekening")
    public void userChooseSumberRekening() {
        if (!isErrorDisplayed) {
            eWallet.chooseSumberRekening();
        }
    }

    @And("user input nominal top up {string} in the field")
    public void userInputNominalTopUpInTheField(String nominalTopUp) {
        if (!isErrorDisplayed) {
            eWallet.inputNominalTopUp(nominalTopUp);
        }
    }

    @And("user input the catatan {string} in the field")
    public void userInputCatatanInTheField(String Catatan) {
        if (!isErrorDisplayed) {
            eWallet.inputCatatan(Catatan);
        }
    }

    @And("user click the Lanjutkan button after finished input the data top up")
    public void userClickLanjutkanButton() {
        if (!isErrorDisplayed) {
            eWallet.clickLanjutkan();
            isErrorDisplayed = eWallet.isErrorNominalDisplayed();
        }
    }

    @And("user click Top Up button")
    public void userClickTopUpButton() {
        if (!isErrorDisplayed) {
            eWallet.clickTopUp();
        }
    }

    @And("user input the pin {string} in the field")
    public void userInputPINInTheField(String PIN) {
        if (!isErrorDisplayed) {
            eWallet.inputPIN(PIN);
        }
    }

    @And("user click Lanjutkan button")
    public void userClickLanjutkanButtonAfterPIN() {
        if (!isErrorDisplayed) {
            eWallet.clickLanjutkanPIN();
        }
    }

    @Then("validate the top up is successful or failed and display the message {string}")
    public void validateTheTopUpIsSuccessOrFailed(String expectedResult) {
        if (expectedResult.equalsIgnoreCase("Transaksi Berhasil")) {
            eWallet.getExpectedResultMessageSuccessTopUp(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("NOMOR TIDAK TERDAFTAR")) {
            eWallet.getErrorMessageInDestination(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("Nomor Tidak Boleh Kosong")) {
            eWallet.getErrorMessageInDestination(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("Nominal Tidak Boleh Kosong") ||
                expectedResult.equalsIgnoreCase("Minimum transfer adalah 10.000, mohon isikan kembali") ||
                expectedResult.equalsIgnoreCase("Saldo Tidak Cukup, mohon isikan kembali")) {
            eWallet.getErrorMessageInNominal(expectedResult);
        } else if (expectedResult.equalsIgnoreCase("PIN Anda Salah")) {
            eWallet.getErrorMessageInPIN(expectedResult);
        } else {
            throw new AssertionError("Validation failed! Expected result is not recognized: " + expectedResult);
        }
        closeBrowser();
    }






    //TC_WA_039_sd_044 TransferByChoosingFromSavedList
    @When("user chooses from a saved list to top up money")
    public void userChoosesFromASavedListToTopUpMoney() {
        eWallet.chooseAccountEWalletFromSavedList();
    }

    @And("user click Lanjutkan button after finished input the data top up")
    public void userClickLanjutkanButtonAfterInputCatatan() {
        if (!isErrorDisplayed) {
            eWallet.clickLanjutkanButtonAfterInputDataTopUp();
            isErrorDisplayed = eWallet.isErrorNominalDisplayed();
        }
    }






    //TC_WA_045_sd_050 TopUpByChoosingFromSavedList
    @When("user chooses from a favorite list to top up money")
    public void userChoosesFromAFavoriteListToTopUpMoney() {
        eWallet.chooseAccountEWalletFromFavoriteList();
    }



    //TC_WA_051 SavedAccountEWalletAfterSuccessTopUp(OVO)

    @And("user checklist box to add to saved list")
    public void userChecklistBoxToAddToSavedList() {
        eWallet.checklistTheCheckBox();
    }

    @And("user click Button Kembali Ke Homepage")
    public void userClickButtonKembaliKeHomepage() {
        eWallet.clickKembaliKeHomepageButton();
    }

    @And("user click eWallet Menu and select ovo")
    public void userClickEWalletMenuAndSelectOvo() {
        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToOvoMenu();

    }

    @Then("validate success to saved Account EWallet with account {string}")
    public void validateSuccessToSavedAccountEWallet(String nomorEWallet) {
        eWallet.validateSuccessSavedAccountEWallet(nomorEWallet);
        closeBrowser();
    }





    //TC_WA_052 ToFavoriteAccountEWallet

    @When("user click love from Saved List with account 081234567891")
    public void userClickLoveFromSavedListWithAccount081234567891() {
        eWallet.clickLoveToAddToFavoriteList();
    }

    @Then("validate success to favorite EWallet with account {string}")
    public void validateSuccessToFavoriteAccountEWallet(String nomorEWallet) {
        eWallet.validateSuccessFavoriteAccountEWallet(nomorEWallet);
        closeBrowser();
    }






    //TC_WA_053 SavedAccountEWalletAfterSuccessTopUp(ShopeePay)

    @Given("user already login and is on the menu EWallet and choose ShopeePay")
    public void userAlreadyLoginAndIsOnTheMenuTopUpEWalletAndChooseShopeePay() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        eWallet = new EWallet(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToEWalletMenuAndChooseShopeePay();
    }

    @And("user click eWallet Menu and select shopeepay")
    public void userClickEWalletMenuAndSelectShopeePay() {
        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToShopeePayMenu();

    }

    //TC_WA_055 SavedAccountEWalletAfterSuccessTopUp(GoPay)

    @Given("user already login and is on the menu EWallet and choose Gopay")
    public void userAlreadyLoginAndIsOnTheMenuTopUpEWalletAndChooseGoPay() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        eWallet = new EWallet(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToEWalletMenuAndChooseGoPay();
    }

    @And("user click eWallet Menu and select Gopay")
    public void userClickEWalletMenuAndSelectGopay() {
        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToGopayMenu();

    }


    //TC_WA_057 SavedAccountEWalletAfterSuccessTopUp(Dana)

    @Given("user already login and is on the menu Top Up EWalled and choose Dana")
    public void userAlreadyLoginAndIsOnTheMenuTopUpEWalletAndChooseDana() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        eWallet = new EWallet(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToEWalletMenuAndChooseDana();
    }

    @And("user click eWallet Menu and select dana")
    public void userClickEWalletMenuAndSelectDana() {
        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToDanaMenu();

    }



    //TC_WA_059 SavedAccountEWalletAfterSuccessTopUp(LinkAja)

    @Given("user already login and is on the menu EWallet and choose Link Aja")
    public void userAlreadyLoginAndIsOnTheMenuTopUpEWalletAndChooseLinkAja() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        eWallet = new EWallet(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToEWalletMenuAndChooseLinkAja();
    }

    @And("user click eWallet Menu and select linkAja")
    public void userClickEWalletMenuAndSelectLinkAja() {
        driver.navigate().to("https://setara.vercel.app/e-wallet");
        eWallet.goToLinkAjaMenu();

    }




}
