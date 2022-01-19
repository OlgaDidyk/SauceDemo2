package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/cart.html";

    private static final By TITLE_LOCATOR = By.xpath(".//span[@class='title' and text()='Your Cart']");
    private static final By PRODUCT_TITLE_IN_CART = By.className("inventory_item_name");
    private static final By CHECKOUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }

    public boolean validateAddedProducts(List<String> partialTitles) {
        List<WebElement> products = driver.findElements(PRODUCT_TITLE_IN_CART);

        if (products.isEmpty()) {
            return false;
        }

        for (WebElement product : products) {
            String productTitle = product.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
            if (!partialTitles.contains(productTitle)) {
                return false;
            }
        }
        return true;
    }

    public void pushCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();

    }

/*    public boolean createListOfTitles(String title){
        List<String> titles = new ArrayList<>();
        titles.add(title);
        return validateAddedProducts(titles);
    }*/
}
