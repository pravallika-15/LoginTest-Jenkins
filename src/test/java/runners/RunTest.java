import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RunTest {

    @Test
    public void testValidLogin() throws InterruptedException {
        // Setup Chrome for GitHub Actions - must be headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        try {
            // Go to login page
            driver.get("https://the-internet.herokuapp.com/login");

            // Enter credentials
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // Wait for page to load
            Thread.sleep(2000);

            // Verify login success
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/secure"), "Login failed - URL does not contain /secure");

        } finally {
            // Always close the browser - critical for CI
            driver.quit();
        }
    }
}