package com.example.apartment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ThirteenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thirteen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thirteen, menu);
		return true;
	}
	public void complClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(ThirteenActivity.this,SixthActivity.class);
		startActivity(next);
	}
	public void fectyClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(ThirteenActivity.this,SeventhActivity.class);
		startActivity(next);
	}
	public void eventClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(ThirteenActivity.this,EventActivity.class);
		startActivity(next);
	}


}
