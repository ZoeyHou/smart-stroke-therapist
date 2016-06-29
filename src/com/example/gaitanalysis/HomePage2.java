package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HomePage2 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page2);
        
        // getting patient details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        String patient_id = i.getStringExtra("patient_id");
        
        Intent i1 = new Intent();
        i1.putExtra("patient_id",patient_id);
        i1.setClass(HomePage2.this, PatInfoMod.class);
        startActivity(i1);
        Intent i2 = new Intent();
        i2.putExtra("patient_id",patient_id);
        i2.setClass(HomePage2.this, PersonalSetting.class);
        startActivity(i2);
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
