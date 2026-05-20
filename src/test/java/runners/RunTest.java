package runners;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunTest {

    @Test
    public void testValidLogin() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/login");

            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            Thread.sleep(2000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/secure"));

            String messageText = driver.findElement(By.id("flash")).getText();
            Assert.assertTrue(messageText.contains("You logged into a secure area!"));

            System.out.println("Test 1 Passed: Valid login successful");
            Thread.sleep(3000);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/login");

            driver.findElement(By.id("username")).sendKeys("wronguser");
            driver.findElement(By.id("password")).sendKeys("wrongpassword!");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            Thread.sleep(1000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("/secure"));

            String messageText = driver.findElement(By.id("flash")).getText();
            Assert.assertTrue(messageText.contains("Your username is invalid!"));

            System.out.println("Test 2 Passed: Invalid login rejected");
            Thread.sleep(2000);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testEmptyFields() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/login");

            // Don't enter anything, just click login
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            Thread.sleep(1000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("/secure"));

            String messageText = driver.findElement(By.id("flash")).getText();
            Assert.assertTrue(messageText.contains("Your username is invalid!"));

            System.out.println("Test 3 Passed: Empty fields rejected");
            Thread.sleep(2000);
        } finally {
            driver.quit();
        }
    }
}