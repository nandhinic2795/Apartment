package com.example.apartment;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class SeventhActivity extends Activity {
	Spinner spinner;
	ArrayAdapter<CharSequence> adapter;
	EditText cid,pn,FacilityName;
	Button btnfsub,btnfva,btncheck;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seventh);
		 final Calendar c=Calendar.getInstance();
	        final int year=c.get(Calendar.YEAR);
	        final int month=c.get(Calendar.MONTH);
	        final int day=c.get(Calendar.DAY_OF_MONTH);
	        final int hour=c.get(Calendar.HOUR_OF_DAY);
	        final int minute=c.get(Calendar.MINUTE);
	        final EditText date=(EditText)findViewById(R.id.txtfacdate);
	        final EditText time=(EditText)findViewById(R.id.txtfactime);
		spinner=(Spinner)findViewById(R.id.SpinFacName);
		adapter=ArrayAdapter.createFromResource(this,R.array.facility_arrays, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		cid=(EditText)findViewById(R.id.txtfcp);
		pn=(EditText)findViewById(R.id.txtfpn);
		
		btnfsub=(Button)findViewById(R.id.btnfsub);
		btnfva=(Button)findViewById(R.id.btnmclr);
		btncheck=(Button)findViewById(R.id.btnesub);
		
		db=openOrCreateDatabase("TENANTDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS FacTable( cid VARCHAR ,pn VARCHAR,FacilityName VARCHAR,date VARCHAR,time VARCHAR);");
		        btnfsub.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {	
						if((cid.getText().toString().trim().length()==0)||
								(pn.getText().toString().trim().length()==0)||
								(spinner.getSelectedItem().toString().trim().length()==0)||
								(date.getText().toString().trim().length()==0)||
								(time.getText().toString().trim().length()==0))
							
						{
							Toast.makeText(SeventhActivity.this, "Please Enter all values", Toast.LENGTH_LONG).show();
							//showMessage("Error","Please Enter all values" );
						clearText();
							
						}
		        		db.execSQL("INSERT INTO FacTable VALUES('"+cid.getText()+ "','"+pn.getText()+ "','"+spinner.getSelectedItem()+"','"+date.getText()+"','"+time.getText()+"');");
		        		Toast.makeText(SeventhActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
		        		clearText();
					}
				
					private void clearText() {
						cid.setText("");
			           	pn.setText("");
			           	date.setText("");
			           	time.setText("");
			        	
						// TODO Auto-generated method stub
						
					}
					
				//private void showMessage(String string, String string2) 
						//{
					//	}
						// TODO Auto-generated method stub
						
					}
		        		);
  btnfva.setOnClickListener(new OnClickListener() {
		   			
		   			@Override
		   			public void onClick(View arg0) {
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
  time.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			TimePickerDialog timepick=new TimePickerDialog(SeventhActivity.this,new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
					// TODO Auto-generated method stub
				time.setText(arg1+":"+arg2);
				}
			} ,hour,minute,false
			);
			timepick.setTitle("Select Time");
			timepick.show();
		}
	});
date.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			DatePickerDialog datepicker=new DatePickerDialog(SeventhActivity.this,new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					date.setText(arg1+"-"+arg2+"-"+arg3);
				}
			},year,month,day
			);
			datepicker.setTitle("Select Date");
			datepicker.show();
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
	
		        
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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
		
		
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seventh, menu);
		return true;
	}

}
