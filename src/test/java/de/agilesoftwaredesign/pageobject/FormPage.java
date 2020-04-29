package de.agilesoftwaredesign.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPage extends PageObject {
    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void enterForm(String name, String phone) {
        sleep(3000);
        driver.findElement(By.className("nameentry")).sendKeys(name);
        driver.findElement(By.className("phoneentry")).sendKeys(phone);
    }
}