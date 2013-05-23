package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DigitalClock;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class Homescreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
	    	Intent intent = new Intent(Homescreen.this, Register.class);
	    	startActivity(intent);
		} 
		setContentView(R.layout.main);
		ParseAnalytics.trackAppOpened(getIntent());	
		DigitalClock dc = (DigitalClock) findViewById(R.id.clock);
	}
}
