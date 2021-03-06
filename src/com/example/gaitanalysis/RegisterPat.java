package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//
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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RegisterPat extends ActionBarActivity {
    
    // Progress Dialog
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String patient_id;
    EditText inputage;
    EditText inputheight;
    String gender;
    RadioButton man;
    RadioButton woman;
    EditText inputweight;
    // url to update patient info
    private static String url_update_patient = "http://119.29.245.107:81/GaitAnalysis/android_connect/update_patient_detail.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_pat);
        
        // Edit Text
 
        inputage = (EditText) findViewById(R.id.peragetext);
        inputheight = (EditText) findViewById(R.id.perheighttext);
        inputweight = (EditText) findViewById(R.id.EditText01);
        man = (RadioButton) findViewById(R.id.radioButton1);
        woman = (RadioButton) findViewById(R.id.radioButton2);
        
        // Create button
        Button btnUpdatePatient = (Button) findViewById(R.id.regpatnext);
        // getting patient details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        patient_id = i.getStringExtra("patient_id");
        // button click event
        btnUpdatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // update patient in background thread
                new UpdatePatient().execute();
            } 
        });
	}
    
    //Background Async Task to Create new patient
    class UpdatePatient extends AsyncTask<String, String, String> {

        //Before starting background thread Show Progress Dialog
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterPat.this);
            pDialog.setMessage("Creating..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        //
        protected String doInBackground(String... args) {
            String age = inputage.getText().toString().trim();
            String height = inputheight.getText().toString().trim();
            String weight = inputweight.getText().toString().trim();
            if(man.isChecked()){
                gender = "0";
            }else{
                gender = "1";
            }
            // Building Parameters
            Map<String, String> params = new HashMap<String, String>();
            params.put("patient_id", patient_id);
            params.put("age", age);
            params.put("height", height);
            params.put("gender", gender);
            params.put("weight", weight);
            /*List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("patient_id", patient_id));
            params.add(new BasicNameValuePair("age", age));
            params.add(new BasicNameValuePair("height", height));
            params.add(new BasicNameValuePair("gender", gender));
            params.add(new BasicNameValuePair("weight", weight));*/
            // getting JSON Object
            // Note that create patient url accepts POST method
            JSONObject json;
			json = JSONParser.makeHttpRequest(url_update_patient,
			                                             "POST", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
            // check for success tag
            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // successfully created patient
                    Intent i = new Intent();
                    i.putExtra("patient_id",patient_id);
                    i.setClass(RegisterPat.this, LegLenRecord.class);
                    startActivity(i);
                    // closing this screen 
                    pDialog.dismiss();
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
		getMenuInflater().inflate(R.menu.register_pat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(RegisterPat.this, HomePage.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
