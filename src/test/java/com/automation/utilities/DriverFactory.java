package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;


public class DriverFactory {

    /**
     * This method return webdriver object based on browser type
     * If you want to use chrome browser, just provide chrome as a parameter
     * @param browserName
     * @return webdriver object
     */
    public static WebDriver createWebDriver(String browserName){

        if(browserName.equalsIgnoreCase("chrome")){
            // to fix the time out error message
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
            return new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();
        }else{
            WebDriverManager.operadriver().setup();
            return new OperaDriver();
        }
    }
}
