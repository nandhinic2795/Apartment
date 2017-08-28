package com.example.apartment;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

public class SixthActivity extends Activity {
	
	Spinner spinner;
	ArrayAdapter<CharSequence> adapter;
	EditText cnpid,cnpname,TypeOfComplaint,desc;
	Button btnsub;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sixth);
		cnpid=(EditText)findViewById(R.id.txtccpi);
		cnpname=(EditText)findViewById(R.id.txtfpn);
		desc=(EditText)findViewById(R.id.txtcd);
		btnsub=(Button)findViewById(R.id.btncsub);
		 final EditText txtcdate=(EditText)findViewById(R.id.txtcompdate);
		  final Calendar c=Calendar.getInstance();
	        final int year=c.get(Calendar.YEAR);
	        final int month=c.get(Calendar.MONTH);
	        final int day=c.get(Calendar.DAY_OF_MONTH);
	
		spinner=(Spinner)findViewById(R.id.SpinTypeComp);
		adapter=ArrayAdapter.createFromResource(this,R.array.complaint_arrays, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		db=openOrCreateDatabase("TENANTDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS ComplTable( cnpid VARCHAR ,Date varchar,cnpname VARCHAR,TypeOfComplaint VARCHAR,desc VARCHAR);");
		 btnsub.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {	
					if((cnpid.getText().toString().trim().length()==0)||
							(txtcdate.getText().toString().trim().length()==0)||
							(cnpname.getText().toString().trim().length()==0)||
							(spinner.getSelectedItem().toString().trim().length()==0)||
							(desc.getText().toString().trim().length()==0))
						
					{
						Toast.makeText(SixthActivity.this, "Please Enter all values", Toast.LENGTH_LONG).show();
					clearText();
						
					}
	        		db.execSQL("INSERT INTO ComplTable VALUES('"+cnpid.getText()+ "','"+txtcdate.getText()+ "','"+cnpname.getText()+ "','"+spinner.getSelectedItem()+"','"+desc.getText()+"');");
	        		Toast.makeText(SixthActivity.this, "Submit successfully", Toast.LENGTH_LONG).show();
	        		clearText();
	        		showdiag();
				}
			
				private void clearText() {
					cnpid.setText("");
					txtcdate.setText("");
		           	cnpname.setText("");
		        	spinner.setTag("");
		        	desc.setText("");
		           						
					// TODO Auto-generated method stub
					
				}
				
			//private void showMessage(String string, String string2) 
					//{
				//	}
					// TODO Auto-generated method stub
					
				}
	        		);
	        
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(getBaseContext(), arg0.getItemAtPosition(arg2)+" selected", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
		txtcdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DatePickerDialog datepicker=new DatePickerDialog(SixthActivity.this,new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
						// TODO Auto-generated method stub
						txtcdate.setText(arg1+"-"+arg2+"-"+arg3);
					}
				},year,month,day
				);
				datepicker.setTitle("Select Date");
				datepicker.show();
			}
		});

		 	  
	}
	
	public void showdiag(){
		AlertDialog.Builder mydiag=new AlertDialog.Builder(this);
		mydiag.setTitle("Alert");
		mydiag.setMessage("Your Problem solved by within 2 Days");
		mydiag.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		AlertDialog myalert=mydiag.create();
		myalert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sixth, menu);
		return true;
	}
	public void showMessage(String title,String message)
	  {
	  	Builder builder=new Builder(this);
	  	builder.setCancelable(true);
	  	builder.setTitle(title);
	  	builder.setMessage(message);
	  	builder.show();
	  }
}
