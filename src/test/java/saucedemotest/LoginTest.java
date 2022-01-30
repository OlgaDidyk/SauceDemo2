package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.LoginPage;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void openLoginPage() {
        Assert.assertTrue(
                loginPage
                        .open()
                        .isPageLoaded()
                , "Login page is not loaded"
        );
    }

    @Test (priority = 5)
    public void validCredentialLogin() {
        loginPage
                .login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Test (priority = 1)
    public void userNamePlaceholderTest() {
        Assert.assertEquals(loginPage.getUsernamePlaceholder(),
                LoginPage.USERNAME_TEXT_FIELD_PLACEHOLDER,
                "Username placeholder is not valid");
    }

    @Test (priority = 4)
    public void lockedUserTest() {
        loginPage
                .login(LOCKED_USERNAME, PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(), LoginPage.ERROR_MESSAGE_LOCKED_USER,
                "Error message is incorrect");
    }

    @Test (priority = 2)
    public void userNameFieldRequiredTest() {
        loginPage
                .login(null, PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(),
                LoginPage.ERROR_MESSAGE_USERNAME_REQUIRED,
                "Username required text is incorrect");
    }

    @Test (priority = 3)
    public void passwordFieldRequiredTest() {
        loginPage
                .login(USERNAME, null);
        Assert.assertEquals(loginPage.getErrorMessage(),
                LoginPage.ERROR_MESSAGE_PASSWORD_REQUIRED,
                "Password required text is incorrect");
    }

}
