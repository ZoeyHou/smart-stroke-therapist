package com.example.gaitanalysis;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
//import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PatInfoMod extends ActionBarActivity {
	private ActionBar actionbar;
    
    TextView txtage;
    TextView txtheight;
    TextView txtgender;
    TextView txtname;
    TextView txtweight;
    TextView txtleg_length;
    String patient_id;
    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    //ArrayList<HashMap<String, String>> PatientList;
    // single patient url
    private static String url_pat_detail = "http://api.androidhive.info/android_connect/get_patient_details.php";
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
    //JSONArray patient =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pat_info_mod);
        
        // getting patient details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        patient_id = i.getStringExtra(TAG_PID);
        
        //PatientList = new ArrayList<HashMap<String, String>>();
        //Loading in Background Thread
        new LoadPatient().execute();
        // Get listview
        //ListView lv = getListView();

	}
    
    //Background Async Task to Get complete patient details
    class LoadPatient extends AsyncTask<String, String, String> {
        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PatInfoMod.this);
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
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("patient_id", patient_id);
                    	/*List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("patient_id", patient_id));*/
                        
                        // getting patient details by making HTTP request
                        // Note that patient details url will use GET request
                        JSONObject json;
						json = JSONParser.makeHttpRequest(url_pat_detail, "GET", params);
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
                            // Text
                            txtname = (TextView) findViewById(R.id.name);
                            txtgender = (TextView) findViewById(R.id.gender);
                            txtage = (TextView) findViewById(R.id.age);
                            txtheight = (TextView) findViewById(R.id.height);
                            txtweight = (TextView) findViewById(R.id.weight);
                            txtleg_length = (TextView) findViewById(R.id.leglen);
                            // display patient data
                            txtname.setText("姓名"+patient.getString(TAG_NAME));
                            
                             if(patient.getString(TAG_GENDER) == "0")
                            	 txtgender.setText("性别：男");
                             else
                            	 txtgender.setText("性别：女");
                            
                            txtage.setText("年龄:"+patient.getString(TAG_AGE)+"岁");
                            txtheight.setText("身高："+patient.getString(TAG_HEIGHT)+"cm");
                            txtweight.setText("体重："+patient.getString(TAG_WEIGHT)+"kg");
                            txtleg_length.setText("腿长："+patient.getString(TAG_LL)+"cm");
                            Intent in = new Intent();
                            in.putExtra("patient_id",patient_id);
                            in.setClass(PatInfoMod.this, PatInfoMod2.class);
                            startActivity(in);
                            pDialog.dismiss();
                            finish();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pat_info_mod, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(PatInfoMod.this, HomePage2.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
