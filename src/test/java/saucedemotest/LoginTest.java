package saucedemotest;

import io.qameta.allure.Allure;
import io.qameta.allure.Flaky;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.AllureUtils;
import utils.TestListener;
import web.pages.LoginPage;


@Listeners({TestListener.class})
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


    @Test(priority = 5)
    @Step("пример с кейвордом {keyword}")
    @Flaky
    @Parameters({"keyword"})
    public void validCredentialLogin(@Optional String keyword) {
        loginPage
                .login(USERNAME, PASSWORD);
        String json = String.format("{\"user\": \"%s\", \"password\": \"%s\"}", USERNAME, PASSWORD);
        //AllureUtils.attachJson(json.getBytes(StandardCharsets.UTF_8));
        Allure.addAttachment("Credentials from Allure static", "text/json", json);//можно так добавлять аттачи, без создания утилиты AllureUtils
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Step("Фейловый тест")
    @Test(priority = 1)
    public void userNamePlaceholderTest() {
        Assert.assertEquals(loginPage.getUsernamePlaceholder(),
                LoginPage.USERNAME_TEXT_FIELD_PLACEHOLDER,
                "Username placeholder is not valid");
    }

    @Test(priority = 4)
    public void lockedUserTest() {
        loginPage
                .login(LOCKED_USERNAME, PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(), LoginPage.ERROR_MESSAGE_LOCKED_USER,
                "Error message is incorrect");
    }

    @Test(priority = 2)
    public void userNameFieldRequiredTest() {
        loginPage
                .login(null, PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(),
                LoginPage.ERROR_MESSAGE_USERNAME_REQUIRED,
                "Username required text is incorrect");
    }

    @Test(priority = 3)
    public void passwordFieldRequiredTest() {
        loginPage
                .login(USERNAME, null);
        Assert.assertEquals(loginPage.getErrorMessage(),
                LoginPage.ERROR_MESSAGE_PASSWORD_REQUIRED,
                "Password required text is incorrect");
    }

}
