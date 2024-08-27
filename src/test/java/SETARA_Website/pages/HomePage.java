package SETARA_Website.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By menuTransactionInHomepage = By.xpath("//h1[text()='Menu']");




    public HomePage(WebDriver driver){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.driver = driver;
    }

    //Method Action
    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getMenuTransactionInHomepage(){
        return driver.findElement(menuTransactionInHomepage).getText();
    }


}
