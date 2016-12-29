package com.hellopay.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.hellopay.page.GmailPage;
import util.Config;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class VerifyAccount {

    public WebDriver driver;
    public void runTest(WebDriver driver, String email, String password) {
        this.driver = driver;
        openEmail(email, password);
//        clickVerificationLink();

    }

    public void openEmail(String email, String password){
        GmailPage gmailPage = PageFactory.initElements(driver, GmailPage.class);

        driver.findElement(By.cssSelector("Body")).sendKeys(Keys.COMMAND + "t");

        driver.get(Config.gmailUrl);

        gmailPage.inputEmail(email);
        gmailPage.inputPassword(password);
        gmailPage.selectGmail();
        try {
            gmailPage.closeIntruction();
        } catch (Exception ex){}
        gmailPage.selectInboxFolder();
    }

    public void clickVerificationLink(){

    }

}
