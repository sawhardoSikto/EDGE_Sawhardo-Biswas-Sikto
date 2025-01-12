package Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Testcase {

	public static void main(String[] args) throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\libs\\chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://chill-gamer-1b45e.web.app/login");
		Thread.sleep(1000);
		driver.findElement(By.name("email")).sendKeys("siktobiswa@gmail.com");
		driver.findElement(By.name("password")).sendKeys("12345Aa");
		driver.findElement(By.name("login")).click();
		String a=driver.getCurrentUrl();
		String d="https://chill-gamer-1b45e.web.app";
		if (a.equals(d))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		driver.close();
	driver.quit();
		
		
		
	}

}
