package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Products']");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By CLOSE_MENU_ICON = By.id("react-burger-cross-btn");
    public static final By LOGOUT_MENU_OPTION = By.id("logout_sidebar_link");
    public static final String REMOVE_BUTTON = "REMOVE";
    public static final String ADD_TO_CART_BUTTON = "ADD TO CART";

    private static final String PRODUCT_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }

    public void addProductToCart(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).click();
    }

    public boolean openMenuAndValidation() {
        driver.findElement(MENU_BUTTON).click();
        return driver.findElement(CLOSE_MENU_ICON).isDisplayed();
    }

    public void pushMenuOption(By menuOption) {
        driver.findElement(menuOption).click();
    }

    public String checkButtonTitle(String partialProductTitle) {
        return driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).getText();
    }

}
