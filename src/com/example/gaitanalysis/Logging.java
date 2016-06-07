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
		
		//�ֱ��ȡ��¼��ע��text��button
		final EditText usernameTxt= (EditText) findViewById(R.id.accountInput);
		final EditText pwdText = (EditText) findViewById(R.id.pwdInput);
		Button login = (Button) findViewById(R.id.button1);
		Button registerLogin = (Button) findViewById(R.id.button2);
		final String usernameString = usernameTxt.getText().toString();
		final String pwdString = pwdText.getText().toString();
		
		
		
		/*��¼ע�᲻��ң��ҿ����˾�һ��copy�ˣ���ʱ˭������һ�飬˭�ĸ�~
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
								"��������û����������������������룡", Toast.LENGTH_SHORT).show();
						pwdTxt.setText("");
						usernameTxt.setText("");
					}
				}else{
					Toast.makeText(LoginActivity.this, 
							"���������Ϣ��������", Toast.LENGTH_SHORT).show();
					
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
