package saucedemotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.BasePage;
import web.pages.CatalogPage;
import web.pages.HeaderPage;

import java.util.ArrayList;
import java.util.List;

public class CatalogTest extends BaseTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";
    public static final String TEST_PRODUCT2_TITLE = "Sauce Labs Backpack";

    @BeforeMethod
    private void login() {
        loginPage.open();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @AfterMethod
    private void clearCart() {
        login();
        cartPage.open()
                .isPageLoaded();
        List<WebElement> allButtons = driver.findElements(By.className("cart_button"));
        for (WebElement button : allButtons) {
            if (button.getText().equals("REMOVE")) {
                button.click();
            }
        }
    }

    @Test
    public void addProductToCartTest() {
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);
        cartPage.open();
        cartPage.isPageLoaded();
        List<String> listOfExpectedTitles = new ArrayList<>();
        listOfExpectedTitles.add(TEST_PRODUCT_TITLE);
        Assert.assertTrue(cartPage.validateAddedProducts(listOfExpectedTitles), "Products in the cart is not match");
    }

    @Test
    public void logoutTest() {
        Assert.assertTrue(catalogPage.openMenu(), "Menu is not opened");
        catalogPage.pushMenuOption(CatalogPage.LOGOUT_BUTTON_NAME);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not opened");
    }

    @Test
    public void buttonTitleChangedToRemoveTest() {
        catalogPage.addProductToCart(TEST_PRODUCT2_TITLE);
        Assert.assertEquals(catalogPage.checkButtonTitle(TEST_PRODUCT2_TITLE),
                CatalogPage.REMOVE_BUTTON,
                "Button title is not changed to Remove");
    }

    @Test
    public void buttonTitleChangedToAddToCartTest() {
        buttonTitleChangedToRemoveTest();
        catalogPage.addProductToCart(TEST_PRODUCT2_TITLE);
        Assert.assertEquals(catalogPage.checkButtonTitle(TEST_PRODUCT2_TITLE),
                CatalogPage.ADD_TO_CART_BUTTON, "Button title is not changed to Add to cart");
    }

    @Test
    public void emptyCartHasNoCounterTest() {
        driver.findElement(HeaderPage.CART_ICON);
        Assert.assertTrue(BasePage.wait.until(ExpectedConditions.invisibilityOfElementLocated(HeaderPage.CART_COUNTER)));
    }

    @Test
    public void sortingZtoATest() {
        ArrayList<String> expected = catalogPage.sortingProductListZtoA();
        catalogPage.setSortingOption(CatalogPage.SORT_OPTION_ZA_NAME);
        Assert.assertEquals(catalogPage.getProductList(), expected, "Sorting Z to A is incorrect");
    }

    @Test
    public void sortingAtoZTest() {
        ArrayList<String> expected = catalogPage.sortingProductListAtoZ();
        catalogPage.setSortingOption(CatalogPage.SORT_OPTION_AZ_NAME);
        Assert.assertEquals(catalogPage.getProductList(), expected, "Sorting A to Z is incorrect");
    }
}
