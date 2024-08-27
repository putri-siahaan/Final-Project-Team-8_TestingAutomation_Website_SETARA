package SETARA_Website.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class EWallet extends PageObject {

    private By ovoMenu(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/a[1]");
    }

    private By topUpKeTujuanBaruButton(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div[1]/div/button");
    }

    private By nomorEWalletBaruField(){
        return By.xpath("//*[@id='destinationNumber']/div/input");
    }

    private By cariNomorButton(){
        return By.xpath("//*[@id='destinationNumber']/div/button");
    }

    private By sumberRekeningDropdown(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/form/div[4]/div/div[2]/div/div/div");
    }

    private By chooseSumber(){
        return By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div");
    }

    private By nominalTopUpField(){
        return By.xpath("//div[@class='ant-input-number-input-wrap']/input[@id='amount']");
    }

    private By catatanField(){
        return By.id("notes");
    }

    private By lanjutkanButton(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/form/button");
    }

    private By topUpButton(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div[2]/button/span");
    }

    private By pinField(){
        return By.xpath("//span[contains(@class, 'ant-input-affix-wrapper') and contains(@class, 'input-label')]//input[@type='password' and @placeholder='Masukkan PIN Anda']");
    }

    private By lanjutkanPINButton(){
        return By.xpath("//*[@id='basic']/div[2]/div/div/div/div/button/span");
    }

    private By successTopUpMoney(){
        return By.xpath("//p[text()='Transaksi Berhasil']");
    }

    private By errorMessageDestinationNumber(){
        return By.xpath("//*[@id='destinationNumber_help']/div");
    }

    private By errorMessageNominalTopUp(){
        return By.xpath("//*[@id='amount_help']/div");
    }

    private By errorMessagePIN(){
        return By.xpath("//*[@id='basic_pin_help']/div");
    }


    // Transfer By Saved List
    private By AccountEwalletFromSavedList(){
        return By.xpath("//*[@id='contact-list']/div");
    }

    private By lanjutkanButtonByFavoriteOrSavedList(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/div/div/form/button");
    }

    // Transfer By Favorite List
    private By AccountEWalletFromFavoriteList(){
        return By.xpath("//*[@id='contact-list']/div/div");
    }


    // Case Saved Account Rekening
    private By cheklistBox(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/form/div[2]/div/div/div/div/label");
    }

    private By kembaliKeHomepageButton(){
        return By.xpath("//*[@id='root']/main/div/div[2]/button[1]");
    }

    private By contactList(){return By.id("contact-list");}

    //TC_WA_053 SavedAccountEWalletAfterSuccessTopUp(ShopeePay)

    private By shopeePayMenu(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/a[2]/div");
    }
    private By goPayMenu(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/a[3]/div");
    }
    private By danaMenu(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/a[4]/div");
    }
    private By linkAjaMenu(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/a[5]/div");
    }









    WebDriver driver;
    WebDriverWait wait;

    public EWallet(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }




    //TC_WA_031 sd TC_WA_038 Top Up To New Account Number

    public void goToOvoMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ovoMenu()));
        driver.findElement(ovoMenu()).click();
    }

    public void clickTransferKeTujuanBaru() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(topUpKeTujuanBaruButton()));
        driver.findElement(topUpKeTujuanBaruButton()).click();
    }

    public void inputNomorEWalletBaru(String nomorEWalletBaru) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nomorEWalletBaruField()));
        driver.findElement(nomorEWalletBaruField()).sendKeys(nomorEWalletBaru);
    }

    public void clickCariNomor() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cariNomorButton()));
        driver.findElement(cariNomorButton()).click();
        driver.findElement(cariNomorButton()).click();
    }

    public boolean isErrorDestinationDisplayed() {
        try {
            WebElement errorElementDestination = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageDestinationNumber()));
            return errorElementDestination.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void chooseSumberRekening() {
        try {
            // Wait for the sumberRekeningDropdown element to be clickable
            WebElement sumberRekeningElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sumberRekeningDropdown()));

            // Scroll to the sumberRekeningDropdown element
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sumberRekeningElement);

            Thread.sleep(500); // Small delay to ensure the element is ready

            // Try clicking the sumberRekeningDropdown element
            try {
                sumberRekeningElement.click();
            } catch (ElementNotInteractableException e) {
                // Fallback to using JavaScript if normal click doesn't work
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sumberRekeningElement);
            }

            // Wait for the chooseSumber element to be visible and clickable
            WebElement chooseSumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(chooseSumber()));

            // Click the chooseSumber element to select the dropdown option
            chooseSumberElement.click();

        } catch (InterruptedException e) {
            // Handle the InterruptedException here
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println("Thread was interrupted, Failed to complete operation");
        }
    }

    public void inputNominalTopUp(String nominalTopUp) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nominalTopUpField()));
        driver.findElement(nominalTopUpField()).sendKeys(nominalTopUp);
    }

    public void inputCatatan(String catatan) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catatanField()));
        driver.findElement(catatanField()).sendKeys(catatan);
    }

    public void clickLanjutkan() {
        driver.findElement(lanjutkanButton()).click();
    }

    public boolean isErrorNominalDisplayed() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageNominalTopUp()));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickTopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(topUpButton()));
        driver.findElement(topUpButton()).click();
    }

    public void inputPIN(String pin) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pinField()));
        driver.findElement(pinField()).sendKeys(pin);
    }

    public void clickLanjutkanPIN() {
        driver.findElement(lanjutkanPINButton()).click();
    }

    public void getExpectedResultMessageSuccessTopUp(String successTopUp) {
        try {
            // Tunggu hingga elemen alert muncul
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(successTopUpMoney()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(successTopUp)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + successTopUp + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }

    // Error In Destination Number
    @Step
    public void getErrorMessageInDestination(String expectedMessages) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(errorMessageDestinationNumber()));

            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(expectedMessages)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + expectedMessages + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }




    // Error In Nominal
    @Step
    public void getErrorMessageInNominal(String expectedMessages) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(errorMessageNominalTopUp()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(expectedMessages)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + expectedMessages + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }



    // Error In PIN
    @Step
    public void getErrorMessageInPIN(String expectedMessages) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(errorMessagePIN()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(expectedMessages)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + expectedMessages + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }








    //TC_WA_039_sd_044 TopUpByChoosingFromSavedList
    public void chooseAccountEWalletFromSavedList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountEwalletFromSavedList()));
        driver.findElement(AccountEwalletFromSavedList()).click();
    }

    public void clickLanjutkanButtonAfterInputDataTopUp() {
        WebElement lanjutkanElement = wait.until(ExpectedConditions.elementToBeClickable(lanjutkanButtonByFavoriteOrSavedList()));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lanjutkanElement);
        driver.findElement(lanjutkanButtonByFavoriteOrSavedList()).click();
    }







    //TC_WA_045_sd_050 TopUpByChoosingFromFavoriteList
    public void chooseAccountEWalletFromFavoriteList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountEWalletFromFavoriteList()));
        driver.findElement(AccountEWalletFromFavoriteList()).click();
    }







    //TC_WA_051 SavedAccountRekeningAfterSuccessTransfer
    public void checklistTheCheckBox() {
        driver.findElement(cheklistBox()).click();
    }

    public void clickKembaliKeHomepageButton() {
        // Wait for the sumberRekeningDropdown element to be clickable
        WebElement kembaliKeHomepageElement = wait.until(ExpectedConditions.elementToBeClickable(kembaliKeHomepageButton()));

        // Scroll to the sumberRekeningDropdown element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", kembaliKeHomepageElement);

        driver.findElement(kembaliKeHomepageButton()).click();
    }


    public void validateSuccessSavedAccountEWallet(String nomorEWallet) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactList = wait.until(ExpectedConditions.visibilityOfElementLocated(contactList()));

        // Scroll ke daftar tersimpan
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", contactList);

        // Ambil semua nomor rekening dalam daftar tersimpan
        List<WebElement> savedAccounts = driver.findElements(By.xpath("//div[@id='contact-list']//span[contains(@class, 'md:text-caption-large')]"));

        // Cek apakah nomor rekening ditemukan
        boolean isAccountFound = false;
        for (WebElement account : savedAccounts) {
            String accountNumber = account.getText();
            if (accountNumber.equals(nomorEWallet)) {
                isAccountFound = true;
                break;
            }
        }

        // Assertion
        Assert.assertTrue(isAccountFound, "Nomor rekening '" + nomorEWallet + "' tidak ditemukan di daftar tersimpan.");
    }







    //TC_WA_052 ToFavoriteAccountEWallet

    public void clickLoveToAddToFavoriteList() {
        List<WebElement> contactItems = driver.findElements(By.xpath("//div[@id='contact-item']"));

        for (WebElement item : contactItems) {
            String name = item.findElement(By.tagName("h6")).getText();
            if (name.equals("Kendrick Lamar")) {
                WebElement loveButton = item.findElement(By.xpath(".//svg[@fill='currentColor']"));
                loveButton.click();
                break;
            }
        }
    }


    public void validateSuccessFavoriteAccountEWallet(String nomorEWallet) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactList = wait.until(ExpectedConditions.visibilityOfElementLocated(contactList()));

        // Scroll ke daftar tersimpan
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", contactList);

        // Ambil semua elemen yang mengandung nomor rekening dalam daftar tersimpan
        List<WebElement> favoriteAccounts = driver.findElements(By.xpath("//div[@id='contact-list']//span[contains(@class, 'text-[10px]')]"));

        // Cek apakah nomor rekening ditemukan
        boolean isAccountFound = false;
        for (WebElement account : favoriteAccounts) {
            String accountNumber = account.getText();
            if (accountNumber.equals(nomorEWallet)) {
                isAccountFound = true;
                break;
            }
        }

        // Validasi
        Assert.assertTrue(isAccountFound, "Nomor rekening '" + nomorEWallet + "' tidak ditemukan di daftar favorite.");
    }









    //TC_WA_053 SavedAccountEWalletAfterSuccessTopUp(ShopeePay)

    public void goToShopeePayMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shopeePayMenu()));
        driver.findElement(shopeePayMenu()).click();
    }
    public void goToEWalletMenuAndChooseShopeePay() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shopeePayMenu()));
        driver.findElement(shopeePayMenu()).click();
    }


    //TC_WA_055 SavedAccountEWalletAfterSuccessTopUp(GoPay)

    public void goToGopayMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(goPayMenu()));
        driver.findElement(goPayMenu()).click();
    }
    public void goToEWalletMenuAndChooseGoPay() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(goPayMenu()));
        driver.findElement(goPayMenu()).click();
    }



    //TC_WA_057 SavedAccountEWalletAfterSuccessTopUp(Dana)

    public void goToDanaMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(danaMenu()));
        driver.findElement(danaMenu()).click();
    }
    public void goToEWalletMenuAndChooseDana() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(danaMenu()));
        driver.findElement(danaMenu()).click();
    }



    //TC_WA_059 SavedAccountEWalletAfterSuccessTopUp(Link Aja)

    public void goToLinkAjaMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkAjaMenu()));
        driver.findElement(linkAjaMenu()).click();
    }
    public void goToEWalletMenuAndChooseLinkAja() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkAjaMenu()));
        driver.findElement(linkAjaMenu()).click();
    }




}
