package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

	WebElement searchField;

	public MainPage(WebDriver driver) {
		super(driver);
		// initialize the WebElement with Search filed cssSelector
		searchField = driver.findElement(By.cssSelector("#search-query"));
	}

	// Performing the search for the latest Donald's Twittes
	public void doSearch(String fieldText) {
		sendText(searchField, fieldText);
		searchField.submit();
	}

}
