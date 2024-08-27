package SETARA_Website.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InfoSaldo extends PageObject{

    // Locator melihat saldo di Homepage
    private By tampilkanSaldoIcon() {
        return By.xpath("//button[@aria-label='Saldo ditampilkan']");
    }
    private By SaldoDiHomepage() {
        return By.className("text-heading-6");
    }


    // Locator melihat saldo di menu "Info Saldo"
    private By menuInfoSaldo() {
        return By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div/div[2]/div[1]/img");
    }
    private By titleInfoSaldo() {
        return By.xpath("//h1[text()='Info Saldo']");
    }
    private By detailTotalInfoSaldo() {
        return By.xpath("//p[contains(@class, 'text-body-large') and contains(@class, 'font-semibold')]");
    }



    WebDriver driver;
    WebDriverWait wait;

    public InfoSaldo(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Method untuk mendapatkan saldo di homepage
    @Step
    public void clickIconUntukMelihatSaldo(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(tampilkanSaldoIcon()));
        driver.findElement(tampilkanSaldoIcon()).click();
    }

    @Step
    public String getInfoSaldoDiHomepage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SaldoDiHomepage()));
        return driver.findElement(SaldoDiHomepage()).getText();
    }



    // Method untuk membuka menu "Info Saldo"
    @Step
    public void clickInfoSaldoMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuInfoSaldo()));
        driver.findElement(menuInfoSaldo()).click();
    }

    @Step
    public String getTitleInfoSaldo() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInfoSaldo()));
        return driver.findElement(titleInfoSaldo()).getText();
    }

    // Method untuk mendapatkan detail saldo
    @Step
    public String getDetailedBalance() {
        return driver.findElement(detailTotalInfoSaldo()).getText();
    }
}
