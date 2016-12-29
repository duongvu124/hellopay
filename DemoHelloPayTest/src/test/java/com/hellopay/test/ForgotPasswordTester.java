package com.hellopay.test;

//import com.oracle.tools.packager.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.hellopay.page.RegisterPage;
import util.Config;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class ForgotPasswordTester {
    public WebDriver driver;
    public void runTest(WebDriver driver) {
        this.driver = driver;
        resetPassword(Config.email);
    }

    public void resetPassword(String email){

//        Log.info(" Begin to reset password ");
        driver.get(Config.resetPasswordUrl);
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.inputForgottenEmail(email);
        registerPage.resetPassword();
    }

}
