package saucedemotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import web.pages.*;


public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected CatalogPage catalogPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CheckoutCompletePage checkoutCompletePage;

    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String LOCKED_USERNAME = "locked_out_user";
    public static final String FIRST_NAME_VALUE = "FirstName";
    public static final String LAST_NAME_VALUE = "LastName";
    public static final String POSTAL_CODE_VALUE = "1111";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }
}