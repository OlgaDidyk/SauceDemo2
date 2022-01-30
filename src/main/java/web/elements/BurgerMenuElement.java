package web.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.AbstractElement;

public class BurgerMenuElement extends AbstractElement {

    public static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    public static final By CLOSE_MENU_ICON = By.id("react-burger-cross-btn");
    public static final String LOGOUT_BUTTON_NAME = "Logout";
    public static final String MENU_BUTTON_PATTERN = "//a[contains(text(),'%s')]";

    public BurgerMenuElement(WebDriver driver) {
        super(driver);
        super.baseElementLocator = BURGER_MENU_BUTTON;
    }

    public boolean open() {
        driver.findElement(BURGER_MENU_BUTTON).click();
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(CLOSE_MENU_ICON));
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

}
