package Day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing1 {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\libs\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Open the login page
            driver.get("http://schoolapps.free.nf/login.php");

            // Wait for the username field and input credentials
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uname")));
            WebElement usernameField = driver.findElement(By.name("uname"));
            WebElement passwordField = driver.findElement(By.name("pass"));
            WebElement roleField = driver.findElement(By.name("role"));

            // Enter login details
            usernameField.sendKeys("elasdias");
            passwordField.sendKeys("1223");
            roleField.sendKeys("Admin"); // Assuming this works as a text input; change if dropdown

            // Submit the login form
            WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
            loginButton.click();

            // Validate the redirection to the dashboard
            String expectedUrl = "http://schoolapps.free.nf/admin/index.php";
            try {
                // Wait until URL contains 'admin/index.php'
                wait.until(ExpectedConditions.urlToBe(expectedUrl));

                // Verify the URL
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.equals(expectedUrl)) {
                    System.out.println("Test Case Passed: Successfully navigated to the dashboard.");
                } else {
                    System.out.println("Test Case Failed: Redirected to an unexpected URL: " + currentUrl);
                }
            } catch (Exception e) {
                System.out.println("Test Case Failed: Unable to navigate to the dashboard. Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
