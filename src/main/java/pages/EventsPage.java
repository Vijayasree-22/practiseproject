package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Base;
import utils.eventTD;

public class EventsPage extends Base{

	public EventsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickEventCard(String eventName) {
	By EventCardLink = By.xpath("//article[@data-testid='event-card']//a[h3[contains(text(),'" + eventName + "')]]");
		driver.findElement(EventCardLink).click();
	}
	

}
