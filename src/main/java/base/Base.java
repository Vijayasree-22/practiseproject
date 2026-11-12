package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public Base(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		PageFactory.initElements(driver, this);
	}
	
	protected WebElement waitforelementVisibility(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	protected void sendkeys(WebElement element, String text) {
		WebElement element1 = waitforelementVisibility(element);
		element1.clear();
		element1.sendKeys(text);
	}
	
	protected String gettext(WebElement element) {
		WebElement element1 = waitforelementVisibility(element);
		return element1.getText();
	}
	
	protected void waitforurlToBe(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}

}
