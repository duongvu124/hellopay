package util;

//import com.oracle.tools.packager.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by vuthaiduong on 12/28/16.
 */
public class UIComponent {
    public static void printPageInfo(WebDriver driver){
//        Log.debug("Load page url: " + driver.getCurrentUrl() + " - Done");
//        Log.debug("Load page title: " + driver.getTitle() + " - Done");
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
//            Log.info("Fill : " + inputText + " in element : " + inputElement.getAttribute("name"));
    }

    public void checkVerificationCodeinMail(){

    }

}
