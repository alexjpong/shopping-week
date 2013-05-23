package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class Homescreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			Intent intent = new Intent(Homescreen.this, Login.class);
			startActivity(intent);
		}
		setContentView(R.layout.main);
		ParseAnalytics.trackAppOpened(getIntent());
		DigitalClock dc = (DigitalClock) findViewById(R.id.clock);
		Button logout = (Button) findViewById(R.id.logout);

	}

	public void logout(View view) {
		ParseUser.logOut();
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			Intent intent = new Intent(Homescreen.this, Login.class);
			startActivity(intent);
		}

	}
}
