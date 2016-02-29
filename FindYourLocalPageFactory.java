import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindYourLocalPageFactory {
	WebDriver driver;
	
	@FindBy(xpath="//button[contains(.,'Pub')]")
	WebElement buttonPub;
	
	@FindBy(id="venue-search")
	WebElement venueSearchBox;
	
	@FindBy(xpath="//small[@class='venue-details']")
	List<WebElement> venueList;
		
	
	public FindYourLocalPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickButtonPub() {
		buttonPub.click();		 
	}
	
	public void searchVenue(String name) {
		venueSearchBox.sendKeys(name);	 
	}
	
	public List<WebElement> getVenueList(){
		return venueList;
	}
}
