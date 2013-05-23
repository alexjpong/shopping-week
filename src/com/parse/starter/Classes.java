package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class Classes extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
	    	Intent intent = new Intent(Classes.this, Register.class);
	    	startActivity(intent);
		} 
		setContentView(R.layout.classes);
		ParseAnalytics.trackAppOpened(getIntent());		
	}
}
