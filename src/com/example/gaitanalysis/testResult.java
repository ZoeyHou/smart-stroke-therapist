package com.example.gaitanalysis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class testResult extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		String patient_id=intent.getStringExtra("patient_id");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testing_layout);
	}
}
