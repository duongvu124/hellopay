package util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class CommonFlow {

    public WebDriver driver;

    public void checkVerificationCodeInEmail(WebDriver driver){
        this.driver = driver;

        String selectLinkOpeninNewTab = Keys.chord(Keys.COMMAND,"t");
        driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);
        driver.get(Config.gmailUrl);


    }

}
