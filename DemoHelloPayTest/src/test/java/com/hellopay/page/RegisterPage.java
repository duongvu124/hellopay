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

    // Warning message
    @FindBy(how = How.XPATH, using = "//*[text()='Name is required.']")
    private WebElement noNameWarning;

    @FindBy(how = How.XPATH, using = "//*[text()='Sorry, but you have to provide a phone number to proceed. Please enter a valid phone number and try again.']")
    private WebElement noPhoneNumberWarning;

    @FindBy(how = How.XPATH, using = "//*[text()='Sorry, this phone number is already registered. Please check your number or ']")
    private WebElement existPhoneNumberWarning;

    @FindBy(how = How.XPATH, using = "//*[text()='The mobile number or email is already registered. If this is yours, please ']")
    private WebElement existPhoneNumberAndEmailWarning;

    @FindBy(how = How.XPATH, using = "//*[text()='Please double check your email address.']")
    private WebElement noEmailWarning;

    @FindBy(how = How.XPATH, using = "//*[text()='Sorry, this email address is already registered. Please check your address or ']")
    private WebElement existEmailWarning;

    @FindBy(how = How.XPATH, using = "//*[text()='Please choose a password of at least 8 characters, containing capital letter and number.']")
    private WebElement noPasswordWarning;

    // verify mobile
    @FindBy(how = How.XPATH, using = "//*[text()='Resend SMS']")
    private WebElement txtResendSMS;

    @FindBy(how = How.ID, using = "emailVerificationCode")
    private WebElement tbVerificationCode;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 15);

    public void selectCountry(String country) throws Exception{

        dropboxCountry.click();
        String countryXpath = "//li/span[text()='"+country+"']/..";
        WebElement countryEl = driver.findElement(By.xpath(countryXpath));
        wait.until(ExpectedConditions.elementToBeClickable(countryEl));
        UIComponent.clickByJS(driver, countryEl);
    }

    public void inputFullName(String name){
        wait.until(ExpectedConditions.elementToBeClickable(tbFullName));
        UIComponent.fillText(tbFullName, name);
    }

    public void inputPhoneNumber(String prefix, String phoneNumber){
        UIComponent.fillText(tbMobilePrefix, prefix);
        UIComponent.fillText(tbMobile, phoneNumber);
    }

    public void inputEmail(String email){
        UIComponent.fillText(tbEmail, email);
    }

    public void inputPassword(String password){
        UIComponent.fillText(tbPassword, password);
    }

    public void submitRegister(){
        btnRegister.click();
    }

    // Assert Warning
    public boolean isNoNameWarning(){
        return noNameWarning.isDisplayed();
    }
    public boolean isNoPhoneNumberWarning(){
        return noPhoneNumberWarning.isDisplayed();
    }
    public boolean isExistPhoneNumberWarning(){
        return existPhoneNumberWarning.isDisplayed();
    }
    public boolean isExistPhoneNumberAndEmailWarning(){
        return existPhoneNumberAndEmailWarning.isDisplayed();
    }
    public boolean isNoEmailWarning(){
        return noEmailWarning.isDisplayed();
    }
    public boolean isExistEmailWarning(){
        return existEmailWarning.isDisplayed();
    }
    public boolean isNoPasswordWarning(){
        return noPasswordWarning.isDisplayed();
    }

    public boolean isTbVerificationCode() throws InterruptedException {
        wait(4000);
        return tbVerificationCode.isDisplayed();
    }
    public boolean isTxtResendSMS() throws InterruptedException {
        wait(4000);
        return txtResendSMS.isDisplayed();
    }

}
