package com.automation.Homework3;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationForm {
    private WebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com/registration_form";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
    }

    /*
    Step 1. Go to “https://practicecybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed: “The date of birth is not valid”
     */
    @Test
    public void test1(){
        WebElement birthday =driver.findElement(By.name("birthday"));
        birthday.sendKeys("wrong_dob");
        WebElement invalidBtdyMessage=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]"));
        Assert.assertTrue(invalidBtdyMessage.isDisplayed());
    }

    /*
    Step 1. Go to “https://practicecybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Verify that following options for programming
    languages are displayed: c++, java, JavaScript
     */
    @Test
    public void test2(){
        WebElement cPlusPlus=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[1]/label"));
        WebElement java = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[2]/label"));
        WebElement javaScript= driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[3]/label"));
        Assert.assertTrue(cPlusPlus.isDisplayed());
        Assert.assertTrue(java.isDisplayed());
        Assert.assertTrue(javaScript.isDisplayed());
        BrowserUtils.wait(2);
    }

    /*
    Step 1. Go to “https://practicecybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter only one alphabetic character into first name input box.
    Step 4. Verify that warning message is displayed:
    “first name must be more than 2 and less than 64
    characters long”
     */
    @Test
    public void test3(){
        WebElement firstname =driver.findElement(By.name("firstname"));
        firstname.sendKeys("a");
        WebElement invalidFNameMessage=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[2]"));
        Assert.assertTrue(invalidFNameMessage.isDisplayed());
    }

    /*
    Test case #4
    Step 1. Go to https://practicecybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter only one alphabetic character into last name input box.
    Step 4. Verify that warning message is displayed:
    “The last name must be more than 2 and less than 64 characters long”
     */
    @Test
    public void test4(){
        WebElement lastName =driver.findElement(By.name("lastname"));
        lastName.sendKeys("a");
        WebElement invalidLNameMessage=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[2]"));
        Assert.assertTrue(invalidLNameMessage.isDisplayed());
    }

    /*
    Step 1. Go to “https://practicecybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Enter any valid first name.
Step 4. Enter any valid last name.
Step 5. Enter any valid user name.
Step 6. Enter any valid password.
Step 7. Enter any valid phone number.
Step 8. Select gender.
Step 9. Enter any valid date of birth.
Step 10. Select any department.
Step 11. Enter any job title.
Step 12. Select java as a programming language.
Step 13. Click Sign up.
Step 14. Verify that following success message is
displayed: “You've successfully completed
registration!”
     */
    @Test
    public void test5() {

        driver.findElement(By.name("firstname")).sendKeys("isaac");
        driver.findElement(By.name("lastname")).sendKeys("hope");
        driver.findElement(By.name("username")).sendKeys("ihoprede");
        driver.findElement(By.name("email")).sendKeys("ihope@cyber.com");
        driver.findElement(By.name("password")).sendKeys("123sdghfs");
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");
        driver.findElement(By.cssSelector("input[value='male']")).click();
        driver.findElement(By.name("birthday")).sendKeys("01/01/2000");
        Select departmentSelect = new Select(driver.findElement(By.name("department")));
        departmentSelect.selectByVisibleText("MPDC");
        Select jobTitleSelect = new Select(driver.findElement(By.name("job_title")));
        jobTitleSelect.selectByVisibleText("SDET");
        driver.findElement(By.xpath("//label[text()='Java']/preceding-sibling::input")).click();
        driver.findElement(By.id("wooden_spoon")).click();

        BrowserUtils.wait(4);

        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();
        BrowserUtils.wait(4);

        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
