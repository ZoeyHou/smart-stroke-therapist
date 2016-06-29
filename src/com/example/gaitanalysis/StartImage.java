package com.example.gaitanalysis;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


public class StartImage extends ActionBarActivity {
	 // ActionBar actionBar=getSupportActionBar();
	  //actionBar.hide();
	private final int SPLASH_DISPLAY_LENGHT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar(); 
		actionBar.hide();
		setContentView(R.layout.activity_start_image);
		
		new Handler().postDelayed(new Runnable(){ 
			 
	         @Override
	         public void run() { 
	             Intent mainIntent = new Intent(StartImage.this,MainActivity.class); 
	             StartImage.this.startActivity(mainIntent); 
	             StartImage.this.finish(); 
	         } 
	             
	    }, SPLASH_DISPLAY_LENGHT); 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_image, menu);
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
