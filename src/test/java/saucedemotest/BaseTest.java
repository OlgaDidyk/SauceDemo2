package saucedemotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.CapabilitiesGenerator;
import utils.PropertiesUtils;
import utils.TestListener;
import web.pages.*;

@Listeners({TestListener.class})
public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected CatalogPage catalogPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CheckoutCompletePage checkoutCompletePage;

    //public static String USERNAME = "standard_user";
    //public static String PASSWORD = "secret_sauce";
    public static String USERNAME = PropertiesUtils.getEnv("valid_login");
    public static String PASSWORD = PropertiesUtils.getEnv("valid_password");
    public static final String LOCKED_USERNAME = "locked_out_user";
    public static final String FIRST_NAME_VALUE = "FirstName";
    public static final String LAST_NAME_VALUE = "LastName";
    public static final String POSTAL_CODE_VALUE = "1111";

/*    public void initParams() { Этот метода уже не нужен потому что креды передаем из properties  файла
        USERNAME = System.getProperty("username");
        PASSWORD = System.getProperty("password");
    }*/

    @BeforeClass
    public void setUp(ITestContext iTestContext) {
        //initParams();
/* Этот кусок вынесли в CapabilitiesGenerator класс
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");*/

        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());  /*если нужен другой драйвер то меняем
         этот метод на другой который определяет ваш драйвер по параметру и уже выбирает нужный CapabilitiesGenerator и
         нужный объект драйвера.*/
        setContextAttribute(iTestContext, "driver", driver);
        driver.manage().window().maximize();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    private void setContextAttribute(ITestContext iTestContext, String attributeKey, Object attributeValue) {
        iTestContext.setAttribute(attributeKey, attributeValue);

    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }
}