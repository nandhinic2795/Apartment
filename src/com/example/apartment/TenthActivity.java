package com.example.apartment;


import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TenthActivity extends Activity {
	EditText asname,mid,sid,sname,sphno,mailid;
	Button btnAdd,btnview,btnDelete,btnClr,btnup,btnviewall;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tenth);
		asname=(EditText)findViewById(R.id.txtsan);
		mid=(EditText)findViewById(R.id.txtsmi);
		sid=(EditText)findViewById(R.id.txtssi);
		sname=(EditText)findViewById(R.id.txtsn);
		sphno=(EditText)findViewById(R.id.txtspn);
		mailid=(EditText)findViewById(R.id.txtsi);
		btnAdd=(Button)findViewById(R.id.btnsad);
		btnview=(Button)findViewById(R.id.btnsv);
		btnDelete=(Button)findViewById(R.id.btnsdel);
		btnClr=(Button)findViewById(R.id.btnsclr);
		btnup=(Button)findViewById(R.id.btnsup);
		btnviewall=(Button)findViewById(R.id.btnsvw);
		db=openOrCreateDatabase("ADMINDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS SocietyTable( asname VARCHAR ,mid VARCHAR,sid VARCHAR,sname VARCHAR,sphno VARCHAR,mailid VARCHAR );");
		        btnAdd.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {	
						if((asname.getText().toString().trim().length()==0)||
								(mid.getText().toString().trim().length()==0)||
								(sid.getText().toString().trim().length()==0)||
								(sname.getText().toString().trim().length()==0)||
								(sphno.getText().toString().trim().length()==0)||
								(mailid.getText().toString().trim().length()==0))
								
						{
							showMessage("Error","Please Enter all values" );
						clearText();
							
						}
		        		db.execSQL("INSERT INTO SocietyTable VALUES('"+asname.getText()+ "','"+mid.getText()+ "','"+sid.getText()+"','"+sname.getText()+"','"+sphno.getText()+"','"+mailid.getText()+"');");
		        		Toast.makeText(TenthActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
		        		clearText();
					}
				
					private void showMessage(String string, String string2) {
						// TODO Auto-generated method stub
						
					}

					private void clearText() {
						asname.setText("");
			           	mid.setText("");
			        	sid.setText("");
			        	sname.setText("");
			           	sphno.setText("");
			        	mailid.setText("");
				
						// TODO Auto-generated method stub
						
					}
					
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
						if(asname.getText().toString().trim().length()==0)
			    		{
			    			showMessage("Error", "Please enter Name");
			    			return;
			    		}
			    		Cursor c=db.rawQuery("SELECT * FROM SocietyTable WHERE asname='"+asname.getText()+"'", null);
			    		if(c.moveToFirst())
			    		{
			    		
			    			mid.setText(c.getString(1));
			    			sid.setText(c.getString(2));
			    			sname.setText(c.getString(3));
			    			sphno.setText(c.getString(4));
			    			mailid.setText(c.getString(5));
			    		}
			    		else
			    		{
			    			showMessage("Error", "Invalid Name");
			    			clearText();
			    		}	
					}

					private void clearText() {
						asname.setText("");
			           	mid.setText("");
			        	sid.setText("");
			        	sname.setText("");
			           	sphno.setText("");
			        	mailid.setText("");
				
						// TODO Auto-generated method stub
						
					}

					private void showMessage(String string, String string2) {
						
				
						
					}
				});	
		        btnDelete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						if(asname.getText().toString().trim().length()==0)
			    		{
			    			showMessage("Error", "Please enter Name");
			    			return;
			    		}
			    		Cursor c=db.rawQuery("SELECT * FROM SocietyTable WHERE asname='"+asname.getText()+"'", null);
			    		if(c.moveToFirst())
			    		{
			    			db.execSQL("DELETE FROM SocietyTable WHERE asname='"+asname.getText()+"'");
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
						asname.setText("");
			           	mid.setText("");
			        	sid.setText("");
			        	sname.setText("");
			           	sphno.setText("");
			        	mailid.setText("");
				
						
				
					}
						// TODO Auto-generated method stub
						
					});
	           btnup.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View arg0) {
	   				// TODO Auto-generated method stub
	   				if(asname.getText().toString().trim().length()==0)
	   	    		{
	   	    			showMessage("Error", "Please enter Name");
	   	    			return;
	   	    		}
	   	    		Cursor c=db.rawQuery("SELECT * FROM SocietyTable WHERE asname='"+asname.getText()+"'", null);
	   	    		if(c.moveToFirst())
	   	    		{
	   	    			db.execSQL("UPDATE SocietyTable SET mid='"+mid.getText()+"',sid='"+sid.getText()+"',sname='"+sname.getText()+
	   	    					"',sphno='"+sphno.getText()+"',mailid='"+mailid.getText()+"'WHERE asname='"+asname.getText()+"'");
	   	    			showMessage("Success", "Record Modified");
	   	    		}
	   	    		else
	   	    		{
	   	    			showMessage("Error", "Invalid");
	   	    		}
	   	    			
	   			}
	   			
	   		});
	           btnviewall.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View arg0) {
	   				// TODO Auto-generated method stub
	   				Cursor c=db.rawQuery("SELECT * FROM SocietyTable", null);
	   	    		if(c.getCount()==0)
	   	    		{
	   	    			showMessage("Error", "No records found");
	   	    			return;
	   	    		}
	   	    		StringBuffer buffer=new StringBuffer();
	   	    		while(c.moveToNext())
	   	    		{
	   	    			buffer.append("Association Member: "+c.getString(0)+"\n");
	   	    			buffer.append("Member Id: "+c.getString(1)+"\n");
	   	    			buffer.append("Secretary Id: "+c.getString(2)+"\n");
	   	    			buffer.append("Secretary Name: "+c.getString(3)+"\n");
	   	    			buffer.append("Phone Number: "+c.getString(4)+"\n");
	   	    			buffer.append("Mail Id: "+c.getString(5)+"\n");
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
		getMenuInflater().inflate(R.menu.tenth, menu);
		return true;
	}

}
