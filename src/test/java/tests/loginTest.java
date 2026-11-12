package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import context.WebDriverContext;
import listeners.ReportListener;
import pages.LoginPage;
import utils.loginTD;

@Listeners(listeners.ReportListener.class)
public class loginTest extends baseTest {
//login tests
	@Test(testName = "TC01-Login with valid credentials", description = "Verifies that a registered user can successfully authenticate into the EventHub application using a valid email and password", groups = {
			"Functional" })
	public void Login_with_valid_credentials() {
		WebDriver driver = WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		System.out.println("Logged in successfully, Title: " + driver.getTitle());
	}

	@Test(testName = "TC02-Login with both fields empty", description = "Verifies that appropriate validation error messages are displayed when the user submits the login form without entering an email address and password.", groups = {
			"Functional" })
	public void Login_with_both_fields_empty() {
		WebDriver driver = WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);

		login.openEventHub();
		login.signin(loginTD.emptyEmail, loginTD.emptyPassword);

		String emailError = login.getEmailError();
		Assert.assertEquals(emailError, "Enter a valid email");
		System.out.println("emailError : " + emailError);
		String passwordError = login.getPasswordError();
		Assert.assertEquals(passwordError, "Password must be at least 6 characters");
		System.out.println("passwordError : " + passwordError);
	}
}