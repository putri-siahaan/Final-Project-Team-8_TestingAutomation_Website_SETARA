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


public class TransferAntarBCA extends PageObject {

    // Case Saved Account Rekening
    private By cheklistBox(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/form/div[2]/div/div/div/div/label");
    }

    private By kembaliKeHomepageButton(){
        return By.xpath("//*[@id='root']/main/div/div[2]/button[1]");
    }

    private By contactList(){return By.id("contact-list");}



    // TF Antar BCA to New Account Rekening

    private By transferAntarBCAMenu(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/div[1]/div");
    }

    private By transferKeTujuanBaruButton(){
        return By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]/div/button/span");
    }

    private By nomorTransferBaruField(){
        return By.xpath("//input[@aria-label='Input nomor transfer baru']");
    }

    private By cariNomorButton(){
        return By.xpath("//button[@aria-label='Cari Nomor']");
    }

    private By sumberRekeningDropdown(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/form/div[4]/div/div[2]/div/div/div");
    }

    private By chooseSumber(){
        return By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div");
    }

    private By nominalTransferField(){
        return By.xpath("//div[@class='ant-input-number-input-wrap']/input[@id='amount']");
    }

    private By catatanField(){
        return By.id("notes");
    }

    private By lanjutkanButton(){
        return By.xpath("//button[@aria-label='Lanjutkan']");
    }

    private By transferButton(){
        return By.xpath("//button[@type='button' and .//span[text()='Transfer']]");
    }

    private By pinField(){
        return By.xpath("//span[contains(@class, 'ant-input-affix-wrapper') and contains(@class, 'input-label')]//input[@type='password' and @placeholder='Masukkan PIN Anda']");
    }

    private By lanjutkanPINButton(){
        return By.xpath("//*[@id='basic']/div[2]/div/div/div/div/button/span");
    }

    private By successTransferBCA(){
        return By.xpath("//p[text()='Transaksi Berhasil']");
    }

    private By errorMessageDestinationRekening(){
        return By.xpath("//*[@id='destinationNumber_help']/div");
    }

    private By errorMessageNominalTf(){
        return By.xpath("//*[@id='amount_help']/div");
    }

    private By errorMessagePIN(){
        return By.xpath("//*[@id='basic_pin_help']/div");
    }


    // Transfer By Saved List
    private By AccountRekeningFromSavedList(){
        return By.xpath("//*[@id='contact-list']/div");
    }

    private By lanjutkanButtonByFavoriteOrSavedList(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div/div/div/form/button");
    }

    // Transfer By Favorite List
    private By AccountRekeningFromFavoriteList(){
        return By.xpath("//*[@id='contact-list']/div/div");
    }







    WebDriver driver;
    WebDriverWait wait;

    public TransferAntarBCA(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Method
    //TC_WA_010 SavedAccountRekeningAfterSuccessTransfer
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


    public void validateSuccessSavedAccountRekening(String nomorRekening) {
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
            if (accountNumber.equals(nomorRekening)) {
                isAccountFound = true;
                break;
            }
        }

        // Assertion
        Assert.assertTrue(isAccountFound, "Nomor rekening '" + nomorRekening + "' tidak ditemukan di daftar tersimpan.");
    }







    //TC_WA_011 ToFavoriteAccountRekening

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


    public void validateSuccessFavoriteAccountRekening(String nomorRekening) {
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
            if (accountNumber.equals(nomorRekening)) {
                isAccountFound = true;
                break;
            }
        }

        // Validasi
        Assert.assertTrue(isAccountFound, "Nomor rekening '" + nomorRekening + "' tidak ditemukan di daftar favorite.");
    }






    //TC_WA_012 sd TC_WA_018 TF To New Account Rekening

    public void goToTransferAntarBCAMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferAntarBCAMenu()));
        driver.findElement(transferAntarBCAMenu()).click();
    }

    public void clickTransferKeTujuanBaru() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferKeTujuanBaruButton()));
        driver.findElement(transferKeTujuanBaruButton()).click();
    }

    public void inputNomorTransferBaru(String nomorTransferBaru) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nomorTransferBaruField()));
        driver.findElement(nomorTransferBaruField()).sendKeys(nomorTransferBaru);
    }

    public void clickCariNomor() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cariNomorButton()));
        driver.findElement(cariNomorButton()).click();
        driver.findElement(cariNomorButton()).click();
    }

    public boolean isErrorDestinationDisplayed() {
        try {
            WebElement errorElementDestination = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageDestinationRekening()));
            return errorElementDestination.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void chooseSumberRekening() {
        try {
            // Wait for the sumberRekeningDropdown element to be clickable
            WebElement sumberRekeningElement = wait.until(ExpectedConditions.elementToBeClickable(sumberRekeningDropdown()));

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
            WebElement chooseSumberElement = wait.until(ExpectedConditions.elementToBeClickable(chooseSumber()));

            // Click the chooseSumber element to select the dropdown option
            chooseSumberElement.click();

        } catch (InterruptedException e) {
            // Handle the InterruptedException here
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println("Thread was interrupted, Failed to complete operation");
        }
    }

    public void inputNominalTransfer(String nominalTransfer) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nominalTransferField()));
        driver.findElement(nominalTransferField()).sendKeys(nominalTransfer);
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
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageNominalTf()));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickTransfer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferButton()));
        driver.findElement(transferButton()).click();
    }

    public void inputPIN(String pin) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pinField()));
        driver.findElement(pinField()).sendKeys(pin);
    }

    public void clickLanjutkanPIN() {
        driver.findElement(lanjutkanPINButton()).click();
    }

    public void getExpectedResultMessageSuccessTransfer(String successTransfer) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(successTransferBCA()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(successTransfer)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + successTransfer + "'");
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
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(errorMessageDestinationRekening()));

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




    // Error In Nominal
    @Step
    public void getErrorMessageInNominal(String expectedMessages) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(errorMessageNominalTf()));

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








    //TC_WA_019_sd_024 TransferByChoosingFromSavedList
    public void chooseAccountRekeningFromSavedList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountRekeningFromSavedList()));
        driver.findElement(AccountRekeningFromSavedList()).click();
    }

    public void clickLanjutkanAfterInputData() {
        WebElement lanjutkanElement = wait.until(ExpectedConditions.elementToBeClickable(lanjutkanButtonByFavoriteOrSavedList()));

        // Scroll to the sumberRekeningDropdown element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lanjutkanElement);
        driver.findElement(lanjutkanButtonByFavoriteOrSavedList()).click();
    }







    //TC_WA_025_sd_030 TransferByChoosingFromFavoriteList
    public void chooseAccountRekeningFromFavoriteList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountRekeningFromFavoriteList()));
        driver.findElement(AccountRekeningFromFavoriteList()).click();
    }



}
