package de.agilesoftwaredesign.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject {
    protected WebDriver driver;
    public static final long SHORT_WAIT_PERIOD_FOR_OBSERVER_CONVENICENCE = 500;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
    
    public void waitForObserverConvience() {
        sleep(SHORT_WAIT_PERIOD_FOR_OBSERVER_CONVENICENCE);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see https://www.seleniumeasy.com/selenium-tutorials/accessing-shadow-dom-elements-with-webdriver
     * @param shadowDomHost
     * @return
     */
    public WebElement accessInterierOfShadowDom(WebElement shadowDomHost) {
        WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                shadowDomHost);
        return ele;
    }
}
