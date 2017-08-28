package com.example.apartment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class TweleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twele);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twele, menu);
		return true;
	}
	public void evtClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(TweleActivity.this,EigthActivity.class);
		startActivity(next);
	}
	public void mainClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(TweleActivity.this,NinethActivity.class);
		startActivity(next);
	}
	public void vcfClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(TweleActivity.this,CmpmngActivity.class);
		startActivity(next);
	}
	
	
}
