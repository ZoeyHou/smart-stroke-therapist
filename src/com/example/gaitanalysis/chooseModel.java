package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class chooseModel extends ActionBarActivity {
	String minute;
	String pattern;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_layout);
		Intent intent=getIntent();
		final String patient_id=intent.getStringExtra("patient_id");
		Button next=(Button)findViewById(R.id.button1);
		next.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("patient_id",patient_id);
				intent.putExtra ("minute",minute);
				intent.putExtra("pattern",pattern);
				intent.setClass(chooseModel.this,pretest.class);
				startActivity(intent);
			}
		});		
		RadioButton twoMinute=(RadioButton)findViewById(R.id.radioButton2);
		RadioButton.OnClickListener twoMinuteListener=new RadioButton.OnClickListener(){
			@Override
			public void onClick(View v){
			    minute="two";
			}
		};
		twoMinute.setOnClickListener(twoMinuteListener);
		RadioButton fiveMinute=(RadioButton)findViewById(R.id.radioButton3);
		RadioButton.OnClickListener fiveMinuteListener=new RadioButton.OnClickListener(){
			@Override
			public void onClick(View v){
				minute="five";
			}
		};
		fiveMinute.setOnClickListener(fiveMinuteListener);
		RadioButton timePattern=(RadioButton)findViewById(R.id.radioButton1);
		RadioButton.OnClickListener timePatternListener=new RadioButton.OnClickListener(){
			@Override
			public void onClick(View v){
				pattern="time";
			}
		};
		timePattern.setOnClickListener(timePatternListener);
		RadioButton stepPattern=(RadioButton)findViewById(R.id.radioButton4);
		RadioButton.OnClickListener stepPatternListener=new RadioButton.OnClickListener(){
			@Override
			public void onClick(View v){
				pattern="step";
			}
		};
		stepPattern.setOnClickListener(stepPatternListener);
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
			Intent intent = new Intent(chooseModel.this, HomePage2.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
