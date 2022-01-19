package saucedemotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.CatalogPage;
import web.pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class ProductTest extends BaseTest {

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
        if (!loginPage.open().toString().equals(LoginPage.BASE_URL)) {
            login();
        } else System.out.println(loginPage.open().toString());
        cartPage.open();
        cartPage.isPageLoaded();
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
    public void logout() {
        catalogPage.openMenuAndValidation();
        catalogPage.pushMenuOption(CatalogPage.LOGOUT_MENU_OPTION);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not open");
    }

    @Test
    public void buttonTitleChangedToRemove() {
        catalogPage.addProductToCart(TEST_PRODUCT2_TITLE);
        Assert.assertEquals(catalogPage.checkButtonTitle(TEST_PRODUCT2_TITLE),
                CatalogPage.REMOVE_BUTTON,
                "Button title is not changed to Remove");
    }

    @Test
    public void buttonTitleChangedToAddToCart() {
        buttonTitleChangedToRemove();
        catalogPage.addProductToCart(TEST_PRODUCT2_TITLE);
        Assert.assertEquals(catalogPage.checkButtonTitle(TEST_PRODUCT2_TITLE),
                CatalogPage.ADD_TO_CART_BUTTON, "Button title is not changed to Add to cart");
    }

}
