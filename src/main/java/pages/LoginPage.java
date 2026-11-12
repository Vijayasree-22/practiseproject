package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Base;

public class LoginPage extends Base {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "login-btn")
	private WebElement signInBtn;
	
	@FindBy(xpath = "(//p[@class='mt-1 text-xs text-red-600'])[1]")
	private WebElement EmailfieldError;
	
	@FindBy(xpath = "(//p[@class='mt-1 text-xs text-red-600'])[2]")
	private WebElement PasswordfieldError;
	
	public void openEventHub() {
		driver.get("https://eventhub.rahulshettyacademy.com/login");
	}
	
	public void signin(String email, String pw) {
		sendkeys(emailInput, email);
		sendkeys(passwordInput, pw);
		click(signInBtn);
	}
	
	// Gets the first error (Email)
	public String getEmailError() {
		return gettext(EmailfieldError);
	}
	
	// Gets the second error (Password)
	public String getPasswordError() {
		return gettext(PasswordfieldError);
	}
}