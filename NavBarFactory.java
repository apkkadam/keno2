import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBarFactory {
	WebDriver driver;
	
	@FindBy(xpath="//p//a[@href='/how-to-play']")
	WebElement howToPlay;
	
	@FindBy(xpath="//p//a[@ui-sref='venue-finder']")
	WebElement findYourLocal;
	
	public NavBarFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickHowToPlayTab() {
		howToPlay.click();
	}
	
	public void clickFindYourLocalTab() {
		findYourLocal.click();
	}
}
