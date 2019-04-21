package Runs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
//		rp.getResultsLinesData();
		
		
		List<List<String>> totalResults = rp.getResultsLinesData();
		//totalResults.addAll();
		
		System.out.println("HashTags List:  ");
		int mainArraySize = totalResults.size();
		for(int a=0; a< mainArraySize; a++) {
//			rp.getResultsLinesData().get(a);

			int currentArraySize = totalResults.get(a).size();
			for (int b=0; b<currentArraySize; b++) {
				if(totalResults.get(a).get(b) != null)
					System.out.println(totalResults.get(a).get(b));
			}
			System.out.println("\n" + "Mentioneds List:  ");
		}

		driver.close();
	}
}
