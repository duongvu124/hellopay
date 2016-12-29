package com.hellopay.test;

import com.hellopay.page.ForgottenPasswordPage;
import com.hellopay.page.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.Config;
import util.ExcelDataReader;

import static util.Config.email;
import java.awt.Desktop;
import java.io.File;


/**
 * Created by vuthaiduong on 12/28/16.
 */
public class RegisterAndRestorePassTest {

    public static WebDriver driver;

    @BeforeSuite
    public void initTest() {
        try {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } catch (Exception ex) {
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }


    @DataProvider(name = "USERINFO")
    public Object[][] createData() throws Exception {
        Object[][] data = ExcelDataReader.getDataFromExl("data/data.xlsx", "UserInfo");
        Object[][] userData = new Object[data.length][1];
        for (int i = 0; i < data.length; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setCountry(String.valueOf(data[i][0]));
            userInfo.setName(String.valueOf(data[i][1]));
            userInfo.setPrefix(String.valueOf(data[i][2]));
            userInfo.setPhoneNumber(String.valueOf(data[i][3]));
            userInfo.setEmail(String.valueOf(data[i][4]));
            userInfo.setPassword(String.valueOf(data[i][5]));
            userInfo.setInCase(String.valueOf(data[i][6]));
            userData[i][0] = userInfo;
        }

        return userData;
    }

    @Test(dataProvider = "USERINFO")
    public void register(UserInfo userInfo) throws Exception {

        driver.get(Config.registerUrl);

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.selectCountry(userInfo.getCountry());
        registerPage.inputFullName(userInfo.getName());
        registerPage.inputPhoneNumber(userInfo.getPrefix(), userInfo.getPhoneNumber());
        registerPage.inputEmail(userInfo.getEmail());
        registerPage.inputPassword(userInfo.getPassword());

        registerPage.submitRegister();

        switch (Integer.parseInt(userInfo.getInCase())) {
            case 1:
                Assert.assertTrue(registerPage.isNoNameWarning());
                break;
            case 2:
                Assert.assertTrue(registerPage.isNoPhoneNumberWarning());
                break;
            case 3:
                Assert.assertTrue(registerPage.isNoPhoneNumberWarning());
                break;
            case 4:
                Assert.assertTrue(registerPage.isNoEmailWarning());
                break;
            case 5:
                Assert.assertTrue(registerPage.isNoPasswordWarning());
                break;
            case 6:
                Assert.assertTrue(registerPage.isNoPasswordWarning());
                break;
            case 7:
                Assert.assertTrue(registerPage.isExistPhoneNumberWarning());
                break;
            case 8:
                Assert.assertTrue(registerPage.isExistEmailWarning());
                break;
            case 9:
                Assert.assertTrue(registerPage.isExistPhoneNumberAndEmailWarning());
                break;
            case 10:
                Assert.assertTrue(registerPage.isTbVerificationCode());
                break;
        }
    }

    @Test
    public void resetPassword() throws Exception {
        driver.get(Config.resetPasswordUrl);
        ForgottenPasswordPage forgottenPasswordPage = PageFactory.initElements(driver, ForgottenPasswordPage.class);
        forgottenPasswordPage.inputForgottenEmail(email);
        forgottenPasswordPage.resetPassword();
        Assert.assertTrue(forgottenPasswordPage.isTxtInformMessage());
    }

    @AfterSuite
    public static void tearDownClass() throws Exception {
        if (Desktop.isDesktopSupported()) {
            File reportFile = new File("target/surefire-reports/index.html");
            Desktop.getDesktop().browse(reportFile.toURI());
//            }
        }
    }
}
