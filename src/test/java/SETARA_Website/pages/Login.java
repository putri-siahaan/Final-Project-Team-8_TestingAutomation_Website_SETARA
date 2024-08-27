package SETARA_Website.pages;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Login extends PageObject {

    private By textMasukInLoginPage(){
        return By.xpath("//h1[text()='Masuk']");
    }
    private By usernameIdField(){
        return By.id("username");
    }
    private By passwordField(){
        return By.id("password");
    }
    private By eyesIconToSeePassword(){
        return By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div[1]/div/div[1]/div/span/span/span");
    }
    private By masukButton(){
        return By.xpath("//*[@id=\"root\"]/div/div/form/div[3]/div/div/div/div/button");
    }
    private By messageSuccessLogin(){
        return By.className("ant-notification-notice-description");
    }
    private By messageFailedLoginBecauseInvalidUsernameorPassword(){
        return By.className("ant-notification-notice-description");
    }

    private By emptyUsername(){
        return By.xpath("//div[@id='username_help']//div[contains(@class,'ant-form-item-explain-error')]");
    }

    private By emptyPassword(){
        return By.xpath("//div[@id='password_help']//div[contains(@class,'ant-form-item-explain-error')]");
    }


    //TC_AW_1 [Positive]


    WebDriver driver;
    WebDriverWait wait;


    public Login(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTextMasukInLoginPage(){
        return driver.findElement(textMasukInLoginPage()).getText();
    }

    @Step
    public void inputUsernameId(String validUsernameId){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameIdField()));
        driver.findElement(usernameIdField()).sendKeys(validUsernameId);
    }

    @Step
    public void inputPassword(String validPassword){
        driver.findElement(passwordField()).sendKeys(validPassword);
    }

    @Step
    public void clickEyesIcon(){
        driver.findElement(eyesIconToSeePassword()).click();
    }

    @Step
    public void clickMasukButton(){
        driver.findElement(masukButton()).click();
    }

    @Step
    public void validateSuccessLogin(String successLogin) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(messageSuccessLogin()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(successLogin)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + successLogin + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }

    }














    //TC_AW_2-4 [Negative]

    @Step
    public void getErrorMessageInvalidUsernameOrPassword(String expectedMessage) {
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(messageFailedLoginBecauseInvalidUsernameorPassword()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(expectedMessage)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + expectedMessage + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }





    //TC_AW_5 [Negative 4]

    @Step
    public void validateFailedLoginBecauseEmptyUsername(String expectedMessage){
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(emptyUsername()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(expectedMessage)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + expectedMessage + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }













    //TC_AW_6 [Negative 5]

    @Step
    public void validateFailedLoginBecauseEmptyKataSandi(String expectedMessage){
        try {
            // Tunggu hingga elemen alert muncul di DOM
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(emptyPassword()));

            // Mengambil teks alert menggunakan JavaScriptExecutor
            String messageText = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", alertElement);

            // Validasi teks alert
            if (!messageText.equalsIgnoreCase(expectedMessage)) {
                throw new AssertionError("Validation failed! Actual result '" + messageText +
                        "' does not match expected result '" + expectedMessage + "'");
            }

        } catch (TimeoutException e) {
            throw new AssertionError("Validation failed! Alert message did not appear within the expected time.", e);
        } catch (NoSuchElementException | JavascriptException e) {
            throw new AssertionError("Validation failed! Alert message element not found or could not retrieve text.", e);
        }
    }


}
