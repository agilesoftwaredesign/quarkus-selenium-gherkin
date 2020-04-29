package de.agilesoftwaredesign.pageobject;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomepagePage {
    protected WebDriver driver;

    public HomepagePage(WebDriver driver, URL homepageBaseUrl) {
        this.driver = driver;
        driver.get(homepageBaseUrl.toString());
    }

    protected FormPage openForms() {
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
