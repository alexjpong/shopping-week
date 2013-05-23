package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class ShopClasses extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
		}
		setContentView(R.layout.shop);
		ParseAnalytics.trackAppOpened(getIntent());
	}
}
