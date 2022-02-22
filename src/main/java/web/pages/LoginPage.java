package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.base.BasePage;

public class LoginPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    private static final By USERNAME_TEXT_FIELD = By.id("user-name");
    private static final By PASSWORD_TEXT_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("input#login-button");
    public static final String USERNAME_TEXT_FIELD_PLACEHOLDER = "Username";
    public static final String ERROR_MESSAGE_LOCKED_USER = "Epic sadface: Sorry, this user has been locked out.";
    public static final String ERROR_MESSAGE_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_MESSAGE_PASSWORD_REQUIRED = "Epic sadface: Password is required";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = LOGIN_BUTTON;
    }

    public void login(String username, String password) {
        if (username != null) {
            driver.findElement(USERNAME_TEXT_FIELD).sendKeys(username);
        }
        if (password != null) {
            driver.findElement(PASSWORD_TEXT_FIELD).sendKeys(password);
        }
        pushLoginBtn();
    }

    public void pushLoginBtn() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getUsernamePlaceholder() {
        return driver.findElement(USERNAME_TEXT_FIELD).getAttribute("placeholder");
    }

    public String getErrorMessage() {
        return driver.findElement(By.tagName("h3")).getText();
    }
}
