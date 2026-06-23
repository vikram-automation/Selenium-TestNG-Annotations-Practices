package com.Testing.Annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NxtTrndzApplication {
    @Test(priority = 1)
    public void loginWithValidCredentials() {
        // Set the system property for Chrome driver with its path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        // Create a new instance of ChromeDrive
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulnxttrendz.ccbp.tech/login");
        // login input
        WebElement inputel = driver.findElement(By.id("username"));
        inputel.sendKeys("rahul");
        // password input
        WebElement passwordel = driver.findElement(By.id("password"));
        passwordel.sendKeys("rahul@2021");
        // click button element
        WebElement buttonel = driver.findElement(By.className("login-button"));
        buttonel.click();
        // redirect element
        String Expectedurl = "https://rahulnxttrendz.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(Expectedurl));
        String actualurl = driver.getCurrentUrl();
        Assert.assertEquals(Expectedurl,actualurl,"url do not match");
        //Testing assert method working with currect and wrong example
        String expextedHeading = "Clothes That Get YOU Noticed";
        WebElement headinel = driver.findElement(By.className("home-heading"));
        String actualHeading = headinel.getText();
        Assert.assertEquals(expextedHeading,actualHeading,"Homepage heading does not match");
        driver.quit();
    }
    @Test(priority = 2)
    public void loginWithInValidCredentials(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        // Create a new instance of ChromeDrive
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulnxttrendz.ccbp.tech/login");
        // login input
        WebElement inputel = driver.findElement(By.id("username"));
        inputel.sendKeys("rahul");
        // password input
        WebElement passwordel = driver.findElement(By.id("password"));
        passwordel.sendKeys("rahul2021");
        // click button element
        WebElement buttonel = driver.findElement(By.className("login-button"));
        buttonel.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        WebElement errormsgel = driver.findElement(By.className("error-message"));
        String errorText = errormsgel.getText();
        Assert.assertEquals(errorText,"*username and password didn't match");
        driver.quit();

    }
}
