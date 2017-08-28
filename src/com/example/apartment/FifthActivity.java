package com.example.apartment;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FifthActivity extends Activity {
	EditText tname,tpho,tid,tdsgn,tfm,tbn,tfno;
	Button btnAdd,btnview,btnDelete,btnClr,btnoup,btnvw;
	SQLiteDatabase db;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);

		tname=(EditText)findViewById(R.id.txttname);
		tpho=(EditText)findViewById(R.id.txttpno);
		tid=(EditText)findViewById(R.id.txttmid);
		tdsgn=(EditText)findViewById(R.id.txttdesn);
		tfm=(EditText)findViewById(R.id.txttfm);
		tbn=(EditText)findViewById(R.id.txttblkn);
		tfno=(EditText)findViewById(R.id.txttfno);
		btnAdd=(Button)findViewById(R.id.btntadd);
		btnview=(Button)findViewById(R.id.btnsup);
		btnvw=(Button)findViewById(R.id.btntva);
		btnDelete=(Button)findViewById(R.id.btntdel);
		btnClr=(Button)findViewById(R.id.btnsvw);
		btnoup=(Button)findViewById(R.id.btnsclr);
		
		db=openOrCreateDatabase("ADMINDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS TenantTable( tname VARCHAR ,tpho VARCHAR,tid VARCHAR,tdsgn VARCHAR,tfm VARCHAR,tbn VARCHAR,tfno VARCHAR );");
		        btnAdd.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {	
						if((tname.getText().toString().trim().length()==0)||
								(tpho.getText().toString().trim().length()==0)||
								(tid.getText().toString().trim().length()==0)||
								(tdsgn.getText().toString().trim().length()==0)||
								(tfm.getText().toString().trim().length()==0)||
								(tbn.getText().toString().trim().length()==0)||
							    (tfno.getText().toString().trim().length()==0))
						{
							Toast.makeText(FifthActivity.this, "Please Enter all values", Toast.LENGTH_LONG).show();
						}
						else if(isValidPhone(tpho.getText().toString())){
							Toast.makeText(getApplicationContext(),"Phone number is valid",Toast.LENGTH_SHORT).show();
						}
							else{
							Toast.makeText(getApplicationContext(),"Phone number is not valid",Toast.LENGTH_SHORT).show();	 
						}
		        		db.execSQL("INSERT INTO TenantTable VALUES('"+tname.getText()+ "','"+tpho.getText()+ "','"+tid.getText()+"','"+tdsgn.getText()+"','"+tfm.getText()+"','"+tbn.getText()+"','"+tfno.getText()+"');");
		        		Toast.makeText(FifthActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
						
					}
				
						
						// TODO Auto-generated method stub
						
					
					
				//private void showMessage(String string, String string2) 
						//{
					//	}
						// TODO Auto-generated method stub
						
					}
		        		);
		          
		        
		        btnview.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if(tname.getText().toString().trim().length()==0)
			    		{
			    			showMessage("Error", "Please enter Name");
			    			return;
			    		}
			    		Cursor c=db.rawQuery("SELECT * FROM TenantTable WHERE tname='"+tname.getText()+"'", null);
			    		if(c.moveToFirst())
			    		{
			    		
			    			tpho.setText(c.getString(1));
			    			tid.setText(c.getString(2));
			    			tdsgn.setText(c.getString(3));
			    			tfm.setText(c.getString(4));
			    			tbn.setText(c.getString(5));
			    			tfno.setText(c.getString(6));
				    		
			    		}
			    		else
			    		{
			    			showMessage("Error", "Invalid Name");
			    			clearText();
			    		}	
					}

					private void clearText() {
						tname.setText("");
			           	tpho.setText("");
			        	tid.setText("");
			        	tdsgn.setText("");
			           	tfm.setText("");
			        	tbn.setText("");
			        	tfno.setText("");
				
						// TODO Auto-generated method stub
						
					}

					private void showMessage(String string, String string2) {
						
				
						
					}
				});	
		        btnvw.setOnClickListener(new OnClickListener() {
		   			
		   			@Override
		   			public void onClick(View arg0) {
		   				// TODO Auto-generated method stub
		   				Cursor c=db.rawQuery("SELECT * FROM TenantTable", null);
		   	    		if(c.getCount()==0)
		   	    		{
		   	    			showMessage("Error", "No records found");
		   	    			return;
		   	    		}
		   	    		StringBuffer buffer=new StringBuffer();
		   	    		while(c.moveToNext())
		   	    		{
		   	    			buffer.append("Name: "+c.getString(0)+"\n");
		   	    			buffer.append("Phone Number: "+c.getString(1)+"\n");
		   	    			buffer.append("Mail ID: "+c.getString(2)+"\n");
		   	    			buffer.append("Designation: "+c.getString(3)+"\n");
		   	    			buffer.append("Family Members: "+c.getString(4)+"\n");
		   	    			buffer.append("Block Name: "+c.getString(5)+"\n");
		   	    			buffer.append("Flat No: "+c.getString(6)+"\n");
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
			
			 btnDelete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						if(tname.getText().toString().trim().length()==0)
			    		{
			    			showMessage("Error", "Please enter Name");
			    			return;
			    		}
			    		Cursor c=db.rawQuery("SELECT * FROM TenantTable WHERE tname='"+tname.getText()+"'", null);
			    		if(c.moveToFirst())
			    		{
			    			db.execSQL("DELETE FROM TenantTable WHERE tname='"+tname.getText()+"'");
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
		        
	           btnClr.setOnClickListener(new OnClickListener() {
					

					@Override
					public void onClick(View arg0) {
						tname.setText("");
			           	tpho.setText("");
			        	tid.setText("");
			        	tdsgn.setText("");
			           	tfm.setText("");
			        	tbn.setText("");
			        	tfno.setText("");
						
					}
						// TODO Auto-generated method stub
						
					});
	           btnoup.setOnClickListener(new OnClickListener() {
	      			
	      			@Override
	      			public void onClick(View arg0) {
	      				// TODO Auto-generated method stub
	      				if(tname.getText().toString().trim().length()==0)
	      	    		{
	      	    			showMessage("Error", "Please enter Name");
	      	    			return;
	      	    		}
	      	    		Cursor c=db.rawQuery("SELECT * FROM TenantTable WHERE tname='"+tname.getText()+"'", null);
	      	    		if(c.moveToFirst())
	      	    		{
	      	    			db.execSQL("UPDATE TenantTable SET tpho='"+tpho.getText()+"',tid='"+tid.getText()+"',tdsgn='"+tdsgn.getText()+
	      	    					"',tfm='"+tfm.getText()+"',tbn='"+tbn.getText()+"',tfno='"+tfno.getText()+"' WHERE tname='"+tname.getText()+"'");
	      	    			showMessage("Success", "Record Modified");
	      	    		}
	      	    		else
	      	    		{
	      	    			showMessage("Error", "Invalid");
	      	    		}
	      	    			
	      			}
	      			
	      		});

		    }
			private boolean isValidPhone(String phone)
			{
			    boolean check=false;
			    if(!Pattern.matches("[a-zA-Z]+", phone))
			    {
			        if(phone.length() < 6 || phone.length() > 13)
			        {
			            check = false;
			 
			        }
			        else
			        {
			            check = true;
			 
			        }
			    }
			    else
			    {
			        check=false;
			    }
			    return check;
			}
			public boolean isValidPhone(CharSequence phone) {
				if(TextUtils.isEmpty(phone)) {
				return false;
				} else{
				return android.util.Patterns.PHONE.matcher(phone).matches();
				}
				}
				
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fifth, menu);
		return true;
	}

}
