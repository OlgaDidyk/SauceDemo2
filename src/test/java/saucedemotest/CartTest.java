package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.CheckoutPage;

public class CartTest extends BaseTest {

    @BeforeMethod
    private void login() {
        loginPage.open();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Test
    public void purchaseTest() {
        catalogPage.addProductToCart(CatalogTest.TEST_PRODUCT_TITLE);
        cartPage.open();
        cartPage.isPageLoaded();
        cartPage.pushButton();
        checkoutPage.isPageLoaded();
        checkoutPage.fillInField(CheckoutPage.FIRST_NAME_LOCATOR, FIRST_NAME_VALUE);
        checkoutPage.fillInField(CheckoutPage.LAST_NAME_LOCATOR, LAST_NAME_VALUE);
        checkoutPage.fillInField(CheckoutPage.POSTAL_CODE_LOCATOR, POSTAL_CODE_VALUE);
        checkoutPage.pushContinueButton();
        checkoutStepTwoPage.pushFinishButton();
        checkoutCompletePage.isPageLoaded();
        checkoutCompletePage.pushBackHomeButton();
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
