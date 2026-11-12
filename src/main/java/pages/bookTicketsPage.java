package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import utils.eventTD;

public class bookTicketsPage extends Base{

	
	public bookTicketsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(id="ticket-count")
	private WebElement ticketcount;
	
	@FindBy(xpath="//button[normalize-space()='+']")
	private WebElement plusBtn;
	
	@FindBy(xpath="//button[normalize-space()='−']")
	private WebElement minusBtn;
	
	@FindBy(xpath="//div[@class=\"flex justify-between text-gray-600\"]//span[1]")
	private WebElement grayAreaOne;
	
	@FindBy(xpath="//div[@class=\"flex justify-between text-gray-600\"]//span[2]")
	private WebElement grayAreaTwo;
	
	public boolean isEventImageDisplayed(String eventName) {
		By eventImage = By.cssSelector("img[alt='" + eventName + "']");
		return driver.findElement(eventImage).isDisplayed();
	}
	public boolean isEventTitleDisplayed(String eventName) {
		By eventTitle = By.xpath("//a[@href='/events']/following-sibling::span[2][normalize-space()='" + eventName + 
				"']");
		return driver.findElement(eventTitle).isDisplayed();
	}
	public void selectTickets(int requiredTickets) {
	    int currentTicketCount = Integer.parseInt(gettext(ticketcount));
	    while (currentTicketCount < requiredTickets) {
	        click(plusBtn);
	        currentTicketCount = Integer.parseInt(gettext(ticketcount));
	    }
	}

	public void reduceSelectedTickets(int requiredTickets) {
	    int currentTicketCount = Integer.parseInt(gettext(ticketcount));
	    while (currentTicketCount > requiredTickets) {
	        click(minusBtn);
	        currentTicketCount = Integer.parseInt(gettext(ticketcount));
	    }
	}
	
	public int getCurrentTickets() {
		  int currentTicketCount = Integer.parseInt(gettext(ticketcount));
		  return currentTicketCount;
	}
	
	public int getTicketCountInGreyArea() {
		String text = gettext(grayAreaOne);
		String[] ticketText = text.split("×");
		int ticketcount = Integer.parseInt(ticketText[1].trim().split(" ")[0]);
		return ticketcount;	
	}
	public int getTicketCostInGreyArea() {
		String text = gettext(grayAreaOne);
		String[] ticketText = text.split("×");
		String cost = ticketText[0].replace("$","").replace(",", "").trim();
		int Ticketcost = Integer.parseInt(cost);
		return Ticketcost;
	}
	public int getTotalCostInGreyArea() {
		String text = gettext(grayAreaTwo);
		String[] ticketText = text.split("×");
		String cost = ticketText[0].replace("$","").replace(",", "").trim();
		int Ticketcost = Integer.parseInt(cost);
		return Ticketcost;
	}
	
	public void waitForPlusButtonToBeEnabled() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(plusBtn));
	}
	
	public boolean minusBtn_isDisabled() {
		return minusBtn.isDisplayed();
	}
	public boolean plusBtn_isDisabled() {
		return plusBtn.isDisplayed();
	}
	

}
