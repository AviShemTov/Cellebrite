package Runs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pageObjects.BasePage;

public class JsonAction {

	public void collectForJSON(List<String> list, String header) {

//		JSONObject hashTagsObj = new JSONObject();
//		JSONObject mentionedsObj = new JSONObject();
		JSONObject obj = new JSONObject();

		// Getting data ready for the Writing method
		JSONArray mentionedsList = new JSONArray();
		for (Object mentiond : list) {
			mentionedsList.add(mentiond);
		}

//		JSONArray hashTagsList = new JSONArray();
//		for (Object hashTag : hashTags) {
//			hashTagsList.add(hashTag);
//		}

//		hashTagsObj.put("HashTags", hashTagsList);
//		mentionedsObj.put("Mentioneds", mentionedsList);
		obj.put(header, mentionedsList);
		String fileName = "DonaldTrumpOnTwittr.json";

		// Calling the actual file creation method
		writeToJSON(obj, fileName);
//		writeToJSON(mentionedsObj, fileName);

	}

	public void writeToJSON(JSONObject obj, String fileName) {

		// Using simpleJSON to create json file and write data to it
		try (FileWriter file = new FileWriter(fileName, true)) {
			file.write(obj.toJSONString());
			file.write("\n");
			file.flush();
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		} catch (Exception e) {
		}
	}
}
