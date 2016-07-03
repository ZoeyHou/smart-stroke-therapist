package com.example.gaitanalysis;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.content.Intent;

public class testing extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testing_layout);
		//get value from last activity
		Intent intent=getIntent();
		String patient_id=intent.getStringExtra("patient_id");
		String pattern=intent.getStringExtra("pattern");
        String minute=intent.getStringExtra("minute");
        if(pattern=="time"){
		//Start collecting data of sensors
        //time pattern
		Intent serviceIntent = new Intent();
		serviceIntent.putExtra("patient_id", patient_id);
		serviceIntent.putExtra("minute", minute);
		serviceIntent.setClass(this, dataCollectService.class);
		startService(serviceIntent);
        }
        else  if(pattern=="step"){
           //step pattern
        }
	}
}