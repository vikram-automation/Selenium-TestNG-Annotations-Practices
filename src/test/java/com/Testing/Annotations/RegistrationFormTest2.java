package com.Testing.Annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationFormTest2 {
    public WebDriver driver;
    public WebDriverWait wait;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qaregform.ccbp.tech/");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testRegisterWithEmptyInputs(){
        // finding first name empty input feiled
        WebElement FirstName = driver.findElement(By.id("firstName"));
        FirstName.sendKeys("");
        // finding second name empty input feiled
        WebElement LastName = driver.findElement(By.id("lastName"));
        LastName.sendKeys("");
        // click submit button with empty inputs
        WebElement ButtonEl = driver.findElement(By.className("submit-button"));
        ButtonEl.click();
        // finding error messege element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String ExpectedText = "Required";
        String actualerror = errormsg.getText();
        Assert.assertEquals(ExpectedText,actualerror,"Error text at first name do not match");

    }
    @Test(priority = 2)
    public void testRegisterWithEmptyFirstname(){
        // finding first name empty input feiled
        WebElement FirstName = driver.findElement(By.id("firstName"));
        FirstName.sendKeys("");
        WebElement LastName = driver.findElement(By.id("lastName"));
        LastName.sendKeys("Doe");
        // click submit button with one empty
        WebElement ButtonEl = driver.findElement(By.className("submit-button"));
        ButtonEl.click();
        // finding error messege element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String ExpectedText = "Required";
        String actualerror = errormsg.getText();
        Assert.assertEquals(ExpectedText,actualerror,"Error text at first name do not match");
    }
    @Test(priority = 3)
    public void testRegisterWithEmptyLastname(){
        // finding first name empty input feiled
        WebElement FirstName = driver.findElement(By.id("firstName"));
        FirstName.sendKeys("John");
        // finding lastname empty
        WebElement LastName = driver.findElement(By.id("lastName"));
        LastName.sendKeys("");
        WebElement ButtonEl = driver.findElement(By.className("submit-button"));
        ButtonEl.click();
        // finding error messege element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String ExpectedText = "Required";
        String actualerror = errormsg.getText();
        Assert.assertEquals(ExpectedText,actualerror,"Error text at last name do not match");
    }
    @Test(priority = 4)
    public void testRegisterWithValidCreds(){
        // finding first name empty input feiled
        WebElement FirstName = driver.findElement(By.id("firstName"));
        FirstName.sendKeys("John");
        // finding lastname empty
        WebElement LastName = driver.findElement(By.id("lastName"));
        LastName.sendKeys("Doe");
        WebElement ButtonEl = driver.findElement(By.className("submit-button"));
        ButtonEl.click();
        // finding error messege element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.view-container p:first-of-type")));
        WebElement successmsg = driver.findElement(By.cssSelector("div.view-container p:first-of-type"));
        String ExpectedText = "Submitted Successfully";
        String actualerror = successmsg.getText();
        Assert.assertEquals(ExpectedText,actualerror,"Success message do not match");
    }
}
