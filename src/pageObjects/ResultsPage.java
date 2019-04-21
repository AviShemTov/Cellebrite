package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage extends BasePage {
	WebElement resultLine;
	int i = 1;
	List<String> linesData = new ArrayList<>();
	
	public ResultsPage(WebDriver driver) {
		super(driver);

	}

	public List<String> getResultsLinesData() {
		for (i = 1; i <= 100; i++) {
			String xPath = ".//*[@id='stream-items-id']/li[" + i + "]/div[1]/div[2]/div[2]/p";
			try {
				resultLine = driver.findElement(By.xpath(xPath));
			} catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println(i + ":  " + resultLine.getText());
			
			
			//If the result line we scraped has # in it, we will call the method to take it's text and print it as Hash Tags.
			if (contains(resultLine.getText(), '#') == true) {
				getHashTagsList(i);
			}
			
			//If the result line we scraped has @ in it, we will call the method to take it's text and print it as Mentions.
			if (contains(resultLine.getText(), '@') == true) {
				getMentionsList(i);
			}
			
			
			//System.out.println("\n\n");
			JavascriptExecutor jsx = (JavascriptExecutor)driver;
			jsx.executeScript("window.scrollBy(0,500)");
			if (i==21 || i==41 || i==61 || i==81 || i==97) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return linesData;
	}
	
	public boolean contains(String str, char chr) {
		  return str.indexOf(chr) != -1;
		}
	
	
	public List<String> getMentionsList(int i) {

		String mentionPath = ".//*[@id='stream-items-id']/li[" + i + "]/div[1]/div[2]/div[2]/p/a";

		ArrayList<String> mentionsList = new ArrayList<>();
		List<WebElement> mentions = driver.findElements(By.xpath(mentionPath));
		//System.out.println(mentions.size());

		for (WebElement mention : mentions) {
			System.out.println("Mention: " + mention.getText());
			mentionsList.add(mention.getText());
		}
		return mentionsList;

	}
	
	public List<String> getHashTagsList(int i) {

		String hashTagPath = ".//*[@id='stream-items-id']/li[" + i + "]/div[1]/div[2]/div[2]/p/a";

		ArrayList<String> hashTagsList = new ArrayList<>();
		List<WebElement> hashTags = driver.findElements(By.xpath(hashTagPath));
		//System.out.println(hashTags.size());

		for (WebElement hashTag : hashTags) {
			System.out.println("HashTag: " + hashTag.getText());
			hashTagsList.add(hashTag.getText());
		}
		return hashTagsList;

	}
	
}
