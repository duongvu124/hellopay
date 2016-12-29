package com.hellopay.page;
import org.openqa.selenium.WebDriver;
import util.UIComponent;

/**
 * Created by vuthaiduong on 12/28/16.
 */
public abstract class PageMaster {
    public final WebDriver driver;

    public PageMaster(WebDriver driver) {
        this.driver = driver;
        UIComponent.printPageInfo(driver);
    }
}

