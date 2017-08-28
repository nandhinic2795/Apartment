package com.example.apartment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(ThirdActivity.this,FourthActivity.class);
		startActivity(next);
	}
	public void TntClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(ThirdActivity.this,FifthActivity.class);
		startActivity(next);
	}
	public void socClick(View arg0) {
		// TODO Auto-generated method stub
		Intent next=new Intent(ThirdActivity.this,TenthActivity.class);
		startActivity(next);
	}
    
    
}
