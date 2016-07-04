package com.example.gaitanalysis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class testResult extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		final String patient_id=intent.getStringExtra("patient_id");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testres_layout);
	Button next=(Button)findViewById(R.id.nextStep);
	next.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(testResult.this, ShowTestResult.class);
			intent.putExtra("patient_id", patient_id);
			startActivity(intent);
		}
	});		
	Button reTest=(Button)findViewById(R.id.reTest);
	reTest.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(testResult.this, chooseModel.class);
			intent.putExtra("patient_id", patient_id);
			startActivity(intent);
		}
	});	
	}
}
