package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
//import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Register_rel extends ActionBarActivity {

    // Progress Dialog
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputrelative_id;
    EditText inputrelative_pwd;
    EditText inputpatient_id;
    
    // url to create relative
    private static String url_create_relative = "http://api.androidhive.info/android_connect/create_relative.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_rel);
        
        // Edit Text
        inputrelative_id = (EditText) findViewById(R.id.personaccount);
        inputrelative_pwd = (EditText) findViewById(R.id.personpassword);
        inputpatient_id = (EditText) findViewById(R.id.EditText01);
        // Create button
        Button btnCreateRelative = (Button) findViewById(R.id.regpatnext);
        // button click event
        btnCreateRelative.setOnClickListener(newView.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create relative in background thread
                new CreateRelative().execute();
            }
        });
	}
    
    //Background Async Task to Create new relative
    class CreateRelative extends AsyncTask<String, String, String> {
        
        //Before starting background thread Show Progress Dialog
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = newProgressDialog(Register_rel.this);
            pDialog.setMessage("Creating..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
    
        //Creating relative
        protected String doInBackground(String... args) {
            String relative_id = inputrelative_id.getText().toString().trim();
            String relative_pwd = inputrelative_pwd.getText().toString();
            String patient_id = inputpatient_id.getText().toString().trim();
            // Building Parameters
            List<NameValuePair> params = newArrayList<NameValuePair>();
            params.add(newBasicNameValuePair("relative_id", relative_id));
            params.add(newBasicNameValuePair("relative_pwd", relative_pwd));
            params.add(newBasicNameValuePair("patient_id", patient_id));
            // getting JSON Object
            // Note that create relative url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_relative,
                                                         "POST", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
            // check for success tag
            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // successfully created
                    //Intent i = newIntent(getApplicationContext(), AllActivity.class);
                    //startActivity(i);
                    // closing this screen
                    finish();
                } else{
                    // failed to create
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        //After completing background task Dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_rel, menu);
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
