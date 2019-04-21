package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void click(WebElement el) {
		sleep(2000);
		el.click();
	}

	public void sendText(WebElement el, String text) {
		el.clear();
		el.sendKeys(text);
		sleep(500);
	}

	public void sleep(long milisec) {
		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
