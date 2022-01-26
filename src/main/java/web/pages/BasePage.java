package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected String baseUrl;
    protected By basePageElement;
    public static WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public BasePage open() {
        driver.get(baseUrl);
        return this;
    }

    public boolean isPageLoaded() {
        return driver.findElement(basePageElement).isDisplayed();
    }


    public void isPageLoadedWait() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(basePageElement));
    }
}
