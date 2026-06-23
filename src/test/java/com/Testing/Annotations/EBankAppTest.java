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

public class EBankAppTest {
    public WebDriver driver;
    public WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qaebank.ccbp.tech/ebank/login");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void testLoginWithEmptyInputs(){
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("");
        WebElement Pin = driver.findElement(By.id("pinInput"));
        Pin.sendKeys("");
        WebElement Loginbtn = driver.findElement(By.className("login-button"));
        Loginbtn.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String ExpectedText = "Invalid user ID";
        String actualText = errorMsg.getText();
        Assert.assertEquals(ExpectedText,actualText,"Error text with empty input fields");
    }
    @Test(priority = 2)
    public void estLoginWithEmptyUserId() {
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("");
        WebElement Pin = driver.findElement(By.id("pinInput"));
        Pin.sendKeys("231225");
        WebElement Loginbtn = driver.findElement(By.className("login-button"));
        Loginbtn.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String ExpectedText = "Invalid user ID";
        String actualText = errorMsg.getText();
        Assert.assertEquals(ExpectedText, actualText, "Error text with empty User ID do not");
    }
    @Test(priority = 3)
    public void testLoginWithEmptyPin(){
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        WebElement Pin = driver.findElement(By.id("pinInput"));
        Pin.sendKeys("");
        WebElement Loginbtn = driver.findElement(By.className("login-button"));
        Loginbtn.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String ExpectedText = "Invalid PIN";
        String actualText = errorMsg.getText();
        Assert.assertEquals(ExpectedText,actualText,"Error text with empty PIN do not match");
    }
    @Test(priority = 4)
    public void testLoginWithInvalidCreds(){
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        WebElement Pin = driver.findElement(By.id("pinInput"));
        Pin.sendKeys("123456");
        WebElement Loginbtn = driver.findElement(By.className("login-button"));
        Loginbtn.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String ExpectedText = "User ID and PIN didn't match";
        String actualText = errorMsg.getText();
        Assert.assertEquals(ExpectedText,actualText,"Error text with invalid PIN do not match");

    }
    @Test(priority = 5)
    public void testLoginWithValidCreds(){
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        WebElement Pin = driver.findElement(By.id("pinInput"));
        Pin.sendKeys("231225");
        WebElement Loginbtn = driver.findElement(By.className("login-button"));
        Loginbtn.click();
        String ExpectedUrl = "https://qaebank.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(ExpectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(ExpectedUrl,actualUrl,"URLs do not match");
    }
}
