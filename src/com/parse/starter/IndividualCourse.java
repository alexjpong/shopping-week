package com.parse.starter;

import java.util.List;

import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseCloud;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseObject;
import com.parse.ParseException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class IndividualCourse extends Activity {

	private String body;
	TextView test;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_course);
		test = (TextView) findViewById(R.id.individualCourseText);

		body = getIntent().getExtras().getString("field");
		body = body + getIntent().getExtras().getString("number") + ":\n";
		body = body + getIntent().getExtras().getString("description");

		ParseQuery query = new ParseQuery("Faculty");
		query.whereEqualTo("id", getIntent().getExtras().getString("faculty"));
		query.getFirstInBackground(new GetCallback() {
			public void done(ParseObject professor, ParseException e) {
				if (e == null) {
					Log.d("faculty join", "faculty retrieved.");
					body = body + "\nProfessor: "+professor.get("first")+ " "+ professor.get("middle") +
							" "+professor.get("last");
					test.setText(body);
				} else {
					Log.e("faculty join", "failed."+ e.getMessage());
				}
			}
		});

		test.setText(body);
	}
}
