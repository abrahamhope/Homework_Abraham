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

public class TestCasesAll {
    private WebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com/registration_form";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    /*
    Step 1. Go to “https://practicecybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed: “The date of birth is not valid”
     */
    @Test
    public void test1(){
        driver.get(URL);
        driver.manage().window().maximize();

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
        driver.get(URL);
        driver.manage().window().maximize();
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
        driver.get(URL);
        driver.manage().window().maximize();
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
        driver.get(URL);
        driver.manage().window().maximize();
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
        driver.get(URL);
        driver.manage().window().maximize();
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

    /*
    Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
Step 2. And click on “File Upload".
Step 3. Upload any file with .txt extension from your computer.
Step 4. Click “Upload” button.
Step 5. Verify that subject is: “File Uploaded!” Step 6. Verify that uploaded file name is displayed.
     */
    @Test
    public void test7(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
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

    /*
    Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
Step 2. And click on “Autocomplete”.
Step 3. Enter “United States of America” into country input box.
Step 4. Verify that following message is displayed: “You selected: United States of America”
     */
    @Test
    public void test8(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
        Assert.assertTrue(driver.findElement(By.id("result")).isDisplayed());
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
