package com.hellopay.test;

//import com.oracle.tools.packager.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by vuthaiduong on 12/28/16.
 */
public class RegisterAndRestorePassSuite {

    public static WebDriver driver;

    @BeforeSuite
    public void initTest(){
        try {
//            Log.info(" BEGIN TEST SUITE");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } catch (Exception ex){
//            Log.info("Have error when init test flow !" + ex.getMessage());
        }
    }
    public static WebDriver getDriver(){
        return driver;
    }

    @Test
    public void runRegisterAccountTest() throws Exception{
        RegisterAccountTester register = new RegisterAccountTester();
        register.runTest(driver);
    }

    @Test
    public void runForgotPassword(){
        ForgotPasswordTester forgotPasswordTester = new ForgotPasswordTester();
        forgotPasswordTester.runTest(driver);
    }


    @AfterSuite
    public void quitTest(){

//        Log.info(" FINISH TEST SUITE");
    }

}
