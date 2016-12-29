package com.hellopay.page;

//import com.oracle.tools.packager.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.UIComponent;

/**
 * Created by vuthaiduong on 12/28/16.
 */
public class RegisterPage extends PageMaster {

    // Register page

    @FindBy(how = How.CLASS_NAME, using = "fakeSelect")
    private WebElement dropboxCountry;

    @FindBy(how = How.ID, using = "fullName")
    private WebElement tbFullName;

    @FindBy(how = How.ID, using = "mobilePrefix")
    private WebElement tbMobilePrefix;

    @FindBy(how = How.ID, using = "mobile")
    private WebElement tbMobile;

    @FindBy(how = How.ID, using = "email")
    private WebElement tbEmail;

    @FindBy(how = How.ID, using = "password")
    private WebElement tbPassword;

    @FindBy(how = How.ID, using = "register")
    private WebElement btnRegister;

    @FindBy(how = How.XPATH, using = "//*[text()='Login Now']")
    private WebElement txLoginNow;

    // Login page

    @FindBy(how = How.ID, using = "email")
    private WebElement tbLoginAccount;

    @FindBy(how = How.ID, using = "password")
    private WebElement tbPasswordLogin;

    @FindBy(how = How.ID, using = "login")
    private WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//*[text()='Forgot your password?']")
    private WebElement txForgotPassword;

    // Forgot password page

    @FindBy(how = How.ID, using = "uid")
    private WebElement tbRecoveryEmail;

    @FindBy(how = How.ID, using = "login")
    private WebElement btnResetPassword;

    // verify OTP

    @FindBy(how = How.ID, using = "emailVerificationCode")
    private WebElement tbVerificationCode;

    @FindBy(how = How.ID, using = "emailVerificationCode")
    private WebElement btnConfirmVerificationCode;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 15);

    public void selectCountry(String country) throws Exception{
//        wait.until(ExpectedConditions.elementToBeClickable(dropboxCountry));
//        UIComponent.selectDropDownBoxItemByVisibleText(dropboxCountry, country);
//        Select countrySelect = new Select(dropboxCountry);
//        countrySelect.selectByVisibleText(country);
        dropboxCountry.click();
        String countryXpath = "//li/span[text()='"+country+"']/..";
//        Thread.sleep(2000);
        WebElement countryEl = driver.findElement(By.xpath(countryXpath));
        wait.until(ExpectedConditions.elementToBeClickable(countryEl));
        clickByJS(countryEl);
//        Log.info( country + " is selected");
    }

    private void clickByJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void inputFullName(String name){
        wait.until(ExpectedConditions.elementToBeClickable(tbFullName));
        UIComponent.fillText(tbFullName, name);
//        Log.info(" Full Name Is:  " + name);
    }

    public void inputPhoneNumber(String prefix, String phoneNumber){
        UIComponent.fillText(tbMobilePrefix, prefix);
        UIComponent.fillText(tbMobile, phoneNumber);
//        Log.info(" Phone Number Is:  " + prefix + " " + phoneNumber);
    }

    public void inputEmail(String email){
        UIComponent.fillText(tbEmail, email);
//        Log.info(" Email Is:  " + email);
    }

    public void inputPassword(String password){
        UIComponent.fillText(tbPassword, password);
//        Log.info(" Password Is:  " + password);
    }

    public void submitRegister(){
        btnRegister.click();
//        Log.info(" Click on OpenAccount Now button");
    }

    public void inputVerificationCode(String code){
        wait.until(ExpectedConditions.elementToBeClickable(tbVerificationCode));
        UIComponent.fillText(tbVerificationCode, code);
//        Log.info(" Verification Code Is:  " + code);
        btnConfirmVerificationCode.click();
    }

    public void inputForgottenEmail(String email){
        UIComponent.fillText(tbRecoveryEmail, email);
//        Log.info(" Recovery Email Is :  " + email);
    }

    public void resetPassword(){
//        Log.info(" Begin to reset password");
        btnResetPassword.click();
    }
}
