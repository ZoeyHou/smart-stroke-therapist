package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
import android.content.ContentValues;

public class PatInfoMod2 extends ActionBarActivity {
    
    EditText txtname;
    RadioButton man;
    RadioButton woman;
    EditText txtage;
    EditText txtheight;
    EditText txtweight;
    EditText txtleg_length;
    Button btnSave;
    String pid;
    String gender;
    
    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    // single patient url
    private static final String url_get_pat = "http://api.androidhive.info/android_connect/get_patient_details.php";
    private static final String url_update_pat = "http://api.androidhive.info/android_connect/update_patient.php";
    
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PATIENT = "patient";
    private static final String TAG_PID = "patient_id";
    private static final String TAG_NAME = "patient_name";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_AGE = "age";
    private static final String TAG_HEIGHT = "height";
    private static final String TAG_WEIGHT = "weight";
    private static final String TAG_LL = "leg_length";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pat_info_mod2);
        
        // save button
        btnSave = (Button)findViewById(R.id.button);
        // getting patient details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        pid = i.getStringExtra(TAG_PID);
        // Getting complete patient details in background thread
        new GetPatientDetails().execute();
        // save button click event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // starting background task to update patient
                new SavePatientDetails().execute();
            }
        });
	}
    
    //Background Async Task to Get complete patient details
    class GetPatientDetails extends AsyncTask<String, String, String> {
        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PatInfoMod2.this);
            pDialog.setMessage("Loading patient details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        
        //Getting patient details in background thread
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try{
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("patient_id", pid));
                        // getting patient details by making HTTP request
                        // Note that patient details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(url_get_pat, "GET", params);
                        // check your log for json response
                        Log.d("Single Patient Details", json.toString());
                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if(success == 1) {
                            // successfully received patient details
                            JSONArray patientObj = json
                            .getJSONArray(TAG_PATIENT); // JSON Array
                            // get first patient object from JSON Array
                            JSONObject patient = patientObj.getJSONObject(0);
                            // patient with this pid found
                            // Edit Text
                            txtname = (EditText) findViewById(R.id.editName);

                            man = (RadioButton) findViewById(R.id.radio0);
                            woman = (RadioButton) findViewById(R.id.radio1);
                            txtage = (EditText) findViewById(R.id.editAge);
                            txtheight = (EditText) findViewById(R.id.editHeight);
                            txtweight = (EditText) findViewById(R.id.editWeight);
                            txtleg_length = (EditText) findViewById(R.id.editLegLen);
                            // display patient data in EditText
                            txtname.setText(patient.getString(TAG_NAME));
                            /*
                             if(patient.getString(TAG_GENDER) == "0")
                             txtgender.setText(鈥滅敺鈥�;
                             else
                             txtgender.setText(鈥滃コ鈥�;
                             */
                            txtage.setText(patient.getString(TAG_AGE));
                            txtheight.setText(patient.getString(TAG_HEIGHT));
                            txtweight.setText(patient.getString(TAG_WEIGHT));
                            txtleg_length.setText(patient.getString(TAG_LL));
                        }else{
                            // patient with pid not found
                        }
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
        //After completing background task Dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }
    
    //Background Async Task to Save patient Details
    class SavePatientDetails extends AsyncTask<String, String, String> {
        
        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PatInfoMod2.this);
            pDialog.setMessage("Saving patient ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        
        //Saving patient
        protected String doInBackground(String... args) {
            // getting updated data from EditTexts
            String patient_name = txtname.getText().toString().trim();
            if(man.isChecked()){
                gender = "0";
            }else{
                gender = "1";
            }
            String age = txtage.getText().toString().trim();
            String height = txtheight.getText().toString().trim();
            String weight = txtweight.getText().toString().trim();
            String leg_length = txtleg_length.getText().toString().trim();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_PID, pid));
            params.add(new BasicNameValuePair(TAG_NAME, patient_name));
            params.add(new BasicNameValuePair(TAG_GENDER, gender));
            params.add(new BasicNameValuePair(TAG_AGE, age));
            params.add(new BasicNameValuePair(TAG_HEIGHT, height));
            params.add(new BasicNameValuePair(TAG_WEIGHT, weight));
            params.add(new BasicNameValuePair(TAG_LL, leg_length));
            // sending modified data through http request
            // Notice that update patient url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_pat,
                                                         "POST", params);
            // check json success tag
            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // successfully updated
                    Intent i = getIntent();
                    // send result code 100 to notify about patient update
                    setResult(100, i);
                    finish();
                } else{
                    // failed to update patient
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        //After completing background task Dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once patient updated
            pDialog.dismiss();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pat_info_mod2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(PatInfoMod2.this, HomePage2.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
