package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import context.WebDriverContext;
import listeners.ReportListener;
import pages.EventsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.bookTicketsPage;
import utils.eventTD;
import utils.homeTD;
import utils.loginTD;
@Listeners(listeners.ReportListener.class)
public class bookTicketsTest extends baseTest{
	
	@Test(testName = "Verify selected event details",
		  description = "Verify that the selected event image and title are displayed on the booking page."
			)
	public void verify_selectedEventDetails_displayed(){
		WebDriver driver = WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		EventsPage event = new EventsPage(driver);
		bookTicketsPage book = new bookTicketsPage(driver);
		
		String eventName = eventTD.event_Name;
		
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		event.clickEventCard(eventName);
		Assert.assertTrue(book.isEventImageDisplayed(eventName), "Event image is not displayed");
		Assert.assertTrue(book.isEventTitleDisplayed(eventName), "Event title is not displayed");
		
	}
	@Test(
		    testName = "Verify ticket selection and counter",
		    description = "Increase the ticket quantity to 4, then decrease it to 2 and verify the selected ticket count."
		)
	public void verify_TicketsSelection() {
		WebDriver driver = WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		EventsPage event = new EventsPage(driver);
		bookTicketsPage book = new bookTicketsPage(driver);
		
		String eventName = eventTD.event_Name;
		
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		event.clickEventCard(eventName);
		book.selectTickets(4);
		book.reduceSelectedTickets(2);
		int ticketsSelected = book.getCurrentTickets();
		System.out.println(ticketsSelected);
		System.out.println(book.getTicketCountInGreyArea());
	}
	
	@Test(
		    testName = "Verify minus button disabled state",
		    description = "Verify that the minus button is disabled when the booking page opens and that the default ticket count is 1."
		)
	public void verify_minusBtnDisabledState() {
		WebDriver driver =  WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		EventsPage event = new EventsPage(driver);
		bookTicketsPage book = new bookTicketsPage(driver);
		String eventName = eventTD.event_Name;
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		event.clickEventCard(eventName);
		Assert.assertTrue(book.minusBtn_isDisabled(), "Defect:minus button is Not in DISABLED state");
		Assert.assertEquals(book.getCurrentTickets(), 1, "Defect:default ticketcount is Not ZERO");
	}
	
	@Test(
		    testName = "Verify plus button disabled at maximum",
		    description = "Verify that the plus button is enabled initially and becomes disabled after the maximum ticket quantity is selected."
		)
	public void verify_plusBtnDisabledState() throws InterruptedException {
		WebDriver driver =  WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		EventsPage event = new EventsPage(driver);
		bookTicketsPage book = new bookTicketsPage(driver);
		String eventName = eventTD.event_Name;
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		event.clickEventCard(eventName);
		Assert.assertFalse(book.plusBtn_isDisabled(), "Defect:plus button is in DISABLED state");
		book.selectTickets(9);
		Assert.assertTrue(book.plusBtn_isDisabled(), "Defect:plus button is Not in DISABLED state");
	}
	
	@Test(
		    testName = "Verify ticket cost and total amount",
		    description = "Select 2 tickets and verify the displayed price per ticket, total cost, and ticket count in the gray summary area."
		)
	public void verify_TicketCostInGrayArea() throws InterruptedException {
		WebDriver driver =  WebDriverContext.getDriver();
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		EventsPage event = new EventsPage(driver);
		bookTicketsPage book = new bookTicketsPage(driver);
		String eventName = eventTD.event_Name;
		login.openEventHub();
		login.signin(loginTD.validEmail, loginTD.validPassword);
		home.clickBrowseEvents_Btn();
		home.urltobe(homeTD.events_url);
		event.clickEventCard(eventName);
		book.selectTickets(2);
		System.out.println(book.getTicketCostInGreyArea());
		System.out.println(book.getTotalCostInGreyArea());
		System.out.println(book.getTicketCountInGreyArea());
	}
}
