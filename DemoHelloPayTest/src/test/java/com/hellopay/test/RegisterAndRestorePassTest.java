package com.hellopay.test;

import com.hellopay.page.ForgottenPasswordPage;
import com.hellopay.page.LoginPage;
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
import util.FetchingMail;

import static util.Config.email;
import java.awt.Desktop;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                // empty fullName
                Assert.assertTrue(registerPage.isNoNameWarning());
                break;
            case 2:
                // empty prefix
                Assert.assertTrue(registerPage.isNoPhoneNumberWarning());
                break;
            case 3:
                // empty phone number
                Assert.assertTrue(registerPage.isNoPhoneNumberWarning());
                break;
            case 4:
                // empty email
                Assert.assertTrue(registerPage.isNoEmailWarning());
                break;
            case 5:
                // empty password
                Assert.assertTrue(registerPage.isNoPasswordWarning());
                break;
            case 6:
                // wrong password
                Assert.assertTrue(registerPage.isNoPasswordWarning());
                break;
            case 7:
                // duplicate phone number
                Assert.assertTrue(registerPage.isExistPhoneNumberWarning());
                break;
            case 8:
                // duplicate email
                Assert.assertTrue(registerPage.isExistEmailWarning());
                break;
            case 9:
                // duplicate phone number + duplicate email
                Assert.assertTrue(registerPage.isExistPhoneNumberAndEmailWarning());
                break;
            case 10:
                // successful case
                if(registerPage.isTbVerificationCode()){
                    String url = null;
                    FetchingMail fetchingMail = new FetchingMail();
                    List<StringBuilder> list = fetchingMail.fetchMailBody(Config.host, Config.mailStoreType,
                            userInfo.getEmail(), userInfo.getPassword());
                    while(list.size()==0){
                        wait(10000);
                        list = fetchingMail.fetchMailBody(Config.host, Config.mailStoreType,
                                userInfo.getEmail(), userInfo.getPassword());
                    }
                    StringBuilder body = list.get(0);
                    String regex = ".*(http\\S*)(<|>)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(body.toString());
                if(matcher.find()){
                    url = matcher.group(1);
                }
                    driver.get(url);
                    LoginPage loginPage = new LoginPage(driver);
                    loginPage.inputEmail(userInfo.getEmail());
                    loginPage.inputPassword(userInfo.getPassword());
                    loginPage.submit();
                    Assert.assertTrue(registerPage.isTxtResendSMS());
                    System.out.println("Verify Mail Is Successful");
                }
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
        }
    }
}
