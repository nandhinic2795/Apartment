package com.example.apartment;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class EigthActivity extends Activity {
	EditText ContactId,EventName,Invitation;
	Button btneAdd,btneup,btnev,btneviewall;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eigth);
		  final Calendar c=Calendar.getInstance();
	        final int year=c.get(Calendar.YEAR);
	        final int month=c.get(Calendar.MONTH);
	        final int day=c.get(Calendar.DAY_OF_MONTH);
	        final int hour=c.get(Calendar.HOUR_OF_DAY);
	        final int minute=c.get(Calendar.MINUTE);
	        final EditText txtDate=(EditText)findViewById(R.id.edtDate);
	        final EditText txtTime=(EditText)findViewById(R.id.edtTime);
	      ContactId=(EditText)findViewById(R.id.txteid);
		EventName=(EditText)findViewById(R.id.txtename);
		Invitation=(EditText)findViewById(R.id.txteinv);
		btneAdd=(Button)findViewById(R.id.btnesub);
		btneup=(Button)findViewById(R.id.btnemod);
		btnev=(Button)findViewById(R.id.btnev);
		btneviewall=(Button)findViewById(R.id.btnevall);
		db=openOrCreateDatabase("SOCIETYDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS EvtTable( ContactId VARCHAR ,EventName VARCHAR,Date VARCHAR,Time VARCHAR,Invitation VARCHAR );");
		        btneAdd.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {	
						if((ContactId.getText().toString().trim().length()==0)||
								(EventName.getText().toString().trim().length()==0)||
								(txtDate.getText().toString().trim().length()==0)||
								(txtTime.getText().toString().trim().length()==0)||
								(Invitation.getText().toString().trim().length()==0))
							
						{
							Toast.makeText(EigthActivity.this, "Please Enter all values", Toast.LENGTH_LONG).show();
							//showMessage("Error","Please Enter all values" );
						clearText();
							
						}
						else
						{
		        		db.execSQL("INSERT INTO EvtTable VALUES('"+ContactId.getText()+ "','"+EventName.getText()+ "','"+txtDate.getText()+"','"+txtTime.getText()+"','"+Invitation.getText()+"');");
		        		Toast.makeText(EigthActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
		        		clearText();
					}
					}
					private void clearText() {
						ContactId.setText("");
			           	EventName.setText("");
			        	txtDate.setText("");
			        	txtTime.setText("");
			           	Invitation.setText("");
			        	
						// TODO Auto-generated method stub
						
					}
					
				//private void showMessage(String string, String string2) 
						//{
					//	}
						// TODO Auto-generated method stub
						
					}
		        		);
		       
			    
 btneup.setOnClickListener(new OnClickListener() {
	      			
	      			@Override
	      			public void onClick(View arg0) {
	      				// TODO Auto-generated method stub
	      				if(ContactId.getText().toString().trim().length()==0)
	      	    		{
	      	    			showMessage("Error", "Please enter Name");
	      	    			return;
	      	    		}
	      	    		Cursor c=db.rawQuery("SELECT * FROM EvtTable WHERE ContactId='"+ContactId.getText()+"'", null);
	      	    		if(c.moveToFirst())
	      	    		{
	      	    			db.execSQL("UPDATE EvtTable SET ContactId='"+ContactId.getText()+"',EventName='"+EventName.getText()+"',Date='"+txtDate.getText()+
	      	    					"',Time='"+txtTime.getText()+"',Invitation='"+Invitation.getText()+"' WHERE ContactId='"+ContactId.getText()+"'");
	      	    			showMessage("Success", "Record Modified");
	      	    		}
	      	    		else
	      	    		{
	      	    			showMessage("Error", "Invalid");
	      	    		}
	      	    			
	      			}
	      			 
	      			
	      		});
 btnev.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(ContactId.getText().toString().trim().length()==0)
 		{
 			showMessage("Error", "Please enter Name");
 			return;
 		}
 		Cursor c=db.rawQuery("SELECT * FROM EvtTable WHERE ContactId='"+ContactId.getText()+"'", null);
 		if(c.moveToFirst())
 		{
 		
 			EventName.setText(c.getString(1));
 			txtDate.setText(c.getString(2));
 			txtTime.setText(c.getString(3));
 			Invitation.setText(c.getString(4));
 			
	    		
 		}
 		else
 		{
 			showMessage("Error", "Invalid Name");
 			clearText();
 		}	
		}

		private void clearText() {
			ContactId.setText("");
        	EventName.setText("");
     	    txtDate.setText("");
     	    txtTime.setText("");
        	Invitation.setText("");
     	
	
			// TODO Auto-generated method stub
			
		}

		private void showMessage(String string, String string2) {
			
	
			
		}
	});	
 btneviewall.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
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
    			buffer.append("Invitation: "+c.getString(4)+"\n");
    			buffer.append("____________________________\n");
    		}
    		showMessage("Your Details", buffer.toString());	
			
		}
	});
 txtTime.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			TimePickerDialog timepick=new TimePickerDialog(EigthActivity.this,new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
					// TODO Auto-generated method stub
				txtTime.setText(arg1+":"+arg2);
				}
			} ,hour,minute,false
			);
			timepick.setTitle("Select Time");
			timepick.show();
		}
	});
 txtDate.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			DatePickerDialog datepicker=new DatePickerDialog(EigthActivity.this,new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					txtDate.setText(arg1+"-"+arg2+"-"+arg3);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eigth, menu);
		return true;
	}

}
