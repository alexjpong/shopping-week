package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends Activity {
	// testing hi
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		ParseAnalytics.trackAppOpened(getIntent());		
	}
	public void registerSubmit(View v) {

		EditText username = (EditText) findViewById(R.id.register_email);
		EditText password = (EditText) findViewById(R.id.register_pass);

		ParseUser user = new ParseUser();

		user.setUsername(username.getText().toString());
		user.setPassword(password.getText().toString());
		user.setEmail(username.getText().toString());


		// other fields can be set just like with ParseObject
		//user.put("phone", "650-253-0000");
		try
		{
			user.signUpInBackground(new SignUpCallback() {
				public void done(ParseException e) {
					if (e == null) {
						// Hooray! Let them use the app now.
						Intent intent = new Intent(Register.this, Homescreen.class);
						startActivity(intent);
					} 
					else {
						// Sign up didn't succeed. Look at the ParseException
						// to figure out what went wrong
						Log.e("register error", e.getMessage());
					}
				}
			})
			;
		}
		catch(Exception e)
		{
			Log.e("register error", e.getMessage());
		}
	}
	public void gotoLogin(View v)
	{
		Intent intent = new Intent(Register.this, Login.class);
		startActivity(intent);
	}
}
