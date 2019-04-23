package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
	WebElement userNameField;
	WebElement passwordField;
	WebElement logInBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// initialize the WebElement with Search filed xPath
		userNameField = driver.findElement(By.xpath(".//*[@id='doc']/div/div[1]/div[1]/div[1]/form/div[1]/input"));
		passwordField = driver.findElement(By.xpath(".//*[@id='doc']/div/div[1]/div[1]/div[1]/form/div[2]/input"));
		logInBtn = driver.findElement(By.xpath(".//*[@id='doc']/div/div[1]/div[1]/div[1]/form/input[1]"));
	}
	
	//Login method
	public void LogInTo(String userName, String password) {
		sendText(userNameField, userName);
		sendText(passwordField, password);
		click(logInBtn);
	}

}
