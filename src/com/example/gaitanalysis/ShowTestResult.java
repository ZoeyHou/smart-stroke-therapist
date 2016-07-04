package com.example.gaitanalysis;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowTestResult extends ActionBarActivity {
    TextView txt_avg_length;
    TextView txt_avg_cadence;
    TextView txt_avg_degree;
    TextView txt_dcd_length;
    TextView txt_dcd_cadence;
    TextView txt_dcd_degree;
    String patient_id;
    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    //ArrayList<HashMap<String, String>> PatientList;
    // single patient url
    private static String url_test_result = "http://api.androidhive.info/android_connect/get_patient_testResult.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PATIENT = "patient";
    private static final String TAG_PID = "patient_id";
    private static final String TAG_AVG_LENGTH = "avg_length";
    private static final String TAG_AVG_CADENCE = "avg_cadence";
    private static final String TAG_AVG_DEGREE = "avg_degree";
    private static final String TAG_DCD_LENGTH = "dcd_length";
    private static final String TAG_DCD_CADENCE = "dcd_cadence";
    private static final String TAG_DCD_DEGREE = "dcd_degree";
    //JSONArray patient =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		patient_id=intent.getStringExtra("patient_id");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_test_result);
		new showResult().execute();
	}
	//Background Async Task to Get complete test result
    class showResult extends AsyncTask<String, String, String> {
        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowTestResult.this);
            pDialog.setMessage("Loading patient test result. Please wait...");
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
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("patient_id", patient_id);
                    	/*List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("patient_id", patient_id));*/
                        
                        // getting test result by making HTTP request
                        // Note that test result url will use GET request
                        JSONObject json;
						json = JSONParser.makeHttpRequest(url_test_result, "GET", params);
                        // check your log for json response
                        Log.d("Single test result", json.toString());
                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if(success == 1) {
                            txt_avg_length = (TextView) findViewById(R.id.avLength);
                            txt_avg_cadence = (TextView) findViewById(R.id.avGaitTime);
                            txt_avg_degree = (TextView) findViewById(R.id.avAngle);
                            txt_dcd_length = (TextView) findViewById(R.id.gaitLenSym);
                            txt_dcd_cadence = (TextView) findViewById(R.id.GaitTimeSym);
                            txt_dcd_degree = (TextView) findViewById(R.id.angleSym);
                            // display patient data
                            txt_avg_length.setText(json.getString(TAG_AVG_LENGTH)+"cm");
                            txt_avg_cadence.setText(json.getString(TAG_AVG_CADENCE)+"s");
                            txt_avg_degree.setText(json.getString(TAG_AVG_DEGREE)+"cm");
                            txt_dcd_length.setText(json.getString(TAG_DCD_LENGTH));
                            txt_dcd_cadence.setText(json.getString(TAG_DCD_CADENCE));
                            txt_dcd_degree.setText(json.getString(TAG_DCD_DEGREE));
                            Intent in = new Intent();
                            in.putExtra("patient_id",patient_id);
                            in.setClass(ShowTestResult.this, HomePage2.class);
                            startActivity(in);
                            pDialog.dismiss();
                            finish();
                        }else{
                            // patient test result with pid not found
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_test_result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent();
			intent.setClass(ShowTestResult.this, HomePage2.class);
			intent.putExtra("patient_id", patient_id);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
