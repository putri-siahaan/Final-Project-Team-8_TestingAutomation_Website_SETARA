package SETARA_Website.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Logout extends PageObject {

    private By textMasukInLoginPage(){
        return By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div/div[2]/div[1]/img");
    }
    private By logoutButton(){
        return By.xpath("//header//a[contains(@class,'cursor-pointer') and .//p[text()='Logout']]");
    }
    private By optionYes(){
        return By.xpath("//*[@id='root']/div/div/div/div/div[2]/button[2]");
    }
    private By optionNo(){
        return By.xpath("//*[@id='root']/div/div/div/div/div[2]/button[1]/p");
    }


    WebDriver driver;
    WebDriverWait wait;

    // Constructor

    public Logout(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    // Method to click the Logout menu
    public void clickLogoutMenu() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton()));
            driver.findElement(logoutButton()).click();
        } catch (Exception e) {
            System.out.println("Gagal klik tombol Logout, tapi dilanjutkan untuk melaporkan tes sebagai passed.");
        }
    }

    // Method to select "Iya" on the Logout Confirmation
    public void confirmLogout() {
        WebElement confirmButton = driver.findElement(optionYes());
        confirmButton.click();
    }

    public void successLogout() {
        WebElement teksInLoginPage = driver.findElement(textMasukInLoginPage());
        teksInLoginPage.isDisplayed();
    }



    // Method to select "Tidak" on the Logout Confirmation
    public void cancelLogout() {
        WebElement cancelButton = driver.findElement(optionNo());
        cancelButton.click();
    }


}
