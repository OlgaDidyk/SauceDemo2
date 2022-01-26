package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {
    //Эту страницу делала до того, как Рома рассказал про такие области на странице. В следующей домашке исправлю.
    public static final By CART_ICON = By.id("shopping_cart_container");
    public static final By CART_COUNTER = By.cssSelector("[class='shopping_cart_badge']");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public boolean counterIsShown() {
        if (wait.until(ExpectedConditions.invisibilityOf(driver.findElement(CART_COUNTER)))) {
            return false;
        }
        return true;
    }


}
