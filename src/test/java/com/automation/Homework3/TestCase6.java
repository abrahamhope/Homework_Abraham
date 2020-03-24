package com.automation.Homework3;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase6 {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();

    }

    @Test
    public void test6() {
        //Step 1. Go to "https://www.tempmailaddress.com/"
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        //Step 2. Copy and save email as a string.
        String emailAddress = driver.findElement(By.id("email")).getText();

        //Step 3. Then go to “https://practice- cybertekschool.herokuapp.com”
        driver.navigate().to("http://practice.cybertekschool.com/");
        BrowserUtils.wait(5);

        //Step 4. And click on “Sign Up For Mailing List".
        WebElement signUpForMailingList = driver.findElement(By.linkText("Sign Up For Mailing List"));
        signUpForMailingList.click();
        BrowserUtils.wait(2);

        //Step 5. Enter any valid name.
        WebElement fullName = driver.findElement(By.name("full_name"));
        fullName.sendKeys("Mike Johnson");

        //Step 6. Enter email from the Step 2.
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys(emailAddress);

        //Step 7. Click Sign Up
        WebElement signupBtn = driver.findElement(By.name("wooden_spoon"));
        signupBtn.click();

        //Step 8. Verify that following message is displayed: “Thank you for signing up.
        // Click the button below to return to the home page.”
        WebElement signupMessage = driver.findElement(By.name("signup_message"));
        Assert.assertTrue(signupMessage.isDisplayed());

        //Step 9. Navigate back to the “https:// www.tempmailaddress.com/”
        driver.navigate().back();
        driver.navigate().back();
        BrowserUtils.wait(5);
        driver.navigate().back();
        BrowserUtils.wait(5);

        //Step 10. Verify that you’ve received an email from
        //“do-not-reply@practice.cybertekschool.com”
        WebElement emailAd = driver.findElement(By.xpath("//table/tbody/tr[1]/td"));
        System.out.println("displayed :" + emailAd.isDisplayed());
        System.out.println("emailAd :" + emailAd.getText());
        String displayedEmail = emailAd.getText().trim();
        Assert.assertEquals(displayedEmail, "do-not-reply@practice.cybertekschool.com");

        //Step 11. Click on that email to open it.
        emailAd.click();
        BrowserUtils.wait(3);

        //Step 12. Verify that email is from: “do-not- reply@practice.cybertekschool.com”
        WebElement from= driver.findElement(By.id("odesilatel"));
        String expectedFrom= "do-not-reply@practice.cybertekschool.com";
        String actualFrom = from.getText();
        Assert.assertEquals(actualFrom,expectedFrom);

        //Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
        WebElement subject = driver.findElement(By.id("predmet"));
        String expectedSubject= "Thanks for subscribing to practice.cybertekschool.com!";
        String actualSubject=subject.getText();
        Assert.assertEquals(actualSubject,expectedSubject);



    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
