package utils;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        System.out.println((String.format("======================================== STARTING TEST %s" +
                " ========================================", iTestResult.getName())));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FINISHED TEST %s Duration: %ss" +
                        " ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FAILED TEST %s Duration: %ss" +
                        " ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        takeScreenshotToFile(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(String.format("======================================== SKIPPING TEST %s" +
                " ========================================", iTestResult.getName()));
        takeScreenshotToFile(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }


    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    private File takeScreenshotToFile(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute("driver");
            if (driver != null) {
                File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                return file;
            } else {
                return null;
            }
        } catch (NoSuchSessionException | IllegalStateException ex) {
            return null;
        }

    }

}
