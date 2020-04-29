package de.agilesoftwaredesign.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage extends PageObject {
    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void enterForm(String name, String phone) {
        waitForObserverConvience();
        
        WebElement shadowDomHost = accessInterierOfShadowDom(driver.findElement(By.tagName("forms-page")));
        shadowDomHost.findElement(By.cssSelector(".nameentry")).sendKeys(name);
        shadowDomHost.findElement(By.cssSelector(".phoneentry")).sendKeys(phone);
        shadowDomHost.findElement(By.tagName("button")).click();
        
        waitForObserverConvience();
    }
}