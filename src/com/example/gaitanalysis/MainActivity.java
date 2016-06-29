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
      //ͨ��setContentView�������õ�ǰҳ��Ĳ����ļ�Ϊactivity_main
        setContentView(R.layout.activity_main);
        
        findview(); //��ȡҳ���е�radiogroup�ؼ�
        setListener();
        
        
 /*       else if(str.equals("����")==true){
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
        //��������Radiogroup��״̬�ı������
        radiogroup.setOnCheckedChangeListener(mylistener);
        System.out.println("Here");
    }
    
    RadioGroup.OnCheckedChangeListener mylistener=new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup Group, int Checkid) {
        	Button next=(Button)findViewById(R.id.button1);
        	//��ȡѡ��ֵ        	
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
    	//ͨ��findViewById�õ���Ӧ�Ŀؼ�����
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
