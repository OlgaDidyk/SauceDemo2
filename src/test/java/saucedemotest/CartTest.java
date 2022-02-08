package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.CheckoutPage;

public class CartTest extends BaseTest {

    @BeforeMethod
    private void login() {
        Assert.assertTrue(
                loginPage
                        .open()
                        .isPageLoaded()
                , "Login page is not loaded"
        );

        loginPage
                .login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Test
    public void purchaseTest() {
        catalogPage
                .addProductToCart(CatalogTest.TEST_PRODUCT_TITLE);
        cartPage.open()
                .isPageLoaded();
        cartPage
                .pushButton()
                .isPageLoaded();
        checkoutPage
                .fillInField(CheckoutPage.FIRST_NAME_LOCATOR, FIRST_NAME_VALUE)
                .fillInField(CheckoutPage.LAST_NAME_LOCATOR, LAST_NAME_VALUE)
                .fillInField(CheckoutPage.POSTAL_CODE_LOCATOR, POSTAL_CODE_VALUE)
                .pushContinueButton();
        checkoutStepTwoPage.pushFinishButton();
        checkoutCompletePage.isPageLoaded();
        Assert.assertTrue(
                checkoutCompletePage
                        .pushBackHomeButton()
                        .isPageLoaded()
                , "Catalog page is not loaded"
        );
    }

    @Test
    public void menuIsOpenedTest() {
        loginPage
                .open();
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(
                cartPage
                        .open()
                        .isPageLoaded()
                , "Cart page is not loaded"
        );
        Assert.assertTrue(cartPage.burgerMenu.open(), "Menu is not opened");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
