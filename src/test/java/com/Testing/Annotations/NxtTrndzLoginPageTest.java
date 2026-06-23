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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class NxtTrndzLoginPageTest {
    public WebDriver driver;
    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulnxttrendz.ccbp.tech/login");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
    @DataProvider
    public Object[][] loginData(){
        return new Object[][]{
                {"vikram","rahul@2021","*invalid username"},
                {"rahul","vikram2022","*username and password didn't match"},
                {"rahul@1","rahul2022","*invalid username"},
                {"","","*Username or password is invalid"}

        };

    }
    @Test(priority = 1)
    public void invalidUsername(){
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("vikr@m");
        // password element
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("rahul@2021");
        // button element
        WebElement buttonEl = driver.findElement(By.className("login-button"));
        buttonEl.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String errorText = errormsg.getText();
        Assert.assertEquals(errorText,"*invalid username");

    }
    @Test(priority = 2)
    public void invalidPassword(){
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("rahul");
        // password element
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("rahul2023");
        // button element
        WebElement buttonEl = driver.findElement(By.className("login-button"));
        buttonEl.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String errorText = errormsg.getText();
        Assert.assertEquals(errorText,"*username and password didn't match");
    }
    @Test(priority = 3)
    public  void BothUsernameandPasswordInvalid(){
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("vikr@m");
        // password element
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("vikram@2022");
        // button element
        WebElement buttonEl = driver.findElement(By.className("login-button"));
        buttonEl.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String errorText = errormsg.getText();
        Assert.assertEquals(errorText,"*invalid username");

    }
    @Test(priority = 4)
    public void BothUsernameandPasswordareEmpty(){
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("");
        // password element
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("");
        // button element
        WebElement buttonEl = driver.findElement(By.className("login-button"));
        buttonEl.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String errorText = errormsg.getText();
        Assert.assertEquals(errorText,"*Username or password is invalid");

    }

    @Test(priority = 5, dataProvider = "loginData")
    public void loginwithInvaliCredential(String username ,String password,String expectederror){
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys(username);
        // password element
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys(password);
        // button element
        WebElement buttonEl = driver.findElement(By.className("login-button"));
        buttonEl.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsg = driver.findElement(By.className("error-message"));
        String errorText = errormsg.getText();
        Assert.assertEquals(errorText,expectederror,"Error messege is mismatched");

    }




}
