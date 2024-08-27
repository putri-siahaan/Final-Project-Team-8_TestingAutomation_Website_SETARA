package SETARA_Website.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class QRISPayement extends PageObject {

    // Locators
    private By scanQRCodeButton(){
        return By.xpath("//*[@id='root']/main/div/div/div[2]/div[1]/button");
    }
    private By chooseFileQR(){
        return By.xpath("//*[@id='file-upload']");
    }
    private By nominalTransferField(){
        return By.xpath("//div[@class='ant-input-number-input-wrap']/input[@id='amount']");
    }
    private By catatanField(){
        return By.id("notes");
    }
    private By lanjutkanButton(){
        return By.xpath("//*[@id='root']/main/div/div/div[2]/form/div/div[2]/button/span");
    }
    private By konfirmasiButton(){
        return By.xpath("//*[@id='root']/main/div/div[2]/div[2]/button");
    }
    private By pinField(){
        return By.xpath("//span[contains(@class, 'ant-input-affix-wrapper') and contains(@class, 'input-label')]//input[@type='password' and @placeholder='Masukkan PIN Anda']");
    }
    private By lanjutkanPINButton(){
        return By.xpath("//*[@id='basic']/div[2]/div/div/div/div/button/span");
    }
    private By successTransferWithQRIS(){
        return By.xpath("//p[text()='Transaksi Berhasil']");
    }
    private By errorMessageInvalidImage(){
        return By.xpath("//*[@id='root']/main/div/div/div[2]/div/p[1]");
    }
    private By errorMessageNominalTf(){
        return By.xpath("//*[@id='amount_help']/div");
    }




    // Constructor
    WebDriver driver;
    WebDriverWait wait;

    public QRISPayement(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void openQRISPaymentPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(scanQRCodeButton()));
        driver.findElement(scanQRCodeButton()).click();
    }


    public void uploadJpeg(String filePath) {
        WebElement uploadElement = driver.findElement(chooseFileQR());

        // Kirimkan jalur file ke elemen input
        uploadElement.sendKeys(filePath);

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

    public void clickKonfirmasiButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(konfirmasiButton()));
        driver.findElement(konfirmasiButton()).click();
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
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(successTransferWithQRIS()));

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





    // @TC_WA_076 FailedToTransferBecauseInvalidImage

    public void getErrorMessageInvalidImage(String expectedMessages) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(errorMessageInvalidImage()));

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








        // TC_WA_077 sd TC_WA_079 FailedToTransferBecauseNominal0

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
    }











