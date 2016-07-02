package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class HomePage2 extends ActionBarActivity {

	String patient_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page2);
        
		// getting patient details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        patient_id = i.getStringExtra("patient_id");
        
        Button personInfo=(Button)findViewById(R.id.button6);
		personInfo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i1 = new Intent();
		        i1.setClass(HomePage2.this, PersonalSetting.class);
		        startActivity(i1);
			}
		});
		
		Button personSetting=(Button)findViewById(R.id.button4);
		personSetting.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// getting patient details from intent
		        /*Intent i = getIntent();
		        // getting patient id (pid) from intent
		        String patient_id = i.getStringExtra("patient_id");*/
		        Intent i1 = new Intent();
		        i1.setClass(HomePage2.this, PatInfoMod.class);
		        startActivity(i1);
			}
		});
		
		Button test=(Button)findViewById(R.id.button1);
		test.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomePage2.this,selectLengthType.class);
				startActivity(intent);
			}
		});
		
		Button logOff=(Button)findViewById(R.id.button7);
		logOff.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomePage2.this,HomePage.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
