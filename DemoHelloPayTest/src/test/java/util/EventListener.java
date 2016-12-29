package util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.hellopay.test.RegisterAndRestorePassSuite;

/**
 * Created by vuthaiduong on 12/29/16.
 */
    public class EventListener extends TestListenerAdapter {
    WebDriver driver;
    private static String fileSeperator = System.getProperty("file.separator");

    @Override
    public void onTestFailure(ITestResult result) {

        driver = RegisterAndRestorePassSuite.getDriver();


        if (driver != null) {

        }

    }
}
