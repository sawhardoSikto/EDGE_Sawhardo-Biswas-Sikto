package Day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing4 {

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

            // Step 2: Navigate to Edit Teacher Page
            String teacherId = "10"; // Change to the required teacher_id
            String editUrl = "http://schoolapps.free.nf/admin/teacher-edit.php?teacher_id=" + teacherId;
            driver.get(editUrl);

            // Wait for the Edit Page to Load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fname")));

            // Step 3: Edit Teacher Details
            driver.findElement(By.name("fname")).clear();
            driver.findElement(By.name("fname")).sendKeys("Updated First Name");
            driver.findElement(By.name("lname")).clear();
            driver.findElement(By.name("lname")).sendKeys("Updated Last Name");
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("username")).sendKeys("updatedusername");
            driver.findElement(By.name("address")).clear();
            driver.findElement(By.name("address")).sendKeys("Updated Address");
            driver.findElement(By.name("employee_number")).clear();
            driver.findElement(By.name("employee_number")).sendKeys("E67890");
            driver.findElement(By.name("date_of_birth")).clear();
            driver.findElement(By.name("date_of_birth")).sendKeys("1995-05-20");
            driver.findElement(By.name("phone_number")).clear();
            driver.findElement(By.name("phone_number")).sendKeys("01712345678");
            driver.findElement(By.name("qualification")).clear();
            driver.findElement(By.name("qualification")).sendKeys("PhD in Computer Science");
            driver.findElement(By.name("email_address")).clear();
            driver.findElement(By.name("email_address")).sendKeys("updated.email@example.com");
            driver.findElement(By.xpath("//input[@value='Female']")).click(); // Select Female

            // Modify Subjects and Classes (if required)
            driver.findElement(By.xpath("//input[@name='subjects[]'][@value='1']")).click(); // Select subject
            driver.findElement(By.xpath("//input[@name='classes[]'][@value='1']")).click(); // Select class

            // Step 4: Submit the Form
            WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-primary"));

            // Ensure button is clickable
            wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            submitButton.click();

            // Step 5: Validate the Success Message
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
            String successMessage = driver.findElement(By.className("alert-success")).getText();
            if (successMessage.contains("Teacher updated successfully")) {
                System.out.println("Test Passed: " + successMessage);
            } else {
                System.out.println("Test Failed: Unexpected message - " + successMessage);
            }

        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        } finally {
            // Close the browser
            //driver.quit();
        }
    }
}
