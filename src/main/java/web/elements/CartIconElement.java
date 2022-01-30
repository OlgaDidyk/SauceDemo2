package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import web.base.AbstractElement;
import web.base.BasePage;

public class CartIconElement extends AbstractElement {
    public static final By CART_ICON = By.id("shopping_cart_container");
    public static final By CART_COUNTER = By.cssSelector("[class='shopping_cart_badge']");

    public CartIconElement(WebDriver driver) {
        super(driver);
        this.baseElementLocator = CART_ICON;
    }

    public boolean emptyCartHasNotCounter() throws InterruptedException {

        try {
            driver.findElement(CartIconElement.CART_COUNTER);
            return true;
        } catch (NoSuchElementException ex) {
            Thread.sleep(1000);

        }
        return false;

    }


}
