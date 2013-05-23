package com.parse.starter;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;

import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseAnalytics;
import com.parse.ParseCloud;
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

	public void onClick(View view) {
		switch (view.getId()) {

		case R.id.shop_classes:
			Intent intentShop = new Intent(Homescreen.this, ShopClasses.class);
			startActivity(intentShop);
			break;
		case R.id.course_catalog:
			Intent intentCatalog = new Intent(Homescreen.this, CourseCatalog.class);
			startActivity(intentCatalog);
			break;
		case R.id.my_courses:
			Intent intentMyCourses = new Intent(Homescreen.this, MyCourses.class);
			startActivity(intentMyCourses);
			break;
		case R.id.help:
			break;
		case R.id.logout:
			ParseUser.logOut();
			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser == null) {
				Intent intentLogout = new Intent(Homescreen.this, Login.class);
				startActivity(intentLogout);
			}
			break;

		}
	}
	
	@Override
	public void onBackPressed() {
	}

}
