import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class KenoWebTests {

	private WebDriver driver;
	private String baseURL;
	NavBarFactory navBar;
	HowToPlayPageFactory howToPlayPage;
	FindYourLocalPageFactory findYourLocalPage;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();		
		navBar = new NavBarFactory(driver);
		howToPlayPage = new HowToPlayPageFactory(driver);
		findYourLocalPage = new FindYourLocalPageFactory(driver);
		
		// Instead of https, if http is given, it will automatically redirect to https  
		baseURL = "https://keno.com.au?jurisdiction=ACT";
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testScenarios() {
		checkGameGuideNameAndSize();	
		findYourLocalPub("Keno");
	}
	
	public void checkGameGuideNameAndSize() {
		// Navigate to "How to play" section
		navBar.clickHowToPlayTab();
		
		// Scrolling the web page is not required for this test. Can be skipped by commenting it out.
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		
		// Get the pdf name and pdf size and compare it against the expected result
		String pdfName = howToPlayPage.getGameGuideName();
		String pdfSize = howToPlayPage.getGameGuideSize();
		Assert.assertEquals(pdfName, "Game Guide.pdf");
		Assert.assertEquals(pdfSize, "3.3MB");
	}
	
	public void findYourLocalPub(String name) {		
		// Navigate to "Find your local" section
		navBar.clickFindYourLocalTab();
		
		// Get rid of the share location prompt
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		
		// Click the button "Pub" and type in the name to search for
		findYourLocalPage.clickButtonPub();
		findYourLocalPage.searchVenue(name);
		
		// Locate and assert the search results with the expected results
		for (WebElement venue : findYourLocalPage.getVenueList()) {
			String venueType = venue.getText();
			Assert.assertEquals(venueType, "PUB");
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
