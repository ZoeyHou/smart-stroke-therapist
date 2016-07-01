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
import android.content.ContentValues;

public class LegLenRecord extends ActionBarActivity {

    // Progress Dialog
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String patient_id;
    EditText inputleg_length;
    
    // url to update patient info
    private static String url_update_leg = "http://api.androidhive.info/android_connect/update_patient_leg_length.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leg_len_record);
        
        // Edit Text
        
        inputleg_length = (EditText) findViewById(R.id.editText1);
        
        // Create button
        Button btnUpdateLeg = (Button) findViewById(R.id.button1);
        // getting patient details from intent
        Intent i = getIntent();
        // getting patient id (pid) from intent
        patient_id = i.getStringExtra("patient_id");
        // button click event
        btnUpdateLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {           
                // update patient in background thread
                new UpdateLeg().execute();
     /*           //Jump to Page HomePage2
                Intent intent = new Intent();
                intent.setClass(LegLenRecord.this, HomePage2.class);
    			startActivity(intent);
    */
    		
            }
        });
	}
    
    //Background Async Task to Create new patient
    class UpdateLeg extends AsyncTask<String, String, String> {
        
        //Before starting background thread Show Progress Dialog
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LegLenRecord.this);
            pDialog.setMessage("Creating..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        
        //
        protected String doInBackground(String... args) {
            String leg_length = inputleg_length.getText().toString().trim();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("patient_id", patient_id));
            params.add(new BasicNameValuePair("leg_length", leg_length));
            
            // getting JSON Object
            // Note that create patient url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_leg,
                                                         "POST", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
            // check for success tag
            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // successfully created patient
                    //Intent i = newIntent(getApplicationContext(), AllActivity.class);
                    //startActivity(i);
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
		getMenuInflater().inflate(R.menu.leg_len_record, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			 //Jump to Page HomePage2
			Intent intent = new Intent(LegLenRecord.this, HomePage.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
