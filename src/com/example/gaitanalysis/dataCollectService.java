package com.example.gaitanalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import org.json.JSONObject;

public class dataCollectService extends Service implements SensorEventListener{
	SensorManager sensorManager=null;
	float[] x,y,z;
	int delay;
	int n,k;
	String patient_id;
	
	@Override
    //Bind the service
    public IBinder onBind(Intent intent){
    	return null;
    }
    //Start the service
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {  
    	super.onStartCommand(intent, flags, startId);
    	
    	//get from former pages by intents
    	patient_id=intent.getStringExtra("patient_id");
    	String minute=intent.getStringExtra("minute");
    	Map<String, String> params = new HashMap<String, String>();
        params.put("patient_id", patient_id);
        String url="calculate.php";
        JSONObject response=JSONParser.makeHttpRequest(url,"POST",params);
    	//create sensor manager instance
    	sensorManager=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
    	//create accelerometer 
    	Sensor accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		//register a sensor event listener
    	//sample rate is 50hz(20ms per sample)
    	delay=20;
    	if (minute=="two") k=2;
    	else if (minute=="five") k=5;
 
    	if(sensorManager.registerListener(this, accelerometer,delay)==false){
    		System.out.print("fail to register listener ");
    	}
		
		// We want this service to continue running until it is explicitly 
    	// stopped, so return sticky.  
    	return START_STICKY;  
    }
	@Override
	   //Called when there is a new sensor event
	   //Will also be called if we have a new reading from a sensor with the 
	   //exact same sensor values (but a newer timestamp).
	public void onSensorChanged(SensorEvent event){
		 //values[0],values[1],values[2] represent x,y,z axis 
		   float[] values=event.values;
		   x[n]=values[0];
		   y[n]=values[1];
		   z[n]=values[2];
		   n++;
		   if (n==k*60000 % delay) stopSample();	
	}
	   //When accuracy changes
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
	}
	public void stopSample(){
		 //unregister listener and stop sampling
			sensorManager.unregisterListener(this);
	    try {
		//Creates a new JSONArray with values from the given primitive array
		JSONArray xJSONArray=new JSONArray(x);
		String acc_x=xJSONArray.toString();
		JSONArray yJSONArray=new JSONArray(y);
		String acc_y=yJSONArray.toString();
		JSONArray zJSONArray=new JSONArray(z);
		String acc_z=zJSONArray.toString();
		Map<String, String> params = new HashMap<String, String>();
        params.put("acc_x", acc_x);
        params.put("acc_y", acc_y);
        params.put("acc_z", acc_z);
        String url="calculate.php";
        JSONObject response=JSONParser.makeHttpRequest(url,"POST",params);
        Intent intent=new Intent();
        intent.setClass(this,testResult.class);
        intent.putExtra("patient_id", patient_id);
        startActivity(intent);
	    } catch (JSONException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
	    }
	    //stop the service
	    this.stopSelf();
	}
	//Destroy the service
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
}
