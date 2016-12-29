package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by vuthaiduong on 12/28/16.
 */
public class UIComponent {
    public static void printPageInfo(WebDriver driver){
    }

    public static boolean selectDropDownBoxItemByVisibleText(WebElement dropDownBox,
                                                             String visibleText) {
        try {
            Select dropDownSelecter = new Select(dropDownBox);
            dropDownSelecter.selectByVisibleText(visibleText);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public static void fillText(WebElement inputElement, String inputText ){
            inputElement.clear();
            inputElement.sendKeys(inputText);
    }

    public void checkVerificationCodeinMail(){

    }

    public static void clickByJS(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

}
