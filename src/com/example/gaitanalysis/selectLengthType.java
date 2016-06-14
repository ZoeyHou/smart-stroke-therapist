package com.example.gaitanalysis;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class selectLengthType extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.select_layout);
	}
}
