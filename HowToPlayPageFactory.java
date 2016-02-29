import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HowToPlayPageFactory {
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='details']//div")
	WebElement gameGuideName;
	
	@FindBy(xpath="//div[@class='details']//small")
	WebElement gameGuideSize;
	
	public HowToPlayPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getGameGuideName() {
		return gameGuideName.getText();		 
	}
	
	public String getGameGuideSize() {
		return gameGuideSize.getText();		 
	}
}
