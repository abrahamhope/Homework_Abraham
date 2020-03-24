package com.automation.Homework3;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase7 {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createWebDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
    }

    @Test
    public void fileUpload(){
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(2);
        WebElement choseFile=driver.findElement(By.id("file-upload"));
        choseFile.sendKeys("/Users/isikdurmus/Desktop/File upload.txt");
        BrowserUtils.wait(2);
        driver.findElement(By.id("file-submit")).click();
        WebElement fileUploadedMessage=driver.findElement(By.xpath("//*[.='File Uploaded!']"));
        String expected= "File Uploaded!";
        String actual=fileUploadedMessage.getText();
        Assert.assertEquals(actual,expected);

        WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));

        Assert.assertTrue(uploadedFileName.isDisplayed());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
