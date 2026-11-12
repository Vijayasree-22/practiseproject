package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class auto {
	@Test
	public void scenario1() {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://demoqa.com/");
		
		
		WebDriverWait e_wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		
		e_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		e_wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));
		
		e_wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/web/index.php/pim/viewPimModule']")));
		WebElement PIM = driver.findElement(By.cssSelector("a[href='/web/index.php/pim/viewPimModule']"));
		e_wait.until(ExpectedConditions.elementToBeClickable(PIM));
		PIM.click();
		
		
		WebElement employeeId = 
				driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		
		e_wait.until(ExpectedConditions.visibilityOf(employeeId));
		
		employeeId.sendKeys("98421");
		
		e_wait.until(ExpectedConditions.elementToBeClickable(employeeId));
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		
		WebElement id = driver.findElement(By.xpath("//div[@class=\"oxd-table-card\"]//div[@role=\"cell\"][2]"));
		e_wait.until(ExpectedConditions.visibilityOf(id));
		String text = id.getText();
		Assert.assertEquals(text, "98421");
		driver.quit();
	}
	

}
