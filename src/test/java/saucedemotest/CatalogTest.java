package saucedemotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.elements.BurgerMenuElement;
import web.pages.CatalogPage;

import java.util.ArrayList;
import java.util.List;

public class CatalogTest extends BaseTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";
    public static final String TEST_PRODUCT2_TITLE = "Sauce Labs Backpack";

    @BeforeMethod
    private void login() {
        Assert.assertTrue(
                loginPage
                        .open()
                        .isPageLoaded()
                , "Login page is not loaded"
        );
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

    @Test(invocationCount = 5)
    public void addProductToCartTest() {
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);
        cartPage
                .open()
                .isPageLoaded();
        List<String> listOfExpectedTitles = new ArrayList<>();
        listOfExpectedTitles.add(TEST_PRODUCT_TITLE);
        Assert.assertTrue(cartPage.validateAddedProducts(listOfExpectedTitles), "Products in the cart is not match");
    }

    @Test
    public void logoutTest() {
        Assert.assertTrue(catalogPage.burgerMenu.open(), "Menu is not opened");
        catalogPage.burgerMenu.pushMenuOption(BurgerMenuElement.LOGOUT_BUTTON_NAME);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not opened");
    }

    @Test
    public void buttonTitleChangedToRemoveTest() {
        Assert.assertEquals(
                catalogPage
                        .addProductToCart(TEST_PRODUCT2_TITLE)
                        .checkButtonTitle(TEST_PRODUCT2_TITLE)
                , CatalogPage.REMOVE_BUTTON
                , "Button title is not changed to Remove");
    }

    @Test
    public void buttonTitleChangedToAddToCartTest() {
        buttonTitleChangedToRemoveTest();
        Assert.assertEquals(
                catalogPage
                        .addProductToCart(TEST_PRODUCT2_TITLE)
                        .checkButtonTitle(TEST_PRODUCT2_TITLE)
                , CatalogPage.ADD_TO_CART_BUTTON
                , "Button title is not changed to Add to cart"
        );
    }

    @Test
    public void emptyCartHasNotCounterTest() throws InterruptedException {
        Assert.assertFalse(catalogPage.cartIconElement.emptyCartHasNotCounter(), "Cart counter is shown for empty cart");
    }

    @Test
    public void badgeIsNotDisplayed() throws InterruptedException {
        Assert.assertFalse(catalogPage.isBadgeDisplayed(5));
    }

    @Test
    public void sortingZtoATest() {
        ArrayList<String> expected = catalogPage.sortingProductListZtoA();
        Assert.assertEquals(
                catalogPage
                        .setSortingOption(CatalogPage.SORT_OPTION_ZA_NAME)
                        .getProductList()
                , expected
                , "Sorting Z to A is incorrect"
        );
    }

    @Test
    public void sortingAtoZTest() {
        ArrayList<String> expected = catalogPage.sortingProductListAtoZ();
        Assert.assertEquals(
                catalogPage
                        .setSortingOption(CatalogPage.SORT_OPTION_AZ_NAME)
                        .getProductList()
                , expected
                , "Sorting A to Z is incorrect"
        );
    }
}
