package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

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
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.ContentValues;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PersonalSetting extends ActionBarActivity {
    
    RadioButton yes;
    RadioButton no;
    RadioGroup alarm;
    EditText txtstart_hour;
    EditText txtend_hour;
    EditText txtstart_minute;
    EditText txtend_minute;
    TextView txtdoctor_advice;
    RadioGroup sum_fre;
    RadioButton month;
    RadioButton week;
    String pid;

    Button btnSave;
    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    //
    private static String url_update_settings = "http://api.androidhive.info/android_connect/update_settings.php";
    private static String url_set_advice = "http://api.androidhive.info/android_connect/get_doctor_advice.php";
    // JSON Node names
    private static String TAG_SUCCESS = "success";
    private static String TAG_SET = "settings";
    private static String TAG_PID = "patient_id";
    private static String TAG_ALARM = "alarm_or_not";
    private static String TAG_SH = "start_hour";
    private static String TAG_EH = "end_hour";
    private static String TAG_SM = "start_minute";
    private static String TAG_EM = "end_minute";
    private static String TAG_DA = "doctor_advice";
    private static String TAG_SUM = "summary_frequency";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_setting);
		
        // Edit Text
        alarm = (RadioGroup) findViewById(R.id.radioGroup1);
        yes = (RadioButton) findViewById(R.id.radio0);
        no = (RadioButton) findViewById(R.id.radio1);
        txtstart_hour = (EditText) findViewById(R.id.accountInput);
        txtstart_minute = (EditText) findViewById(R.id.EditText01);
        txtend_hour = (EditText) findViewById(R.id.EditText03);
        txtend_minute = (EditText) findViewById(R.id.EditText02);
        //txtdoctor_advice = (Textview) findViewById(R.id.TextView03);
        sum_fre = (RadioGroup) findViewById(R.id.radioGroup2);
        week = (RadioButton) findViewById(R.id.radio01);
        month = (RadioButton) findViewById(R.id.radio02);
        // save button
        btnSave = (Button) findViewById(R.id.button1);
        // getting settings details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        pid = i.getStringExtra(TAG_PID);
        // Getting complete settings details in background thread
        new GetSettings().execute();
        // save button click event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // startings background task
                new SaveSettings().execute();
            }
        });
	}
    
    //Background Async Task to Get complete setting details
    class GetSettings extends AsyncTask<String, String, String> {
        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PersonalSetting.this);
            pDialog.setMessage("Loading Settings. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        
        //Getting patient details in background thread
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run(){
                    // Check for success tag
                    int success;
                    try{
                        // Building Parameters/
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("patient_id", pid);
                        /*List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("patient_id", pid));*/
                        // getting details by making HTTP request
                        // Note that details url will use GET request
                        JSONObject json;
						json = JSONParser.makeHttpRequest(url_set_advice, "GET", params);
                        // check your log for json response
                        Log.d("Single Settings", json.toString());
                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if(success == 1) {
                            // successfully received details
                            JSONArray SettingObj = json
                            .getJSONArray(TAG_SET); // JSON Array
                            // get first setting object from JSON Array
                            JSONObject settings = SettingObj.getJSONObject(0);
                            // patient with this pid found
                            
                            // Edit Text
                            alarm = (RadioGroup) findViewById(R.id.radioGroup1);
                            yes = (RadioButton) findViewById(R.id.radio0);
                            no = (RadioButton) findViewById(R.id.radio1);
                            txtstart_hour = (EditText) findViewById(R.id.accountInput);
                            txtstart_minute = (EditText) findViewById(R.id.EditText01);
                            txtend_hour = (EditText) findViewById(R.id.EditText03);
                            txtend_minute = (EditText) findViewById(R.id.EditText02);
                            txtdoctor_advice = (TextView) findViewById(R.id.TextView03);
                            sum_fre = (RadioGroup) findViewById(R.id.radioGroup2);
                            week = (RadioButton) findViewById(R.id.radio01);
                            month = (RadioButton) findViewById(R.id.radio02);
                            
                            // display advice
                           txtdoctor_advice.setText("医生的建议频率："+settings.getString(TAG_DA));
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
    class SaveSettings extends AsyncTask<String, String, String> {
        
        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PersonalSetting.this);
            pDialog.setMessage("Saving settings ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        
        //Saving patient
        protected String doInBackground(String... args) {
            alarm = (RadioGroup) findViewById(R.id.radioGroup1);
            yes = (RadioButton) findViewById(R.id.radio0);
            no = (RadioButton) findViewById(R.id.radio1);
            txtstart_hour = (EditText) findViewById(R.id.accountInput);
            txtstart_minute = (EditText) findViewById(R.id.EditText01);
            txtend_hour = (EditText) findViewById(R.id.EditText03);
            txtend_minute = (EditText) findViewById(R.id.EditText02);
            txtdoctor_advice = (TextView) findViewById(R.id.TextView03);
            sum_fre = (RadioGroup) findViewById(R.id.radioGroup2);
            week = (RadioButton) findViewById(R.id.radio01);
            month = (RadioButton) findViewById(R.id.radio02);
            // getting updated data from EditTexts
            String alarm_or_not;
            if(yes.isChecked()){
                alarm_or_not = "1";
            }else{
            	alarm_or_not = "0";
            }
            String start_hour = txtstart_hour.getText().toString().trim();
            String start_minute = txtstart_minute.getText().toString().trim();
            String end_hour = txtend_hour.getText().toString().trim();
            String end_minute = txtend_minute.getText().toString().trim();
//            String doctor_advice = null;
            String summary_frequency;
            if(week.isChecked()){
            	summary_frequency = "0";
            }else{
            	summary_frequency = "1";
            }
            // Building Parameters
            Map<String, String> params = new HashMap<String, String>();
            params.put(TAG_PID, pid);
            params.put(TAG_ALARM, alarm_or_not);
            params.put(TAG_SH, start_hour);
            params.put(TAG_SM, start_minute);
            params.put(TAG_EH, end_hour);
            params.put(TAG_EM, end_minute);
            params.put(TAG_SUM, summary_frequency);
            /*List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_PID, pid));
            params.add(new BasicNameValuePair(TAG_ALARM, alarm_or_not));
            params.add(new BasicNameValuePair(TAG_SH, start_hour));
            params.add(new BasicNameValuePair(TAG_SM, start_minute));
            params.add(new BasicNameValuePair(TAG_EH, end_hour));
            params.add(new BasicNameValuePair(TAG_EM, end_minute));
//            params.add(newBasicNameValuePair(TAG_DA, doctor_advice));
            params.add(new BasicNameValuePair(TAG_SUM, summary_frequency));*/
            // sending modified data through http request
            // Notice that update patient url accepts POST method
            JSONObject json = JSONParser.makeHttpRequest(url_update_settings,
                                                         "POST", params);
            // check json success tag
            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // successfully updated
                    Intent i = getIntent();
                    // send result code 100 to notify about update
                    setResult(100, i);
                    finish();
                } else{
                    // failed to update
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        //After completing background task Dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once updated
            pDialog.dismiss();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(PersonalSetting.this, HomePage2.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
