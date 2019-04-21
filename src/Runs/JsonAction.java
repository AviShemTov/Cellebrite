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

	public void collectForJSON(String title, String description, List<String> tags, String time, String lang,
			String stars, String ts) throws IOException {

		// Getting data ready for the Writing method
		JSONObject obj = new JSONObject();
		obj.put("Title", title);
		obj.put("Description", description);
		obj.put("Time", time);
		obj.put("Lang", lang);
		obj.put("Stars", stars);

		JSONArray list = new JSONArray();
		for (Object tag : tags) {
			list.add(tag);
		}
		obj.put("Tags", list);
		String fileName = "SecurityResultGitHub-" + ts + ".json";

		// Calling the actual file creation method
		writeToJSON(obj, fileName);

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
