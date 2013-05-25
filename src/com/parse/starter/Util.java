package com.parse.starter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.parse.ParseObject;

import android.util.Log;

public class Util {
	/**
	 * Takes a URL and gets the string that it the URL returns
	 * 
	 * @param urlString
	 * @return A string
	 */
	public static String readUrl(String urlString) throws Exception {
		URL url = new URL(urlString);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String readIn = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			readIn = readIn + inputLine;
		}
		in.close();
		return readIn;

	}

	/**
	 * Read in a url and load its corresponding JSON into the cloud
	 * 
	 * @param String url
	 * @return loads the result into the cloud
	 */
	public static void loadCatalog(String url) {
		//read url in
		JSONTokener jsonTokener;
		JSONArray jsonArray=null;
		try {
			String s = readUrl(url);
			s.trim();
			jsonTokener =  new JSONTokener(s);
			jsonArray = new JSONArray(jsonTokener);
		} catch (Exception e) {
			Log.e("error reading json catalog","error: "+e.getMessage());
		}

		//insert url into parseobject
		for(int i = 0; i < jsonArray.length(); i++)
		{	
			JSONObject entryObject = null;
			try {
				entryObject = (JSONObject) jsonArray.get(i);				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ParseObject course = new ParseObject("Course");

			try {
				course.put("cat_num", entryObject.getString("cat_num"));
				course.put("term", entryObject.getString("term"));
				course.put("field", entryObject.getString("field"));
				course.put("number", entryObject.getString("number"));
				course.put("title", entryObject.getString("title"));
				course.put("description", entryObject.getString("description"));
				course.put("faculty", entryObject.getString("faculty"));
				course.put("notes", entryObject.getString("notes"));
				course.put("meetings", entryObject.getString("meetings"));
				course.put("building", entryObject.getString("building"));
				course.put("room", entryObject.getString("room"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			course.saveInBackground();
		}
	}

}
