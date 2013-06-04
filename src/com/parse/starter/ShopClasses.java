package com.parse.starter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseCloud;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseObject;
import com.parse.ParseException;

public class ShopClasses extends Activity {

	private TextView test;
	private ListView classList;
	private List<ParseObject> courseList;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
		}
		setContentView(R.layout.shop);
		ParseAnalytics.trackAppOpened(getIntent());
		TimePicker tp = (TimePicker) findViewById(R.id.timePicker);

		test = (TextView) findViewById(R.id.testing);
		classList = (ListView) findViewById(R.id.class_list);

		tp.setCurrentHour(Calendar.HOUR_OF_DAY);
		tp.setCurrentMinute(Calendar.MINUTE);
		tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				updateDisplay(hourOfDay, minute);
			}
		});

		//Parse CloudCode call
		HashMap<String, Object> params = new HashMap<String, Object>();
		String day = "M";
		params.put("day", day);
		ParseCloud.callFunctionInBackground("coursesAtTime", params, new FunctionCallback<JSONArray>() {
			public void done(JSONArray cList, ParseException e) {
				if (e == null) {
					Log.d("course", "Retrieved courses");
					courseList = Util.jsonArrayToParseObjectList(cList);
					classList.setAdapter(new ListAdapter(ShopClasses.this, 
							courseList));
				}
				else 
				{
					Log.d("course", e.getMessage());
				}
			}
		});

		// set each row on listview clickable to lead to individual session
		// screens
		classList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Open up class details and pass them via intent
				Intent intent = new Intent(ShopClasses.this,
						IndividualCourse.class);
				ParseObject course = courseList.get(position);
				intent.putExtra("cat_num", course.getString("cat_num"));
				intent.putExtra("term", course.getString("term"));
				intent.putExtra("field", course.getString("field"));
				intent.putExtra("number", course.getString("number"));
				intent.putExtra("title", course.getString("title"));
				intent.putExtra("description", course.getString("description"));
				intent.putExtra("faculty", course.getString("faculty"));
				intent.putExtra("notes", course.getString("notes"));
				intent.putExtra("meetings", course.getString("meetings"));
				intent.putExtra("building", course.getString("building"));
				intent.putExtra("room", course.getString("room"));
				intent.putExtra("bracketed", course.getBoolean("bracketed"));

				startActivity(intent);

			}
		});
	}

	private void updateDisplay(int hour, int min) {
		// this is to test whether changing the hour does anything
		test.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(min)));

		// WHAT THIS SHOULD DO:
		// query and change listview according to time set

	}

	// helper function for int to string
	// just saying there's a function in java that gets ascii values for you -ap
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
