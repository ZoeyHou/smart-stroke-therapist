package com.example.gaitanalysis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.impl.client.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.content.ContentValues;

public class JSONParser {
static InputStream is = null;
static JSONObject jObj = null;
static String json = "";
// constructor
public JSONParser() {
}
// function get json from url
// by making HTTP POST or GET mehtod
    
public static JSONObject makeHttpRequest(String url, String method, Map<String, String> params){
//public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
    // Making HTTP request
    try{
        // check for request method
        if(method == "POST"){
            // request method is POST
            // defaultHttpClient
            /*DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();*/
            StringBuilder buf = new StringBuilder();
            Set<Entry<String, String>> entrys = null;
            // 婵″倹鐏夌�涙ê婀崣鍌涙殶閿涘苯鍨弨鎯ф躬HTTP鐠囬攱鐪版担鎿勭礉瑜般垹顩ame=aaa&age=10
            if (params != null && !params.isEmpty()) {
                entrys = params.entrySet();
                for (Map.Entry<String, String> entry : entrys) {
                    buf.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                    .append("&");
                }
                buf.deleteCharAt(buf.length() - 1);
            }
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream out = conn.getOutputStream();
            out.write(buf.toString().getBytes("UTF-8"));
            /*if (headers != null && !headers.isEmpty()) {
                entrys = headers.entrySet();
                for (Map.Entry<String, String> entry : entrys) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }*/
            int code = conn.getResponseCode();
            InputStream is = conn.getInputStream();
            //conn.getResponseCode(); // 娑撹桨绨￠崣鎴︼拷浣瑰灇閸旓拷
            //return conn;
        }else if(method == "GET"){
            // request method is GET
        	/*CloseableHttpClient httpClient = HttpClients.createDefault();
            String paramString = URLEncodedUtils.format(params, "utf-8");
            url += "?"+ paramString;
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();*/
            StringBuilder buf = new StringBuilder(url);
            Set<Entry<String, String>> entrys = null;
            // 婵″倹鐏夐弰鐤揈T鐠囬攱鐪伴敍灞藉灟鐠囬攱鐪伴崣鍌涙殶閸︹晳RL娑擄拷
            if (params != null && !params.isEmpty()) {
                buf.append("?");
                entrys = params.entrySet();
                for (Map.Entry<String, String> entry : entrys) {
                    buf.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                    .append("&");
                }
                buf.deleteCharAt(buf.length() - 1);
            }
            URL url1 = new URL(buf.toString());
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            // 鐠佸墽鐤嗙拠閿嬬湴婢讹拷
            /*if (headers != null && !headers.isEmpty()) {
                entrys = headers.entrySet();
                for (Map.Entry<String, String> entry : entrys) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }*/
            //conn.getResponseCode();
            int code = conn.getResponseCode();
            is = conn.getInputStream();
            //return conn;
        }
    } catch(UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch(ClientProtocolException e) {
        e.printStackTrace();
    } catch(IOException e) {
        e.printStackTrace();
    }
    try{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        json = sb.toString();
    } catch(Exception e) {
        Log.e("Buffer Error", "Error converting result "+ e.toString());
    }
    // try parse the string to a JSON object
    try{
        jObj = new JSONObject(json);
    } catch(JSONException e) {
        Log.e("JSON Parser", "Error parsing data "+ e.toString());
    }
    // return JSON String
    return jObj;
}
}