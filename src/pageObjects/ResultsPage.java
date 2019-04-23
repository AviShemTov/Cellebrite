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

	List<String> totalHashTags = new ArrayList<>();
	List<String> totalMentioneds = new ArrayList<>();
	int count = 0;

	public ResultsPage(WebDriver driver) {
		super(driver);

	}

	public List<List<String>> getResultsLinesData(String searchedWord) {
		
		//Adding the headers to Lists for them to print first
		totalHashTags.add("Hash Tags List:");
		totalHashTags.add("******************");
		totalMentioneds.add("Mentioneds List:");
		totalMentioneds.add("******************");
		
		//Creating a loop 100 latest Twitter results
		for (i = 1; i <= 100; i++) {
			String xPath = ".//*[@id='stream-items-id']/li[" + i + "]/div[1]/div[2]/div[2]/p";
			try {
				resultLine = driver.findElement(By.xpath(xPath));
			} catch (Exception e) {
			}
			String result = resultLine.getText();

			// Counting appearance for a given word
			String word[] = result.split(" ");
			for (int i = 0; i < word.length; i++) {
				if (searchedWord.equalsIgnoreCase(word[i]))
					count++;
			}

			// If the result line we scraped has # in it, we will call the method to take
			// it's text and add it to HashTags List
			if (contains(result, '#') == true) {
				totalHashTags.addAll(getHashTagsList(i));
			}

			// If the result line we scraped has @ in it, we will call the method to take
			// it's text and add it to Mentioneds List
			if (contains(result, '@') == true) {
				totalMentioneds.addAll(getMentionsList(i));
			}

			//Using Java Script for Results page scroll down
			JavascriptExecutor jsx = (JavascriptExecutor) driver;
			jsx.executeScript("window.scrollBy(0,500)");
			
			//Waiting for results to load every 20 
			if (i == 21 || i == 41 || i == 61 || i == 81) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("The word '" + searchedWord + "' has **" + count + "** appearences. \n");
		List<List<String>> result = new ArrayList<List<String>>();
		result.add(totalHashTags);
		result.add(totalMentioneds);
		// result.add(count);

		return result;
	}

	public boolean contains(String str, char chr) {
		return str.indexOf(chr) != -1;
	}

	public List<String> getMentionsList(int i) {

		String mentionPath = ".//*[@id='stream-items-id']/li[" + i + "]/div[1]/div[2]/div[2]/p/a/b";

		ArrayList<String> mentionsList = new ArrayList<>();
		List<WebElement> mentions = driver.findElements(By.xpath(mentionPath));

		for (WebElement mention : mentions) {
			mentionsList.add(mention.getText());
		}
		return mentionsList;

	}

	public List<String> getHashTagsList(int i) {

		String hashTagPath = ".//*[@id='stream-items-id']/li[" + i + "]/div[1]/div[2]/div[2]/p/a/b";

		ArrayList<String> hashTagsList = new ArrayList<>();
		List<WebElement> hashTags = driver.findElements(By.xpath(hashTagPath));

		for (WebElement hashTag : hashTags) {
			hashTagsList.add(hashTag.getText());
		}
		return hashTagsList;

	}

}
