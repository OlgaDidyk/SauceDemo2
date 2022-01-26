package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatalogPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Products']");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By CLOSE_MENU_ICON = By.id("react-burger-cross-btn");
    public static final By ANY_PRODUCT_LOCATOR = By.cssSelector(".inventory_item_name");
    public static final By SORT_DROPDOWN_LOCATOR = By.cssSelector(".product_sort_container");
    public static final String REMOVE_BUTTON = "REMOVE";
    public static final String ADD_TO_CART_BUTTON = "ADD TO CART";
    public static final String LOGOUT_BUTTON_NAME = "Logout";
    public static final String SORT_OPTION_ZA_NAME = "Z to A";
    public static final String SORT_OPTION_AZ_NAME = "A to Z";

    public static final String SORT_OPTION_PATTERN = "//option[contains(text(), '%s')]";
    public static final String MENU_BUTTON_PATTERN = "//a[contains(text(),'%s')]";
    private static final String PRODUCT_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.basePageElement = TITLE_LOCATOR;
    }

    public void addProductToCart(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).click();
    }

    public boolean openMenu() {
        driver.findElement(MENU_BUTTON).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CatalogPage.CLOSE_MENU_ICON));
        } catch (TimeoutException timeoutException) {
            return false;
        }
        return true;
    }

    public void pushMenuOption(String partialBtnName) {
        String buttonXPath = String.format(MENU_BUTTON_PATTERN, partialBtnName);
        By buttonLocator = By.xpath(buttonXPath);
        driver.findElement(buttonLocator).click();
    }

    public String checkButtonTitle(String partialProductTitle) {
        return driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).getText();
    }

    public void openSortDropdown() {
        driver.findElement(SORT_DROPDOWN_LOCATOR).click();
    }

    public void setSortingOption(String partialSortOptionName) {
        openSortDropdown();
        String menuOptionXPath = String.format(SORT_OPTION_PATTERN, partialSortOptionName);
        By sortOptionLocator = By.xpath(menuOptionXPath);
        driver.findElement(sortOptionLocator).click();
        getProductList();
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
}
