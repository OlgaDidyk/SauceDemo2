package web.pages;

import org.openqa.selenium.*;
import web.base.BasePage;
import web.elements.BurgerMenuElement;
import web.elements.CartIconElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatalogPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Products']");
    public static final By ANY_PRODUCT_LOCATOR = By.cssSelector(".inventory_item_name");
    public static final By SORT_DROPDOWN_LOCATOR = By.cssSelector(".product_sort_container");
    public static final String REMOVE_BUTTON = "REMOVE";
    public static final String ADD_TO_CART_BUTTON = "ADD TO CART";
    private static final By SHOPPING_CART_BADGE = By.className("shopping_cart_badge");
    public static final String SORT_OPTION_ZA_NAME = "Z to A";
    public static final String SORT_OPTION_AZ_NAME = "A to Z";

    public static final String SORT_OPTION_PATTERN = "//option[contains(text(), '%s')]";
    private static final String PRODUCT_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";

    public BurgerMenuElement burgerMenu;
    public CartIconElement cartIconElement;

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.burgerMenu = new BurgerMenuElement(driver);
        this.cartIconElement = new CartIconElement(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public CatalogPage addProductToCart(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).click();
        return new CatalogPage(driver);
    }

    public String checkButtonTitle(String partialProductTitle) {
        return driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).getText();
    }

    public CatalogPage openSortDropdown() {
        driver.findElement(SORT_DROPDOWN_LOCATOR).click();
        return new CatalogPage(driver);
    }

    public CatalogPage setSortingOption(String partialSortOptionName) {
        openSortDropdown();
        String menuOptionXPath = String.format(SORT_OPTION_PATTERN, partialSortOptionName);
        By sortOptionLocator = By.xpath(menuOptionXPath);
        driver.findElement(sortOptionLocator).click();
        getProductList();
        return new CatalogPage(driver);
    }

    public ArrayList<String> getProductList() {
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> productList = driver.findElements(ANY_PRODUCT_LOCATOR);
        for (WebElement product : productList) {
            obtainedList.add(product.getText());
        }
        System.out.println("Product list from the page: " + obtainedList);
        return obtainedList;
    }

    public ArrayList<String> sortingProductListZtoA() {
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : getProductList()) {
            sortedList.add(s);
        }
        Collections.reverse(sortedList);
        System.out.println("Expected Z to A: " + sortedList);
        return sortedList;
    }

    public ArrayList<String> sortingProductListAtoZ() {
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : getProductList()) {
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        System.out.println("Expected A to Z: " + sortedList);
        return sortedList;
    }

    public boolean isBadgeDisplayed(int durationOfSeconds) throws InterruptedException {
        for (int i = 0; i < durationOfSeconds; i++) {
            try {
                driver.findElement(SHOPPING_CART_BADGE);
                return true;
            } catch (NoSuchElementException ex) {
                Thread.sleep(1000);
                System.out.printf("%s seconds left\n", durationOfSeconds - i);
            }
        }
        return false;
    }

}
