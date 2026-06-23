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

public class JobbyAppLoginPageTest {
    public WebDriver driver;
    public WebDriverWait wait;
    By loginbtn = By.className("login-button");
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qajobbyapp.ccbp.tech/login");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void testLoginWithEmptyInputs(){
        driver.findElement(loginbtn).click();
        String errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).getText();
        String expectedText = "*Username or password is invalid";
        Assert.assertEquals(expectedText,errormsg,"Error text with empty input fields does not match");
    }
    @Test(priority = 2)
    public void testLoginWithEmptyUsername(){
        WebElement passwordel = driver.findElement(By.id("passwordInput"));
        passwordel.sendKeys("rahul@2021");
        driver.findElement(loginbtn).click();
        String errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).getText();
        String expectedText = "*Username or password is invalid";
        Assert.assertEquals(expectedText,errormsg,"Error text with empty input field do not match");
    }
    @Test(priority = 3)
    public void testLoginWithEmptyPassword(){
        WebElement usernameel = driver.findElement(By.id("userNameInput"));
        usernameel.sendKeys("rahul");
        driver.findElement(loginbtn).click();
        String errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).getText();
        String expectedText = "*Username or password is invalid";
        Assert.assertEquals(expectedText,errormsg,"Error text with empty input field do not match");
    }
    @Test(priority = 4)
    public void testLoginWithInvalidCreds(){
        WebElement usernameel = driver.findElement(By.id("userNameInput"));
        usernameel.sendKeys("rahul");
        WebElement passwordel = driver.findElement(By.id("passwordInput"));
        passwordel.sendKeys("rahul");
        driver.findElement(loginbtn).click();
        String errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).getText();
        String expectedText = "*username and password didn't match";
        Assert.assertEquals(expectedText,errormsg,"Error text with invalid password do not match");

    }
    @Test(priority = 5)
    public void testLoginWithValidCreds(){
        WebElement usernameel = driver.findElement(By.id("userNameInput"));
        usernameel.sendKeys("rahul");
        WebElement passwordel = driver.findElement(By.id("passwordInput"));
        passwordel.sendKeys("rahul@2021");
        driver.findElement(loginbtn).click();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl,"URLs do not match");

    }

}
