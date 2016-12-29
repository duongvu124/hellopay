package com.hellopay.page;

//import com.oracle.tools.packager.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.UIComponent;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class GmailPage extends PageMaster {

    @FindBy(how = How.ID, using = "Email")
    private WebElement tbEmail;

    @FindBy(how = How.ID, using = "next")
    private WebElement btnNext;

    @FindBy(how = How.ID, using = "Passwd")
    private WebElement tbPassword;

    @FindBy(how = How.ID, using = "signIn")
    private WebElement btnSignIn;

    @FindBy(how = How.XPATH, using = "//*[@class='WaidBe']")
    private WebElement gmail;

    @FindBy(how = How.ID, using = "close-button")
    private WebElement iconClose;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Inbox')]")
    private WebElement inboxFolder;

    public GmailPage(WebDriver driver){
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 20);

    public void inputEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(tbEmail));
        UIComponent.fillText(tbEmail, email);
//        Log.info(" Email Is:  " + email);
        btnNext.click();
//        Log.info(" Click on Next button");
    }

    public void inputPassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(tbPassword));
        UIComponent.fillText(tbPassword, password);
//        Log.info(" Password Is:  " + password);
        btnSignIn.click();
//        Log.info(" Click on SignIn button");
    }

    public void selectGmail(){
        wait.until(ExpectedConditions.elementToBeClickable(gmail));
        gmail.click();
//        Log.info(" Click on Gmail icon");
    }

    public void closeIntruction(){
        driver.switchTo().frame(":58.i");
        iconClose.click();
//        Log.info(" Click on x icon");
    }

    public void selectInboxFolder(){
        wait.until(ExpectedConditions.elementToBeClickable(inboxFolder));
        inboxFolder.click();
    }


}