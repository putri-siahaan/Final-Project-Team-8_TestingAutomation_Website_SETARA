package SETARA_Website.stepDefinitions;

import SETARA_Website.BaseTest;
import SETARA_Website.pages.HomePage;
import SETARA_Website.pages.Login;
import SETARA_Website.pages.Mutasi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Mutasi_Steps extends BaseTest {

    Login login;
    HomePage homePage;
    Mutasi mutasiPage;


    //TC_WA_061 Success view transaction details history with filter "Today" [Semua Transaksi]

    @Given("user already login and is on the menu Mutasi page")
    public void userIsOnMutasiPage() {
        setUp();
        login = new Login(driver);
        homePage = new HomePage(driver);
        mutasiPage = new Mutasi(driver);

        login.inputUsernameId("ADTP604T");
        login.inputPassword("andika12345");
        login.clickMasukButton();
        //Assertion
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/"));
        Assert.assertEquals(homePage.getCurrentURL(),"https://setara.vercel.app/");

        driver.navigate().to("https://setara.vercel.app/mutasi");
    }

    @When("user selects filter Hari ini and chooses Semua Transaksi")
    public void userSelectsFilterHariIniAndChoosesSemuaTransaksi() {
        mutasiPage.selectFilterHariIni();
        mutasiPage.selectSemuaTransaksi();
    }

    @Then("user should see the transaction history for today")
    public void userShouldSeeTransactionHistoryForToday() {
        boolean isTransactionDisplayed = mutasiPage.verifyTransactionHistoryTodayIsDisplayed();

        if (!isTransactionDisplayed) {
            // Cek apakah pesan "Belum Ada Transaksi :/" yang ditampilkan
            boolean isNoTransactionMessageDisplayed = mutasiPage.isNoTransactionMessageDisplayed();
            Assert.assertTrue("No transactions are available for today, but the 'Belum Ada Transaksi :/' message should be displayed.", isNoTransactionMessageDisplayed);
        } else {
            Assert.assertTrue("Transaction history for today should be displayed", isTransactionDisplayed);
        }
        closeBrowser();
    }





    //TC_WA_062 7 Hari Terakhir

    @When("user selects filter 7 Hari Terakhir and chooses Semua Transaksi")
    public void userSelectsFilter7HariTerakhirAndChoosesSemuaTransaksi() {
        mutasiPage.selectFilter7HariTerakhir();
        mutasiPage.selectSemuaTransaksi();
    }

    @When("user should see the transaction history for the last 7 days")
    public void userSeeTheTransactionHistoryForTheLast7Days() {
        boolean isDisplayed = mutasiPage.verifyTransactionHistory7HariTerakhirIsDisplayed();
        Assert.assertTrue("Transaction history for the last 7 days is not displayed", isDisplayed);
        closeBrowser();
    }






    //TC_WA_063 15 Hari Terakhir

    @When("user selects filter 15 Hari Terakhir and chooses Semua Transaksi")
    public void userSelectsFilter15HariTerakhirAndChoosesSemuaTransaksi() {
        mutasiPage.selectFilter15HariTerakhir();
        mutasiPage.selectSemuaTransaksi();
    }

    @When("user should see the transaction history for the last 15 days")
    public void userSeeTheTransactionHistoryForTheLast15Days() {
        boolean isDisplayed = mutasiPage.verifyTransactionHistory15HariTerakhirIsDisplayed();
        Assert.assertTrue("Transaction history for the last 7 days is not displayed", isDisplayed);
        closeBrowser();
    }





    //TC_WA_064 1 Bulan Terakhir

    @When("user selects filter 1 Bulan Terakhir and chooses Semua Transaksi")
    public void userSelectsFilter1BulanTerakhirAndChoosesSemuaTransaksi() {
        mutasiPage.selectFilter1BulanTerakhir();
        mutasiPage.selectSemuaTransaksi();
    }

    @Then("user should see the transaction history for the last 1 month")
    public void userShouldSeeTransactionHistoryForTheLast1Month() {
        boolean isDisplayed = mutasiPage.verifyTransactionHistory1BulanTerakhirIsDisplayed();
        Assert.assertTrue("Transaction history for the last 1 month is not displayed", isDisplayed);
        closeBrowser();
    }







    //TC_WA_065 Tanggal Lain

    @When("user selects filter Tanggal Lain and chooses Semua Transaksi")
    public void userSelectsFilterTanggalLainAndChoosesSemuaTransaksi() {
        mutasiPage.selectFilterTanggalLainFrom20AgustusTo24Agustus();
        mutasiPage.selectSemuaTransaksi();
    }

    @Then("user should see the transaction history for the selected date")
    public void userShouldSeeTransactionHistoryForSelectedDate() {
        boolean isDisplayed = mutasiPage.verifyTransactionHistoryFrom20To24AugustIsDisplayed();
        Assert.assertTrue("Transaction history for the 20 To 24 August is not displayed", isDisplayed);

        // Assertion: Get Current URL Stay In Data Mutasi 20 Agustus sd 24 Agustus
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://setara.vercel.app/mutasi?startDate=2024-08-20&endDate=2024-08-24&value=5&filter=ALL_TRANSACTIONS"));
        Assert.assertEquals(mutasiPage.getCurrentURL(), "https://setara.vercel.app/mutasi?startDate=2024-08-20&endDate=2024-08-24&value=5&filter=ALL_TRANSACTIONS");

        closeBrowser();
    }








    //TC_WA_066 Detail Transaction

    @When("user clicks on a specific transaction to see the detail")
    public void userClicksOnASpecificTransactionToSeeTheDetail() {
        mutasiPage.clickOnTransaction();
    }

    @Then("user should see the detailed transaction receipt and display {string}")
    public void userShouldSeeTheDetailedTransactionReceipt(String buktiTransferTitle) {
        mutasiPage.verifyTransactionReceiptDisplayed(buktiTransferTitle);
    }

    @And("user click Download Bukti Transfer to store transaction evidence locally")
    public void userClickDownloadBuktiTransferToStoreTransactionEvidenceLocally() {
        mutasiPage.clickDownloadButton();
    }

    @And("verify Bukti Transfer success to save in local")
    public void verifyBuktiTransferSuccessToSaveInLocal() {
        Assert.assertTrue(mutasiPage.verifyDownloadSuccess());
        closeBrowser();
    }






    //TC_WA_067 Melihat Data Pemasukan
    
    @When("user chooses Pemasukan")
    public void userSelectsFilterHariIniAndChoosesPemasukan() {
        mutasiPage.clickPemasukanButton();
    }

    @Then("user should see the income transactions for today")
    public void userShouldSeeTheIncomeTransactionsForToday() {
        boolean isTransactionIncomeDisplayed = mutasiPage.verifySuccessDisplayPemasukkanData();

        if (!isTransactionIncomeDisplayed) {
            boolean isNoTransactionMessageDisplayed = mutasiPage.isNoTransactionMessageDisplayed();
            Assert.assertTrue("No transactions are available, but the 'Belum Ada Transaksi :/' message should be displayed.", isNoTransactionMessageDisplayed);
        } else {
            Assert.assertTrue("Transaction history for today should be displayed", isTransactionIncomeDisplayed);
        }
        closeBrowser();
    }







    //TC_WA_068 Melihat Data Pengeluaran

    @When("user selects filter Hari ini and chooses Pengeluaran")
    public void userSelectsFilterHariIniAndChoosesPengeluaran() {
        mutasiPage.selectFilterHariIni();
        mutasiPage.clickPengeluaranButton();
    }

    @Then("user should see the expense transactions for today")
    public void userShouldSeeTheExpenseTransactionsForToday() {
        boolean isTransactionPengeluaranDisplayed = mutasiPage.verifySuccessDisplayPengeluaranData();

        if (!isTransactionPengeluaranDisplayed) {
            boolean isNoTransactionMessageDisplayed = mutasiPage.isNoTransactionMessageDisplayed();
            Assert.assertTrue("No transactions are available, but the 'Belum Ada Transaksi :/' message should be displayed.", isNoTransactionMessageDisplayed);
        } else {
            Assert.assertTrue("Transaction history for today should be displayed", isTransactionPengeluaranDisplayed);
        }
        closeBrowser();
    }








    // TC_WA_069 MelihatDataPengeluaran7HariTerakhir

    @When("user selects filter 7 hari terakhir and chooses Pengeluaran")
    public void userSelectsFilter7HariTerakhirAndChoosesPengeluaran() {
        mutasiPage.selectFilter7HariTerakhir();
        mutasiPage.clickPengeluaranButton();
    }




    // TC_WA_070 MelihatDataPengeluaran15HariTerakhir

    @When("user selects filter 15 hari terakhir and chooses Pengeluaran")
    public void userSelectsFilter15HariTerakhirAndChoosesPengeluaran() {
        mutasiPage.selectFilter15HariTerakhir();
        mutasiPage.clickPengeluaranButton();
    }




    // TC_WA_071 MelihatDataPengeluaran1BulanTerakhir

    @When("user selects filter 1 bulan terakhir and chooses Pengeluaran")
    public void userSelectsFilter1BulanTerakhirAndChoosesPengeluaran() {
        mutasiPage.selectFilter1BulanTerakhir();
        mutasiPage.clickPengeluaranButton();
    }





    // TC_WA_072 MelihatDataPengeluaranDenganSettingTanggalLain

    @When("user selects filter setting Tanggal Lain and chooses Pengeluaran")
    public void userSelectsFilterSettingTanggalLainAndChoosesPengeluaran() {
        mutasiPage.selectFilterTanggalLainFrom20AgustusTo24Agustus();
        mutasiPage.clickPengeluaranButton();
    }







    // TC_WA_073 Sukses Download Semua Transaksi

    @When("user clicks on Download button")
    public void userClicksOnDownloadButton() {
        mutasiPage.clickDownloadButtonInMutasiPage();
    }

    @Then("the transaction history should be downloaded")
    public void theTransactionHistoryShouldBeDownloaded() {
        Assert.assertTrue(mutasiPage.verifyDownloadAllTransactionSuccess());
        closeBrowser();
    }
}
