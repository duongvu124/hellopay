package com.hellopay.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.UIComponent;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class ForgottenPasswordPage  extends PageMaster{
    @FindBy(how = How.ID, using = "uid")
    private WebElement tbRecoveryEmail;

    @FindBy(how = How.ID, using = "login")
    private WebElement btnResetPassword;

    @FindBy(how = How.XPATH, using = "//*[text()='If your email address was registered, you should receive an email with instructions on how to reset your Password.']")
    private WebElement txtInformMessage;

    public ForgottenPasswordPage (WebDriver driver) {
        super(driver);
    }

    public void inputForgottenEmail(String email){
        UIComponent.fillText(tbRecoveryEmail, email);
    }

    public void resetPassword(){
        btnResetPassword.click();
    }

    public boolean isTxtInformMessage(){
        return txtInformMessage.isDisplayed();
    }
}
