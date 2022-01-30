package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.base.BasePage;

public class CheckoutStepTwoPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/checkout-step-two.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Overview']");
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public void pushFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }
}
