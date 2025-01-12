package Day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing5 {

    public static void main(String[] args) {

        // Set up WebDriver and ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\libs\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1: Log in as Admin
            driver.get("http://schoolapps.free.nf/login.php");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uname")));
            driver.findElement(By.name("uname")).sendKeys("elias");
            driver.findElement(By.name("pass")).sendKeys("123");
            driver.findElement(By.name("role")).sendKeys("Admin");
            driver.findElement(By.cssSelector(".btn.btn-primary")).click();

            // Wait for Dashboard to Load
            wait.until(ExpectedConditions.urlToBe("http://schoolapps.free.nf/admin/index.php"));
            System.out.println("Login successful!");

            // Step 2: Navigate to Delete Teacher URL
            String teacherId = "10"; // Change to the required teacher_id
            String deleteUrl = "http://schoolapps.free.nf/admin/teacher-delete.php?teacher_id=" + teacherId;
            driver.get(deleteUrl);

            // Wait for the page to load (assuming there is a confirmation message after deletion)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));

            // Step 3: Validate the Success Message
            String successMessage = driver.findElement(By.className("alert-success")).getText();
            if (successMessage.contains("Teacher deleted successfully")) {
                System.out.println("Test Passed: " + successMessage);
            } else {
                System.out.println("Test Failed: Unexpected message - " + successMessage);
            }

        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
