package com.example.horus.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Component
public class SeleniumConfig {

    private WebDriver chromeDriver = null;

    public void closeChromeDriver() {
        chromeDriver.close();
        chromeDriver.quit();
    }

    public void setupChromeDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.gecko.driver", "/driver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--enable-javascript");
        options.addArguments("disable-gpu");
        options.addArguments("window-size=800,600");
        this.chromeDriver = new ChromeDriver(options);
    }

    public WebDriver getChromeDriver() {
        return this.chromeDriver;
    }


}
