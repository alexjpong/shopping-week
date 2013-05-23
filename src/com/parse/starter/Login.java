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
import com.parse.LogInCallback;

public class Login extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ParseAnalytics.trackAppOpened(getIntent());		
	}
	public void loginSubmit(View v)
	{
    	EditText username = (EditText) findViewById(R.id.login_email);
    	EditText password = (EditText) findViewById(R.id.login_pass);
    	
    	ParseUser.logInInBackground(username.getText().toString(),
    			password.getText().toString(), new LogInCallback() {
    		  public void done(ParseUser user, ParseException e) {
    		    if (user != null) {
    		      // Hooray! The user is logged in.
    		    	Intent intent = new Intent(Login.this, Homescreen.class);
			    	startActivity(intent);
    		    } 
    		    else {
    		      // Login failed. Look at the ParseException to see what happened.
					Log.e("login error", e.getMessage());
    		    }
    		  }
    		});
	}
	public void gotoRegister(View v)
	{
    	Intent intent = new Intent(Login.this, Register.class);
    	startActivity(intent);
	}
}
