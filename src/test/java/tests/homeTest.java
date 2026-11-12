package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import context.WebDriverContext;
import listeners.ReportListener;
import pages.HomePage;
import pages.LoginPage;
import utils.homeTD;
import utils.loginTD;
@Listeners(listeners.ReportListener.class)
public class homeTest extends baseTest{
	@Test(
		    testName = "Verify Home Page Sections",
		    description = "Verify home page headings, section description, Browse Events navigation and My Bookings navigation."
		)
	public void verify_sectionOne() {
		WebDriver driver = WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		Assert.assertTrue(home.getHeading1_inSection1().contains("Discover & Book"), "Section 1 heading is missing 'Discover & Book'");
		Assert.assertTrue(home.getHeading2_inSection1().contains("Amazing Events"), "Section 1 heading is missing 'Amazing Events'");
		Assert.assertEquals(home.gettext1_inSection1(), "From tech conferences to live concerts, sports events to cultural festivals — find experiences that inspire you.");
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		driver.navigate().back();
		home.clickMyBookings_Btn();
		home.urltobe(homeTD.mybooings_url);
	}
	
	@Test(
		    testName = "Verify Book Event Navigation",
		    description = "Verify user login and navigation to the events page through Browse Events."
		)
	public void bookEvent() {
		WebDriver driver = WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		
	}
}
