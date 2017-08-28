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

public class CmpmngActivity extends Activity {
     	Button btncv,btnvf;
     	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cmpmng);
		btncv=(Button)findViewById(R.id.btnvcmp);
		btnvf=(Button)findViewById(R.id.btnvfac);
		 btncv.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View arg0) {
	   				// TODO Auto-generated method stub
	   				db=openOrCreateDatabase("TENANTDB", Context.MODE_PRIVATE, null);
	   				Cursor c=db.rawQuery("SELECT * FROM ComplTable", null);
	   	    		if(c.getCount()==0)
	   	    		{
	   	    			showMessage("Error", "No records found");
	   	    			return;
	   	    		}
	   	    		StringBuffer buffer=new StringBuffer();
	   	    		while(c.moveToNext())
	   	    		{
	   	    			buffer.append("Flat Number: "+c.getString(0)+"\n");
	   	    			buffer.append("Date: "+c.getString(1)+"\n");
	   	    			buffer.append("ComplaintPerson: "+c.getString(2)+"\n");
	   	    			buffer.append("TyOfComplaint: "+c.getString(3)+"\n");
	   	    			buffer.append("Description: "+c.getString(4)+"\n");			   	    			
	   	    			buffer.append("____________________________\n");
	   	    		}
	   	    		showMessage("Your Details", buffer.toString());	
	   				
	   			}
	   			
	   		});
		 btnvf.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View arg0) {
	   				db=openOrCreateDatabase("TENANTDB", Context.MODE_PRIVATE, null);
	   				
	   				// TODO Auto-generated method stub
	   				Cursor c=db.rawQuery("SELECT * FROM FacTable", null);
	   	    		if(c.getCount()==0)
	   	    		{
	   	    			showMessage("Error", "No records found");
	   	    			return;
	   	    		}
	   	    		StringBuffer buffer=new StringBuffer();
	   	    		while(c.moveToNext())
	   	    		{
	   	    			buffer.append("Flat Number: "+c.getString(0)+"\n");
	   	    			buffer.append("Person Name: "+c.getString(1)+"\n");
	   	    			buffer.append("Facility Name: "+c.getString(2)+"\n");
	   	    			buffer.append("Date: "+c.getString(3)+"\n");
	   	    			buffer.append("Time: "+c.getString(4)+"\n");
	   	    			
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
		getMenuInflater().inflate(R.menu.cmpmng, menu);
		return true;
	}

}
