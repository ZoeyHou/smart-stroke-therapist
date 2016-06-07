package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Logging extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 //  requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_logging);
		
		//分别获取登录、注册text、button
		final EditText usernameTxt= (EditText) findViewById(R.id.accountInput);
		final EditText pwdText = (EditText) findViewById(R.id.pwdInput);
		Button login = (Button) findViewById(R.id.button1);
		Button registerLogin = (Button) findViewById(R.id.button2);
		final String usernameString = usernameTxt.getText().toString();
		final String pwdString = pwdText.getText().toString();
		
		
		
		/*登录注册不离家，我看见了就一起copy了，到时谁负责这一块，谁改改~
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final EditText usernameTxt= 
						(EditText) findViewById(R.id.editText5);
				final EditText pwdTxt = 
						(EditText) findViewById(R.id.editText6);
				Button login = (Button) findViewById(R.id.button3);
				Button registerLogin = (Button) findViewById(R.id.button4);
				final String usernameString = 
						usernameTxt.getText().toString();
				final String pwdString = pwdTxt.getText().toString();
				

				if((!"".equals(usernameString))
						&&(!"".equals(pwdString))){
					DBService dbService = 
							new DBService(LoginActivity.this);
					boolean flag = 
							dbService.login(usernameString, pwdString);
					if(flag){
						Intent intent = new Intent(LoginActivity.this, 
								HomeActivity.class);
						startActivity(intent);
						finish();
					}else{
						Toast.makeText(LoginActivity.this, 
								"您输入的用户名或密码有误，请重新输入！", Toast.LENGTH_SHORT).show();
						pwdTxt.setText("");
						usernameTxt.setText("");
					}
				}else{
					Toast.makeText(LoginActivity.this, 
							"您输入的信息不完整！", Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		*/
		
		registerLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Logging.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logging, menu);
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
