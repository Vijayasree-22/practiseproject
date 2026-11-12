package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Base;

public class HomePage extends Base{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//SectionOne
	@FindBy(xpath = "//h1[text()='Discover & Book']")
	private WebElement Discover_Book_h;
	
	@FindBy(xpath = "//span[text()='Amazing Events']")
	private WebElement AmazingEvents_h;
	
	@FindBy(xpath = "//p[text()='From tech conferences to live concerts, sports events to cultural festivals — find experiences that inspire you.']")
	private WebElement section1Text;
	
	@FindBy(linkText = "Browse Events →")
	private WebElement BrowseEvents_Btn;
	
	@FindBy(linkText = "My Bookings")
	private WebElement MyBookings_Btn;
	
	public String getHeading1_inSection1() {
		return gettext(Discover_Book_h);
	}
	public String getHeading2_inSection1() {
		return gettext(AmazingEvents_h);
	}
	public String gettext1_inSection1() {
		return gettext(section1Text);
	}
	public void clickBrowseEvents_Btn() {
		click(BrowseEvents_Btn);
	}
	public void clickMyBookings_Btn() {
		click(MyBookings_Btn);
	}
	public void urltobe(String url) {
		waitforurlToBe(url);
	}
	
	
	

}
