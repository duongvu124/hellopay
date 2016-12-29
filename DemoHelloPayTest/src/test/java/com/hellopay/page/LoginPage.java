package com.hellopay.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by vuthaiduong on 12/30/16.
 */
public class LoginPage extends PageMaster{


    @FindBy(how = How.ID, using = "email")
    private WebElement tbLoginAccount;

    @FindBy(how = How.ID, using = "password")
    private WebElement tbPasswordLogin;

    @FindBy(how = How.ID, using = "login")
    private WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//*[text()='Forgot your password?']")
    private WebElement txForgotPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
