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
import java.util.List;

public class CommentsAppTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikram\\Downloads\\chromedriver-win32 (4)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qacommentsapp.ccbp.tech/");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @DataProvider
    public Object[][] CommentsDataset() {
        return new Object[][]{
                {"John", "Innovative and interconnected systems."},
                {"Alice", "Smart factories, improved efficiency."},
                {"Robert", "AI-powered automation, limitless potential."},
                {"Harry", "Data-driven decision-making, game-changer."},
                {"Bob", "IoT revolutionizing daily life."}
        };

    }

    @Test(priority = 1)
    public void testCommentsCount() {
        String[] names = {"John", "Alice", "Robert", "Harry", "Bob"};
        String[] comments = {"Innovative and interconnected systems.", "Smart factories, improved efficiency", "AI-powered automation, limitless potential.", "Data-driven decision-making, game-changer.", "IoT revolutionizing daily life."};
        int counter = 0;
        for (int i = 0; i < names.length; i++) {
            WebElement inputName = driver.findElement(By.className("name-input"));
            inputName.clear();
            inputName.sendKeys(names[i]);
            WebElement inputComment = driver.findElement(By.className("comment-input"));
            inputComment.clear();
            inputComment.sendKeys(comments[i]);
            WebElement buttonel = driver.findElement(By.className("add-button"));
            buttonel.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("comments-count"), String.valueOf(counter + 1)));
            counter++;
            String commentsCount = driver.findElement(By.className("comments-count")).getText();
            int actualcount = Integer.parseInt(commentsCount.trim());
            if (actualcount != counter) {
                System.out.println("Comments count do not match. Expected: " + counter + " But Actual: " + actualcount);
            }

        }
    }

    @Test(priority = 2,dataProvider = "CommentsDataset")
    public void testCommentsInitial(String input, String Comment) {
        WebElement inputEl = driver.findElement(By.className("name-input"));
        inputEl.clear();
        inputEl.sendKeys(input);
        WebElement CommentEl = driver.findElement(By.className("comment-input"));
        CommentEl.clear();
        CommentEl.sendKeys(Comment);
        String expectedInitial = String.valueOf(input.charAt(0)).toUpperCase();
        WebElement buttonel = driver.findElement(By.className("add-button"));
        buttonel.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("initial")));
        WebElement actualInitial = driver.findElement(By.className("initial"));
        String actualIntialText = actualInitial.getText();
        if (!actualInitial.equals(expectedInitial)) {
            System.out.println("Initial do not match");
        }
        Assert.assertEquals(actualIntialText, expectedInitial, "Initial do not match");

    }

    @Test(priority = 3)
    public void testCommentsAndOrder() {
        String[] names = {"John", "Alice", "Robert", "Harry", "Bob"};
        String[] comments = {"Innovative and interconnected systems.", "Smart factories, improved efficiency", "AI-powered automation, limitless potential.", "Data-driven decision-making, game-changer.", "IoT revolutionizing daily life."};
        for (int i = 0; i < names.length; i++) {
            WebElement inputName = driver.findElement(By.className("name-input"));
            inputName.clear();
            inputName.sendKeys(names[i]);
            WebElement inputComment = driver.findElement(By.className("comment-input"));
            inputComment.clear();
            inputComment.sendKeys(comments[i]);
            WebElement buttonel = driver.findElement(By.className("add-button"));
            buttonel.click();
        }
        List<WebElement> order1 = driver.findElements(By.cssSelector("div.username-time-container p:first-of-type"));
        List<WebElement> order2 = driver.findElements(By.cssSelector("p.comment"));
        for (int j = 0; j < names.length; j++) {
            String actualname = order1.get(j).getText().trim();
            String actualComment = order2.get(j).getText().trim();
            if (!actualname.equals(names[j])) {
                System.out.println("Order does not match");
            }
            if (!actualComment.equals(comments[j])) {
                System.out.println("Comment does not correspond to the username");
            }
        }
    }

    @Test(priority = 4)
    public void testCommentLikes() {
        String[] names = {"John", "Alice", "Robert", "Harry", "Bob"};
        String[] comments = {"Innovative and interconnected systems.", "Smart factories, improved efficiency", "AI-powered automation, limitless potential.", "Data-driven decision-making, game-changer.", "IoT revolutionizing daily life."};
        for (int i = 0; i < names.length; i++) {
            WebElement inputName = driver.findElement(By.className("name-input"));
            inputName.clear();
            inputName.sendKeys(names[i]);
            WebElement inputComment = driver.findElement(By.className("comment-input"));
            inputComment.clear();
            inputComment.sendKeys(comments[i]);
            WebElement buttonel = driver.findElement(By.className("add-button"));
            buttonel.click();
        }
        List<WebElement> likedButtons = driver.findElements(By.cssSelector("div.like-container button"));
        int counter = 0;
        for (int j = 0; j < likedButtons.size(); j++) {
            likedButtons.get(j).click();
            counter++;
            List<WebElement> likedimage = driver.findElements(By.cssSelector("button.button.active"));
            int likescount = likedimage.size();

            if (likescount!= counter) {
                System.out.println("Likes count do not match");

            }
        }
    }
    @Test(priority = 5)
    public void testCommentDeletes(){
        String[] names = {"John", "Alice", "Robert", "Harry", "Bob"};
        String[] comments = {"Innovative and interconnected systems.", "Smart factories, improved efficiency", "AI-powered automation, limitless potential.", "Data-driven decision-making, game-changer.", "IoT revolutionizing daily life."};
        for (int i = 0; i < names.length; i++) {
            WebElement inputName = driver.findElement(By.className("name-input"));
            inputName.clear();
            inputName.sendKeys(names[i]);
            WebElement inputComment = driver.findElement(By.className("comment-input"));
            inputComment.clear();
            inputComment.sendKeys(comments[i]);
            WebElement buttonel = driver.findElement(By.className("add-button"));
            buttonel.click();
        }
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[data-testid='delete']"));
        int counter = 5;
        for (int j = 0; j < deleteButtons.size(); j++) {
            deleteButtons.get(j).click();
            counter--;
            String deleteCount = driver.findElement(By.className("comments-count")).getText();
            int actualcount = Integer.parseInt(deleteCount.trim());
            if (actualcount != counter) {
                System.out.println("Comments count do not match. Expected: " + counter + " But Actual: " + actualcount);
            }
        }

    }
}
