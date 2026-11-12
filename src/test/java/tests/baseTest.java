package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import context.WebDriverContext;

public class baseTest {
	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		System.out.println("Starting thread: " +
		        Thread.currentThread().getId());
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverContext.setDriver(driver);
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		WebDriverContext.tearDriver();
	}

}
