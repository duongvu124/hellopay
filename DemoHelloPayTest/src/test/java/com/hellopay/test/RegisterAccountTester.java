package com.hellopay.test;

//import com.oracle.tools.packager.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.hellopay.page.RegisterPage;
import util.Config;

/**
 * Created by vuthaiduong on 12/28/16.
 */
public class RegisterAccountTester {

    public WebDriver driver;

    public void runTest(WebDriver driver) throws Exception{
        this.driver = driver;
        register(Config.country, Config.name, Config.prefix, Config.phoneNumber, Config.email, Config.password);
    }


    public void register(String country, String name, String prefix, String phoneNumber, String email, String password) throws Exception{

        driver.get(Config.registerUrl);

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
//        Log.info(" Begin To Register Account ");
        registerPage.selectCountry(country);
        registerPage.inputFullName(name);
        registerPage.inputPhoneNumber(prefix, phoneNumber);
        registerPage.inputEmail(email);
        registerPage.inputPassword(password);
        registerPage.submitRegister();
        registerPage.inputVerificationCode(Config.verificationCode);

        String oldTab = driver.getWindowHandle();

        VerifyAccount verifyAccount = new VerifyAccount();
        verifyAccount.runTest(driver, Config.email, Config.password);

        driver.switchTo().window(oldTab);


    }


}
