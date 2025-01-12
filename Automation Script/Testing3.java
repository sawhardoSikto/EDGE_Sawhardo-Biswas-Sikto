package Day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing3 {

    public static void main(String[] args) {

        // Set up WebDriver and ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\libs\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1: Login to the Application
            driver.get("http://schoolapps.free.nf/login.php");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uname")));
            driver.findElement(By.name("uname")).sendKeys("elias");
            driver.findElement(By.name("pass")).sendKeys("123");
            driver.findElement(By.name("role")).sendKeys("Admin");
            driver.findElement(By.cssSelector(".btn.btn-primary")).click();

            // Wait for Dashboard to Load
            wait.until(ExpectedConditions.urlToBe("http://schoolapps.free.nf/admin/index.php"));
            System.out.println("Login successful!");

            // Step 2: Navigate to Add Teacher Page
            driver.get("http://schoolapps.free.nf/admin/teacher-add.php");

            // Step 3: Fill Out the Teacher Form
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fname")));
            driver.findElement(By.name("fname")).sendKeys("John");
            driver.findElement(By.name("lname")).sendKeys("Doe");
            driver.findElement(By.name("username")).sendKeys("johndoe");
            driver.findElement(By.name("pass")).sendKeys("pass123");
            driver.findElement(By.name("address")).sendKeys("123 Main Street");
            driver.findElement(By.name("employee_number")).sendKeys("E12345");
            driver.findElement(By.name("phone_number")).sendKeys("0123456789");
            driver.findElement(By.name("qualification")).sendKeys("Master's Degree");
            driver.findElement(By.name("email_address")).sendKeys("john.doe@example.com");
            driver.findElement(By.xpath("//input[@value='Male']")).click();
            driver.findElement(By.name("date_of_birth")).sendKeys("1990-01-01");

            // Select Subject (e.g., Mathematics)
            driver.findElement(By.xpath("//input[@name='subjects[]'][@value='1']")).click();

            // Select Class (e.g., 10-A)
            driver.findElement(By.xpath("//input[@name='classes[]'][@value='1']")).click();

            // Step 4: Submit the Form
            driver.findElement(By.cssSelector(".btn.btn-primary")).click();

            // Step 5: Validate the Success Message
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
            String successMessage = driver.findElement(By.className("alert-success")).getText();
            if (successMessage.contains("Teacher added successfully")) {
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
