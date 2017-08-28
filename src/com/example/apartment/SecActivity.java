package com.example.apartment;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



	
	
	
	
	public class SecActivity extends Activity {
		Button b1;
		EditText et1,et2;
		String uname,pwd;

			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_sec);
				b1=(Button)findViewById(R.id.btnoAd);
				et1=(EditText)findViewById(R.id.user);
				et2=(EditText)findViewById(R.id.psd);
				b1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						 uname=et1.getText().toString();
						 pwd=et2.getText().toString(); 
					if(et1.getText().toString().trim().length()==0||
							et2.getText().toString().trim().length()==0)
					{
						Toast.makeText(getApplicationContext(), "Enter your username and password",Toast.LENGTH_SHORT).show();
					}
					else if(uname.equals("Admin") && pwd.equals("123"))
					{
						Intent i=new Intent(SecActivity.this,ThirdActivity.class);
						startActivity(i);
					}
					else if((uname.equals("TenantA01") && pwd.equals("a11"))||(uname.equals("TenantB01") && pwd.equals("b11")))
					{
						Intent i=new Intent(SecActivity.this,ThirteenActivity.class);
						startActivity(i);
					}
					else if(uname.equals("3") && pwd.equals("c"))
					{
						Intent i=new Intent(SecActivity.this,TweleActivity.class);
						startActivity(i);
					}
					
					else
					{
						Toast.makeText(getApplicationContext(), "Invalid username and password",Toast.LENGTH_SHORT).show();
					}
					
				
			}
			
			});
	
			}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sec, menu);
		return true;
	}
	}

	
	
	
	
	
	
	

