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
import android.content.ContentValues;

public class Register2 extends ActionBarActivity {

    // Progress Dialog
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputpatient_id;
    EditText inputpatient_pwd;
    EditText inputdoctor_id;
    
    // url to create relative
    private static String url_create_patient = "http://api.androidhive.info//create_patient.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register2);
        
        // Edit Text
        inputpatient_id = (EditText) findViewById(R.id.personaccount);
        inputpatient_pwd = (EditText) findViewById(R.id.personpassword);
        inputdoctor_id = (EditText) findViewById(R.id.EditText01);
        // Create button
        Button btnCreatePatient = (Button) findViewById(R.id.button1);
        // button click event
        btnCreatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create patient in background thread
                new CreatePatient().execute();
            }
        });
	}
    //Background Async Task to Create new patient
    class CreatePatient extends AsyncTask<String, String, String> {
        
        //Before starting background thread Show Progress Dialog
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register2.this);
            pDialog.setMessage("Creating..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        
        //Creating patient
        protected String doInBackground(String... args) {
            String patient_id = inputpatient_id.getText().toString().trim();
            String patient_pwd = inputpatient_pwd.getText().toString();
            String doctor_id = inputdoctor_id.getText().toString().trim();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("patient_id", patient_id));
            params.add(new BasicNameValuePair("patient_pwd", patient_pwd));
            params.add(new BasicNameValuePair("doctor_id", doctor_id));
            // getting JSON Object
            // Note that create patient url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_patient,
                                                         "POST", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
            // check for success tag
            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // successfully created
                    Intent i = new Intent();
                    i.putExtra("patient_id",patient_id);
                    i.setClass(Register2.this, RegisterPat.class);
                    startActivity(i);
                    // closing this screen
                    //finish();
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
		getMenuInflater().inflate(R.menu.register2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(Register2.this, HomePage.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
