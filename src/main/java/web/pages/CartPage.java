package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.base.BasePage;
import web.elements.BurgerMenuElement;
import web.elements.CartIconElement;

import java.util.List;

public class CartPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/cart.html";

    private static final By TITLE_LOCATOR = By.xpath(".//span[@class='title' and text()='Your Cart']");
    private static final By PRODUCT_TITLE_IN_CART = By.className("inventory_item_name");
    public static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By PRODUCT_LOCATOR = By.xpath("//div[@class='inventory_item_name']");

    public BurgerMenuElement burgerMenu;
    public CartIconElement cartIconElement;

    public CartPage(WebDriver driver) {
        super(driver);
        this.burgerMenu = new BurgerMenuElement(driver);
        this.cartIconElement = new CartIconElement(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public boolean validateAddedProducts(List<String> partialTitles) {
        List<WebElement> products = driver.findElements(PRODUCT_TITLE_IN_CART);

        if (products.isEmpty()) {
            return false;
        }

        for (WebElement product : products) {
            String productTitle = product.findElement(PRODUCT_LOCATOR).getText();
            if (!partialTitles.contains(productTitle)) {
                return false;
            }
        }
        return true;
    }

    public CheckoutPage pushButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }


}
