package com.example.gaitanalysis;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;



@SuppressLint("NewApi") public class MainActivity extends ActionBarActivity {
	//private ActionBar actionbar;
	private RadioGroup radiogroup; 
	private RadioButton radiobutton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //通过setContentView方法设置当前页面的布局文件为activity_main
        setContentView(R.layout.activity_main);
        
        findview(); //获取页面中的radiogroup控件
        setListener();
        
        
 /*       else if(str.equals("亲友")==true){
            next.setOnClickListener(new OnClickListener(){
            	public void onClick(View v){
            		Intent intent=
            				new Intent(MainActivity.this,Register_rel.class);
            		startActivity(intent);
            	}
            });        	
        }*/
    
    }  
    
    private void setListener() {
        // TODO Auto-generated method stub
        //设置所有Radiogroup的状态改变监听器
        radiogroup.setOnCheckedChangeListener(mylistener);
        System.out.println("Here");
    }
    
    RadioGroup.OnCheckedChangeListener mylistener=new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup Group, int Checkid) {
        	Button next=(Button)findViewById(R.id.button1);
        	//获取选中值        	
        	 if(radiogroup.getCheckedRadioButtonId()==R.id.radio1){
        		 next.setOnClickListener(new OnClickListener(){
	                 public void onClick(View v){
	                 	Intent intent=new Intent(MainActivity.this,Register2.class);
	                 	startActivity(intent);
	                 }
                 });   
        	 }
        	 if(radiogroup.getCheckedRadioButtonId()==R.id.radio2){
        		 next.setOnClickListener(new OnClickListener(){
                 	public void onClick(View v){
                 		Intent intent=new Intent(MainActivity.this,Register_rel.class);
                 		startActivity(intent);
                 	}
                 });      
        	 }
        }
    };
    
    private void findview(){
    	//通过findViewById得到对应的控件对象
    	radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);  	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
