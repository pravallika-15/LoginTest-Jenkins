package runners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;  // <-- ADD THIS
import org.testng.annotations.AfterMethod;   // <-- ADD THIS

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Required for Jenkins
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void validLoginTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String success = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(success.contains("You logged into a secure area"));
    }

    @Test
    public void invalidLoginTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String error = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(error.contains("Your username is invalid"));
    }

    @Test
    public void emptyFieldsTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String error = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(error.contains("Your username is invalid"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}