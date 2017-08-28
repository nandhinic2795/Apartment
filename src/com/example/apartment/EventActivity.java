package com.example.apartment;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventActivity extends Activity {
	Button btnviewall;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		btnviewall=(Button)findViewById(R.id.btnViewEvt);
		btnviewall.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db=openOrCreateDatabase("SOCIETYDB", Context.MODE_PRIVATE, null);
				Cursor c=db.rawQuery("SELECT * FROM EvtTable", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("ContactId: "+c.getString(0)+"\n");
	    			buffer.append("EventName: "+c.getString(1)+"\n");
	    			buffer.append("Date: "+c.getString(2)+"\n");
	    			buffer.append("Time: "+c.getString(3)+"\n");
	    			buffer.append("Place: "+c.getString(4)+"\n");
	    			buffer.append("____________________________\n");
	    		}
	    		showMessage("Your Details", buffer.toString());	
				
			}
			
		});

	}
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

}
