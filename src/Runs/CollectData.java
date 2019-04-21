package Runs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.ResultsPage;

public class CollectData {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://twitter.com/");

		
		//Login to Twitter
		LoginPage logp = new LoginPage(driver);
		logp.LogInTo("avishemtov1", "1qaz@wsx");
		
		
		//Running the search
		MainPage mp = new MainPage(driver);
		mp.doSearch("from:@realDonaldTrump");
		mp.sleep(2000);
		
		//Scraps Donald's 100 Twittes data 
		ResultsPage rp = new ResultsPage(driver);
		rp.getResultsLinesData();
		
		//Prints Scraped HashTags && Mentioneds to JSON
		
		
		//driver.close();
		
		
/*		JsonAction ja = new JsonAction();
		ResultsPage rp = new ResultsPage(driver);
		String ts = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());

		// Loop for 5 result pages and write them to json
		for (int a = 1; a < 6; a++) {
			for (int i = 1; i < 11; i++) {

				try {
					ja.collectForJSON(rp.getTitleValues(i), rp.getDescriptionValues(i), rp.getTagsValues(i),
							rp.getTimeValues(i), rp.getLangValues(i), rp.getStarsValues(i), ts);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rp.nextResultPageClick();
		}
		driver.close();*/
	}
}
