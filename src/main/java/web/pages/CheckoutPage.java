package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.base.BasePage;

public class CheckoutPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/checkout-step-one.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Your Information']");
    public static final By FIRST_NAME_LOCATOR = By.id("first-name");
    public static final By LAST_NAME_LOCATOR = By.id("last-name");
    public static final By POSTAL_CODE_LOCATOR = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");


    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public CheckoutPage fillInField(By fieldLocator, String fieldValue) {
        driver.findElement(fieldLocator).sendKeys(fieldValue);
        return this;

    }

    public void pushContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }
}
