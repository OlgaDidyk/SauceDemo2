package web.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage extends AbstractPage {

    protected String baseUrl;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public BasePage open() {
        driver.get(baseUrl);
        return this;
    }

}
