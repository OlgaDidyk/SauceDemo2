package web.base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbstractPage extends AbstractElement {

    public AbstractPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException timeoutException) {
            return false;
        }
        return true;
    }
}
