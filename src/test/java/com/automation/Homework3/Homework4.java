package com.automation.Homework3;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Homework4 {
    private WebDriver driver;
    /*
    1. go to http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox
    2. Randomly select a checkbox. As soon as you check any day, print the name of
    the day and uncheck immediately.
    After you check and uncheck Friday for the third time, exit the program.
     */
    @Test
    public void days(){
    driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        WebElement mondayCheck = driver.findElement(By.id("gwt-debug-cwCheckBox-Monday-input"));
        mondayCheck.click();
        System.out.println(driver.findElement(By.id("gwt-debug-cwCheckBox-Monday-label")));
        driver.findElement(By.id("gwt-debug-cwCheckBox-Monday-input")).click();
    }




    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
