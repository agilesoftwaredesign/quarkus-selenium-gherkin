package de.agilesoftwaredesign.pageobject;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomepagePage {
    protected WebDriver driver;

    public HomepagePage(WebDriver driver, URL homepageBaseUrl) {
        this.driver = driver;
        driver.get(homepageBaseUrl.toString());
    }

    protected FormPage openForms() {
        WebElement elm =
                driver.findElement(By.cssSelector(".content > nav:nth-child(2) > p:nth-child(4) > a:nth-child(1)"));
        elm.click();
        return new FormPage(driver);
    }

    protected ViewPage openView() {
        return new ViewPage(driver);
    }

    protected SepPage openSep() {
        return new SepPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String enterValueIntoForm(String name, String phone) {
        FormPage page = openForms();
        page.enterForm(name, phone);
        String main = getMainContent();
        return main;
    }

    private String getMainContent() {
        return driver.findElement(By.tagName("main")).getText();
    }
}
