package com.gait.test;

import com.example.gait.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class pretest extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pretest_layout);
	}
}
