package com.parse.starter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// adapter for days dropdown
public class DropdownAdapter extends ArrayAdapter<String> {
	ArrayList<String> days;
	
	public DropdownAdapter(Activity activity, ArrayList<String> days) {
		super(activity, R.layout.list_item, R.id.classname, days);
		this.days = days;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		((TextView) view.findViewById(R.id.classname)).setText(days.get(position));
		((TextView) view.findViewById(R.id.room)).setText("");
		((TextView) view.findViewById(R.id.time)).setText("");

		return view;
	}
}
