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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NinethActivity extends Activity {
	Spinner spin1,spin2;
	ArrayAdapter<CharSequence> adapter;
	EditText Workerid,WorkerName,ContactNo;
	Button btnmAdd,btnmview,btnmDelete,btnmClr,btnmoup,btnmvw;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nineth);
		Workerid=(EditText)findViewById(R.id.txtmid);
		WorkerName=(EditText)findViewById(R.id.txtmname);
		ContactNo=(EditText)findViewById(R.id.txtmcno);
		spin1=(Spinner)findViewById(R.id.SpinTypeWork);
		adapter=ArrayAdapter.createFromResource(this,R.array.Typeofwork_arrays, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(adapter);
		spin2=(Spinner)findViewById(R.id.SpinWorkTime);
		adapter=ArrayAdapter.createFromResource(this,R.array.Worktime_arrays, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin2.setAdapter(adapter);
		btnmAdd=(Button)findViewById(R.id.btnmadd);
		btnmview=(Button)findViewById(R.id.btnmview);
		btnmvw=(Button)findViewById(R.id.btnmva);
		btnmDelete=(Button)findViewById(R.id.btnmdel);
		btnmClr=(Button)findViewById(R.id.btnmclr);
		btnmoup=(Button)findViewById(R.id.btnmup);
		
		db=openOrCreateDatabase("SOCIETYDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS MainTable( Workerid VARCHAR ,WorkerName VARCHAR,ContactNo VARCHAR,TypeOfWork VARCHAR,WorkingTime VARCHAR);");
		spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			
				Toast.makeText(getBaseContext(), arg0.getItemAtPosition(arg2)+" selected", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
		spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			
				Toast.makeText(getBaseContext(), arg0.getItemAtPosition(arg2)+" selected", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
		 btnmAdd.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {	
					if((Workerid.getText().toString().trim().length()==0)||
							(WorkerName.getText().toString().trim().length()==0)||
							(ContactNo.getText().toString().trim().length()==0)||
							(spin1.getSelectedItem().toString().trim().length()==0)||
							(spin2.getSelectedItem().toString().trim().length()==0))
					{
						Toast.makeText(NinethActivity.this, "Please Enter all values", Toast.LENGTH_LONG).show();
						//showMessage("Error","Please Enter all values" );
					clearText();
						
					}
	        		db.execSQL("INSERT INTO MainTable VALUES('"+Workerid.getText()+ "','"+WorkerName.getText()+ "','"+ContactNo.getText()+"','"+spin1.getSelectedItem()+"','"+spin2.getSelectedItem()+"');");
	        		Toast.makeText(NinethActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
	        		clearText();
				}
			
				private void clearText() {
					Workerid.setText("");
		           	WorkerName.setText("");
		        	ContactNo.setText("");
		       
		        	
					// TODO Auto-generated method stub
					
				}
				
			//private void showMessage(String string, String string2) 
					//{
				//	}
					// TODO Auto-generated method stub
					
				}
	        		);
	          
	        
	        btnmview.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(Workerid.getText().toString().trim().length()==0)
		    		{
		    			showMessage("Error", "Please enter Name");
		    			return;
		    		}
		    		Cursor c=db.rawQuery("SELECT * FROM MainTable WHERE Workerid='"+Workerid.getText()+"'", null);
		    		if(c.moveToFirst())
		    		{
		    		
		    			WorkerName.setText(c.getString(1));
		    			ContactNo.setText(c.getString(2));
		    			spin1.setTag(c.getString(3));
		    			spin2.setTag(c.getString(4));
		    			
		    		}
		    		else
		    		{
		    			showMessage("Error", "Invalid Name");
		    			clearText();
		    		}	
				}

				private void clearText() {
					
					
					Workerid.setText("");
		           	WorkerName.setText("");
		        	ContactNo.setText("");
		        	// TODO Auto-generated method stub
					
				}

				private void showMessage(String string, String string2) {
					
			
					
				}
			});	
	        btnmvw.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View arg0) {
	   				// TODO Auto-generated method stub
	   				Cursor c=db.rawQuery("SELECT * FROM MainTable", null);
	   	    		if(c.getCount()==0)
	   	    		{
	   	    			showMessage("Error", "No records found");
	   	    			return;
	   	    		}
	   	    		StringBuffer buffer=new StringBuffer();
	   	    		while(c.moveToNext())
	   	    		{
	   	    			buffer.append("Workerid: "+c.getString(0)+"\n");
	   	    			buffer.append("WorkerName: "+c.getString(1)+"\n");
	   	    			buffer.append("ContactNo: "+c.getString(2)+"\n");
	   	    			buffer.append("TypeOfWork: "+c.getString(3)+"\n");
	   	    			buffer.append("WorkingTime: "+c.getString(4)+"\n");
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
		
		 btnmDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					if(Workerid.getText().toString().trim().length()==0)
		    		{
		    			showMessage("Error", "Please enter Name");
		    			return;
		    		}
		    		Cursor c=db.rawQuery("SELECT * FROM MainTable WHERE Workerid='"+Workerid.getText()+"'", null);
		    		if(c.moveToFirst())
		    		{
		    			db.execSQL("DELETE FROM MainTable WHERE Workerid='"+Workerid.getText()+"'");
		    			showMessage("Success", "Record Deleted");
		    		}
		    		else
		    		{
		    			showMessage("Error", "Invalid");
		    		}
		    	
				
				}

				private void showMessage(String string, String string2) {
					// TODO Auto-generated method stub
					
				}
			});
	        
        btnmClr.setOnClickListener(new OnClickListener() {
				

				@Override
				public void onClick(View arg0) {
					Workerid.setText("");
		           	WorkerName.setText("");
		        	ContactNo.setText("");
		        
					
				}
					// TODO Auto-generated method stub
					
				});
        btnmoup.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View arg0) {
   				// TODO Auto-generated method stub
   				if(Workerid.getText().toString().trim().length()==0)
   	    		{
   	    			showMessage("Error", "Please enter Name");
   	    			return;
   	    		}
   	    		Cursor c=db.rawQuery("SELECT * FROM MainTable WHERE Workerid='"+Workerid.getText()+"'", null);
   	    		if(c.moveToFirst())
   	    		{
   	    			db.execSQL("UPDATE MainTable SET ='"+WorkerName.getText()+"',ContactNo='"+ContactNo.getText()+"',spin1='"+spin1.getSelectedItem()+
   	    					"',spin2='"+spin2.getSelectedItem()+"' WHERE Workerid='"+Workerid.getText()+"'");
   	    			showMessage("Success", "Record Modified");
   	    		}
   	    		else
   	    		{
   	    			showMessage("Error", "Invalid");
   	    		}
   	    			
   			}
   			
   		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nineth, menu);
		return true;
	}
	

}
