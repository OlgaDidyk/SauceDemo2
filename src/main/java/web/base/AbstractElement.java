package web.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractElement {

    protected WebDriver driver;
    public static WebDriverWait explicitWait;
    protected By baseElementLocator;

    public AbstractElement(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}

