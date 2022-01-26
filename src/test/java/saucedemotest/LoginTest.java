package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.LoginPage;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void openLoginPage() {
        loginPage.open();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
    }

    @Test
    public void validCredentialLogin() {
        openLoginPage();
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Test
    public void userNamePlaceholderTest() {
        openLoginPage();
        Assert.assertEquals(loginPage.getUsernamePlaceholder(),
                LoginPage.USERNAME_TEXT_FIELD_PLACEHOLDER,
                "Username placeholder is not valid");
    }

    @Test
    public void lockedUserTest() {
        openLoginPage();
        loginPage.login(LOCKED_USERNAME, PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(), LoginPage.ERROR_MESSAGE_LOCKED_USER,
                "Error message is incorrect");
    }

    @Test
    public void userNameFieldRequired() {
        openLoginPage();
        loginPage.login(null, PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(),
                LoginPage.ERROR_MESSAGE_USERNAME_REQUIRED,
                "Username required text is incorrect");
    }

    @Test
    public void passwordFieldRequired() {
        openLoginPage();
        loginPage.login(USERNAME, null);
        Assert.assertEquals(loginPage.getErrorMessage(),
                LoginPage.ERROR_MESSAGE_PASSWORD_REQUIRED,
                "Password required text is incorrect");
    }

}
