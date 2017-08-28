package com.example.apartment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class FourthActivity extends Activity {
	EditText oname,ophno,omlId,odesig,onoblk,onoflat;
	Button btnAdd,btnview,btnDelete,btnClr,btnoup,btnviewall;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);

		oname=(EditText)findViewById(R.id.txtoname);
		ophno=(EditText)findViewById(R.id.txtophno);
		omlId=(EditText)findViewById(R.id.txtomail);
		odesig=(EditText)findViewById(R.id.txtodesgn);
		onoblk=(EditText)findViewById(R.id.txtoblk);
		onoflat=(EditText)findViewById(R.id.txtoflat);
		btnAdd=(Button)findViewById(R.id.btnoAd);
		btnview=(Button)findViewById(R.id.btnoVw);
		btnDelete=(Button)findViewById(R.id.btndlt);
		btnClr=(Button)findViewById(R.id.btnclr);
		btnoup=(Button)findViewById(R.id.btnoup);
		btnviewall=(Button)findViewById(R.id.btncsub);
		
		db=openOrCreateDatabase("ADMINDB", Context.MODE_PRIVATE, null);
	db.execSQL("CREATE TABLE IF NOT EXISTS OwnerTable( oname VARCHAR ,ophno VARCHAR,omlId VARCHAR,odesig VARCHAR,onoblk VARCHAR,onoflat VARCHAR );");
	        btnAdd.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {	
					String email = omlId.getText().toString();

					if((oname.getText().toString().trim().length()==0)||
							(ophno.getText().toString().trim().length()==0)||
							(omlId.getText().toString().trim().length()==0)||
							(odesig.getText().toString().trim().length()==0)||
							(onoblk.getText().toString().trim().length()==0)||
							(onoflat.getText().toString().trim().length()==0))
						showMessage("Error","Please Enter all values" );
						else if(isValidEmail(email)){
		                    Toast.makeText(arg0.getContext(), "Email is valid", Toast.LENGTH_LONG).show();
						}
						else {
						
		                    Toast.makeText(arg0.getContext(), "Email is invalid", Toast.LENGTH_LONG).show();
						}     
							
					db.execSQL("INSERT INTO OwnerTable VALUES('"+oname.getText()+ "','"+ophno.getText()+ "','"+omlId.getText()+"','"+odesig.getText()+"','"+onoblk.getText()+"','"+onoflat.getText()+"');");
	        		Toast.makeText(FourthActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
					
				}
				});
	        
					
			//private void showMessage(String string, String string2) 
					//{
				//	}
					// TODO Auto-generated method stub
					
				
	        	
	        
	            
	          
	        
	        btnview.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(oname.getText().toString().trim().length()==0)
		    		{
		    			showMessage("Error", "Please enter Name");
		    			return;
		    		}
		    		Cursor c=db.rawQuery("SELECT * FROM OwnerTable WHERE oname='"+oname.getText()+"'", null);
		    		if(c.moveToFirst())
		    		{
		    		
		    			ophno.setText(c.getString(1));
		    			omlId.setText(c.getString(2));
		    			odesig.setText(c.getString(3));
		    			onoblk.setText(c.getString(4));
		    			onoflat.setText(c.getString(5));
		    		}
		    		else
		    		{
		    			showMessage("Error", "Invalid Name");
		    			clearText();
		    		}	
				}

				private void clearText() {
					oname.setText("");
		           	ophno.setText("");
		        	omlId.setText("");
		        	odesig.setText("");
		           	onoblk.setText("");
		        	onoflat.setText("");
			
					// TODO Auto-generated method stub
					
				}

				private void showMessage(String string, String string2) {
					
			
					
				}
			});	
	        btnDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					if(oname.getText().toString().trim().length()==0)
		    		{
		    			showMessage("Error", "Please enter Name");
		    			return;
		    		}
		    		Cursor c=db.rawQuery("SELECT * FROM OwnerTable WHERE oname='"+oname.getText()+"'", null);
		    		if(c.moveToFirst())
		    		{
		    			db.execSQL("DELETE FROM OwnerTable WHERE oname='"+oname.getText()+"'");
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
					oname.setText("");
		           	ophno.setText("");
		        	omlId.setText("");
		        	odesig.setText("");
		           	onoblk.setText("");
		        	onoflat.setText("");
			
				}
					// TODO Auto-generated method stub
					
				});
           btnoup.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View arg0) {
   				// TODO Auto-generated method stub
   				if(oname.getText().toString().trim().length()==0)
   	    		{
   	    			showMessage("Error", "Please enter Name");
   	    			return;
   	    		}
   	    		Cursor c=db.rawQuery("SELECT * FROM OwnerTable WHERE oname='"+oname.getText()+"'", null);
   	    		if(c.moveToFirst())
   	    		{
   	    			db.execSQL("UPDATE OwnerTable SET ophno='"+ophno.getText()+"',omlId='"+omlId.getText()+"',odesig='"+odesig.getText()+
   	    					"',onoblk='"+onoblk.getText()+"',onoflat='"+onoflat.getText()+"'WHERE oname='"+oname.getText()+"'");
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
   				Cursor c=db.rawQuery("SELECT * FROM OwnerTable", null);
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
   	    			buffer.append("No of Blocks: "+c.getString(4)+"\n");
   	    			buffer.append("No of Flats: "+c.getString(5)+"\n");
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
            


	 public static boolean isValidEmail(String email)
     {
         String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
         CharSequence inputString = email;
         Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(inputString);
         if(matcher.matches())
         {
             return true;
         }
         else
         {
             return false;
         }
     }

		
	








	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fourth, menu);
		return true;
	}
           }

