package SETARA_Website.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Mutasi extends PageObject {
    WebDriver driver;

    private By downloadButton() {
        return By.xpath("//*[@id='root']/main/div/div[3]/div[2]/button[1]");
    }

    private By filterButton() {
        return By.xpath("//*[@id='root']/main/div/div[3]/div[2]/button[2]");
    }

    private By optionHariIni() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[1]/label");
    }

    private By option7HariYangTerakhir() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[2]/label");
    }

    private By option15HariTerakhir() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[3]/label");
    }

    private By option1BulanTerakhir() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[4]/label");
    }

    private By optionTanggalLain() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[5]/label");
    }

    private By boxDate() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[5]/label/span[2]/div/div/div[1]/input");
    }

    private By tanggal20Agustus() {
        return By.xpath("//td[@title='2024-08-20' and contains(@class, 'ant-picker-cell')]");
    }

    private By tanggal24Agustus() {
        return By.xpath("//td[@title='2024-08-24' and contains(@class, 'ant-picker-cell')]");
    }

    private By okButton() {
        return By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[2]/button[2]");
    }

    private By dataMutasi() {
        return By.xpath("//*[@id='root']/main/div/div[4]");
    }

    private By semuaOption() {
        return By.xpath("//*[@id='root']/main/div/div[2]/button[1]");
    }

    private By pemasukanOption() {
        return By.xpath("//*[@id='root']/main/div/div[2]/button[2]");
    }

    private By pengeluaranOption() {
        return By.xpath("//*[@id='root']/main/div/div[2]/button[3]");
    }

    private By lihatBuktiTransfer24Agusutus() {
        return By.xpath("//*[@id='root']/main/div/div[4]/div[2]/div[2]/a");
    }

    private By titleBuktiTransfer() {
        return By.xpath("//*[@id='root']/main/div/div[1]/div/h5");
    }

    private By downloadBuktiTransferButton() {
        return By.xpath("//*[@id='root']/main/div/button/span");
    }

    WebDriverWait wait;


    public Mutasi(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //TC_WA_061 Hari Ini

    public void selectFilterHariIni() {
        driver.findElement(filterButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionHariIni()));
        driver.findElement(optionHariIni()).click();
        driver.findElement(okButton()).click();
    }

    public void selectSemuaTransaksi() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(semuaOption()));
        driver.findElement(semuaOption()).click();
    }

    public boolean verifyTransactionHistoryTodayIsDisplayed() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
        String formattedDate = today.format(formatter);

        try {
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));
            List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath(".//p[contains(text(), '" + formattedDate + "')]"));

            if (!transactionElements.isEmpty()) {
                System.out.println("Transaction found for date: " + formattedDate);
                return true;
            } else {
                System.out.println("No transaction found for date: " + formattedDate);
            }

            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath(".//h5[contains(text(), 'Belum Ada Transaksi :/')]"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                System.out.println("Message 'Belum Ada Transaksi :/' is displayed");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean isNoTransactionMessageDisplayed() {
        try {
            WebElement noTransactionMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h5[contains(text(), 'Belum Ada Transaksi :/')]")));
            return noTransactionMessageElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    //TC_WA_062 7 Hari Terakhir

    public void selectFilter7HariTerakhir() {
        driver.findElement(filterButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(option7HariYangTerakhir()));
        driver.findElement(option7HariYangTerakhir()).click();
        driver.findElement(okButton()).click();
    }


    public boolean verifyTransactionHistory7HariTerakhirIsDisplayed() {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

        try {
            // Tunggu hingga elemen data mutasi muncul
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));

            // Iterasi untuk setiap tanggal dalam rentang 7 hari terakhir
            for (LocalDate date = today; !date.isBefore(sevenDaysAgo); date = date.minusDays(1)) {
                String formattedDate = date.format(formatter);
                List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath(".//p[contains(text(), '" + formattedDate + "')]"));

                if (!transactionElements.isEmpty()) {
                    return true;  // Jika ada transaksi pada salah satu dari 7 hari terakhir, validasi berhasil
                }
            }

            // Jika tidak ada transaksi, cek apakah ada pesan "Belum Ada Transaksi :/"
            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath(".//h5[contains(text(), 'Belum Ada Transaksi :/')]"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                return false;  // Jika pesan ditemukan, berarti tidak ada transaksi
            }

        } catch (NoSuchElementException e) {
            return false;  // Jika elemen tidak ditemukan, ini bisa berarti tidak ada transaksi atau pesan "Belum Ada Transaksi :/" tidak ada
        }
        return false;
    }


    //TC_WA_063 15 Hari Terakhir

    public void selectFilter15HariTerakhir() {
        driver.findElement(filterButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(option15HariTerakhir()));
        driver.findElement(option15HariTerakhir()).click();
        driver.findElement(okButton()).click();
    }

    public boolean verifyTransactionHistory15HariTerakhirIsDisplayed() {
        LocalDate today = LocalDate.now();
        LocalDate fifteenDaysAgo = today.minusDays(15);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

        try {
            // Tunggu hingga elemen data mutasi muncul
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));

            // Iterasi untuk setiap tanggal dalam rentang 15 hari terakhir
            for (LocalDate date = today; !date.isBefore(fifteenDaysAgo); date = date.minusDays(1)) {
                String formattedDate = date.format(formatter);
                List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath(".//p[contains(text(), '" + formattedDate + "')]"));

                if (!transactionElements.isEmpty()) {
                    return true;  // Jika ada transaksi pada salah satu dari 15 hari terakhir, validasi berhasil
                }
            }
            // Jika tidak ada transaksi, cek apakah ada pesan "Belum Ada Transaksi :/"
            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath(".//h5[contains(text(), 'Belum Ada Transaksi :/')]"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                return false;  // Jika pesan ditemukan, berarti tidak ada transaksi
            }
        } catch (NoSuchElementException e) {
            return false;  // Jika elemen tidak ditemukan, ini bisa berarti tidak ada transaksi atau pesan "Belum Ada Transaksi :/" tidak ada
        }
        return false;
    }


    //TC_WA_064 1 Bulan Terakhir

    public void selectFilter1BulanTerakhir() {
        driver.findElement(filterButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(option1BulanTerakhir()));
        driver.findElement(option1BulanTerakhir()).click();
        driver.findElement(okButton()).click();
    }

    public boolean verifyTransactionHistory1BulanTerakhirIsDisplayed() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

        try {
            // Tunggu hingga elemen data mutasi muncul
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));

            // Iterasi untuk setiap tanggal dalam rentang 1 bulan terakhir
            for (LocalDate date = today; !date.isBefore(oneMonthAgo); date = date.minusDays(1)) {
                String formattedDate = date.format(formatter);
                List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath(".//p[contains(text(), '" + formattedDate + "')]"));

                if (!transactionElements.isEmpty()) {
                    return true;  // Jika ada transaksi pada salah satu dari 1 bulan terakhir, validasi berhasil
                }
            }
            // Jika tidak ada transaksi, cek apakah ada pesan "Belum Ada Transaksi :/"
            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath(".//h5[contains(text(), 'Belum Ada Transaksi :/')]"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                return false;  // Jika pesan ditemukan, berarti tidak ada transaksi
            }
        } catch (NoSuchElementException e) {
            return false;  // Jika elemen tidak ditemukan, ini bisa berarti tidak ada transaksi atau pesan "Belum Ada Transaksi :/" tidak ada
        }
        return false;
    }


    //TC_WA_065 Tanggal Lain

    public void selectFilterTanggalLainFrom20AgustusTo24Agustus() {
        driver.findElement(filterButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTanggalLain()));
        driver.findElement(optionTanggalLain()).click();
        driver.findElement(boxDate()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggal20Agustus()));
        driver.findElement(tanggal20Agustus()).click();
        driver.findElement(tanggal24Agustus()).click();
        driver.findElement(okButton()).click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public boolean verifyTransactionHistoryFrom20To24AugustIsDisplayed() {
        // Tentukan tanggal 20 dan 24 Agustus
        LocalDate startDate = LocalDate.of(2024, 8, 20);
        LocalDate endDate = LocalDate.of(2024, 8, 24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

        try {
            // Tunggu hingga elemen data mutasi muncul
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));

            // Iterasi untuk setiap tanggal dalam rentang 20-24 Agustus
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                String formattedDate = date.format(formatter);
                List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath(".//p[contains(text(), '" + formattedDate + "')]"));

                if (!transactionElements.isEmpty()) {
                    return true;  // Jika ada transaksi pada salah satu dari tanggal tersebut, validasi berhasil
                }
            }

            // Jika tidak ada transaksi, cek apakah ada pesan "Belum Ada Transaksi :/"
            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath(".//h5[contains(text(), 'Belum Ada Transaksi :/')]"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                return false;  // Jika pesan ditemukan, berarti tidak ada transaksi
            }

        } catch (NoSuchElementException e) {
            return false;  // Jika elemen tidak ditemukan, ini bisa berarti tidak ada transaksi atau pesan "Belum Ada Transaksi :/" tidak ada
        }
        return false;
    }


    //TC_WA_066 Detail Transaction

    public void clickOnTransaction() {
        driver.findElement(filterButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTanggalLain()));
        driver.findElement(optionTanggalLain()).click();
        driver.findElement(boxDate()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggal24Agustus()));
        driver.findElement(tanggal24Agustus()).click();
        driver.findElement(tanggal24Agustus()).click();
        driver.findElement(okButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lihatBuktiTransfer24Agusutus()));
        driver.findElement(lihatBuktiTransfer24Agusutus()).click();
    }

    public void verifyTransactionReceiptDisplayed(String buktiTransferTitle) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(titleBuktiTransfer()));

            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            if (!messageText.equalsIgnoreCase(buktiTransferTitle)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + buktiTransferTitle + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (java.util.NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }

    public void clickDownloadButton() {
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(downloadBuktiTransferButton()));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", downloadButton);

        driver.findElement(downloadBuktiTransferButton()).click();
    }

    public boolean verifyDownloadSuccess() {
        // Lokasi download folder
        String downloadDir = "C://Users//user//Downloads";

        // nama file
        String expectedFilePattern = "Mutasi Rekening - c33bb3ee-f278-49a8-88a8-0a3a58841ea7 - ";

        // Menunggu beberapa saat untuk memastikan download selesai
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verifikasi apakah ada file yang cocok dengan pola nama file di folder download
        File dir = new File(downloadDir);
        File[] dirContents = dir.listFiles();

        if (dirContents == null) {
            System.out.println("Directory is empty or not found.");
            return false;
        }

        for (File file : dirContents) {
            System.out.println("Checking file: " + file.getName());
            if (file.getName().contains(expectedFilePattern)) {
                System.out.println("Found file: " + file.getAbsolutePath());
                // Jika file ditemukan, buka file tersebut
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(file);
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    System.out.println("Desktop is not supported on this environment.");
                    return false;
                }
            }
        }
        System.out.println("No file matching the pattern was found.");
        return false;
    }








    //TC_WA_067 Melihat Data Pemasukan

    public void clickPemasukanButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pemasukanOption()));
        driver.findElement(pemasukanOption()).click();
    }

    public boolean verifySuccessDisplayPemasukkanData() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
        String formattedDate = today.format(formatter);

        try {
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));
            List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath("/html/body/div[1]/main/div/div[4]/h5"));

            if (!transactionElements.isEmpty()) {
                System.out.println("Transaction found for date: " + formattedDate);
                return true;
            } else {
                System.out.println("No transaction found for date: " + formattedDate);
            }

            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath("/html/body/div[1]/main/div/div[4]/h5"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                System.out.println("Message 'Belum Ada Transaksi :/' is displayed");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
        return false;
    }








    //TC_WA_068 sd TC_WA_072 Melihat Data Pengeluaran

    public void clickPengeluaranButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pengeluaranOption()));
        driver.findElement(pengeluaranOption()).click();
    }

    public boolean verifySuccessDisplayPengeluaranData() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
        String formattedDate = today.format(formatter);

        try {
            WebElement mutasiBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dataMutasi()));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[4]")));
            List<WebElement> transactionElements = mutasiBodyElement.findElements(By.xpath("/html/body/div/main/div/div[4]"));

            if (!transactionElements.isEmpty()) {
                System.out.println("Transaction found for date: " + formattedDate);
                return true;
            } else {
                System.out.println("No transaction found for date: " + formattedDate);
            }

            WebElement noTransactionMessageElement = mutasiBodyElement.findElement(By.xpath("/html/body/div/main/div/div[4]"));
            if (noTransactionMessageElement != null && noTransactionMessageElement.isDisplayed()) {
                System.out.println("Message 'Belum Ada Transaksi :/' is displayed");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
        return false;
    }






    // TC_WA_073 Sukses Download Semua Transaksi

    public void clickDownloadButtonInMutasiPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(downloadButton()));
        driver.findElement(downloadButton()).click();
    }

    public boolean verifyDownloadAllTransactionSuccess() {
        // Lokasi download folder
        String downloadDir = "C://Users//user//Downloads";

        // nama file
        String expectedFilePattern = "Mutasi Rekening - ";

        // Menunggu beberapa saat untuk memastikan download selesai
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verifikasi apakah ada file yang cocok dengan pola nama file di folder download
        File dir = new File(downloadDir);
        File[] dirContents = dir.listFiles();

        for (File file : dirContents) {
            if (file.getName().contains(expectedFilePattern)) {
                // Jika file ditemukan, buka file tersebut
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }





}
