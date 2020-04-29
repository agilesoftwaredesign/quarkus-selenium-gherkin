package de.agilesoftwaredesign;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.agilesoftwaredesign.pageobject.HomepagePage;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FormSeleniumTest {
    private static final Logger LOG = Logger.getLogger(FormSeleniumTest.class.getName());
    private static WebDriver driver;

    @ConfigProperty(name = "usewebdriver", defaultValue = "chrome")
    String usewebdriver;

    @ConfigProperty(name = "webdriverRemoteUrl", defaultValue = "http://localhost:4444/wd/hub")
    String webdriverRemoteUrl;

    @ConfigProperty(name = "homepageBaseUrl", defaultValue = "http://localhost:8080/")
    String homepageBaseUrl;

    protected HomepagePage homepagePage;

    @BeforeEach
    public void ensureWebDriver() {
        if (driver != null) {
            return;
        }

        if ("chrome".equalsIgnoreCase(usewebdriver)) {
            LOG.info("creating ChromeDriver");
            LOG.info("--> " + System.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();

        } else if (usewebdriver.startsWith("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            String[] split = usewebdriver.split(";");
            if (split.length == 2) {
                capabilities.setBrowserName(split[1]);
            }

            final URL url = toUrl(usewebdriver);
            LOG.info("creating RemoteWebDriver for capabilities: " + capabilities.toString());
            driver = new RemoteWebDriver(url, capabilities);

        } else {
            LOG.severe("no support: " + usewebdriver);
            throw new RuntimeException("no support for " + usewebdriver);
        }

        homepagePage = new HomepagePage(driver, toUrl(homepageBaseUrl));
    }

    private static URL toUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            assert false;
            return null;
        }
    }

    @AfterEach
    public void afterAll() {
        LOG.info("closing WebDriver session");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkHomepagePageTitle() throws Throwable {
        String title = homepagePage.getTitle();
        Assertions.assertEquals("Quarkus Selenium Gherkin", title);
    }

    @Test
    public void verifyFormEntryCalculationWorksProper() {
        String result = homepagePage.enterValueIntoForm("support phone", "+49 89 55555");
        Assertions.assertEquals("ccc", result);
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        driver.get("http://www.google.com/");
        Thread.sleep(5000); // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000); // Let the user actually see something!
        driver.quit();
    }
}
