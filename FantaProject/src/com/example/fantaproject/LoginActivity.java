package com.example.fantaproject;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fantandroid.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	private MioDatabase database;
	private EditText username;
	private EditText password;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_login_utente = "http://pepoman.altervista.org/login.php";
	private static final String TAG_SUCCESS = "success";




	
	protected void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.activity_login);
		database = new MioDatabase(this);	
		
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		Button entra = (Button)findViewById(R.id.button1);
		entra.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				/*SQLiteDatabase db = database.getReadableDatabase();
				String[] selezione = {username.getText().toString(),password.getText().toString()};
				Cursor cursor = db.query("utenti",null,"username = ? AND password = ?",selezione,null,null,null);
			if(cursor.getCount() > 0)
				startMainActivity();	
			else
				startToastError();
			*/
			new LoginUser().execute();	
			}
		});
		Button registra = (Button)findViewById(R.id.button2);
		registra.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startRegisterActivity();
			}
		});
		
	}
	
	class LoginUser extends AsyncTask<String,String,String>{
		
		int success;
		
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Login in corso...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... params) {
			String user = username.getText().toString();
			String pass = password.getText().toString();
			List<NameValuePair> parametri = new ArrayList<NameValuePair>();
			parametri.add(new BasicNameValuePair("user",user));
			parametri.add(new BasicNameValuePair("pass",pass));
			JSONObject json = jsonParser.makeHttpRequest(url_login_utente,"POST", parametri);
			try{
				success = json.getInt(TAG_SUCCESS);
                Log.i("success login",""+success);

				if(success == 1){
					startMainActivity();
				}else{
					pDialog.dismiss();

					}
				
			}catch(JSONException e){
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(String file_url) {
	         // dismiss the dialog once done
			if(success != 1)
				startToastError();

	     }
		
	}
	
	private void startToastError() {
		Toast t = Toast.makeText(this, "Login/Password Errata", Toast.LENGTH_SHORT);			
		t.show();		
	}
	private void startMainActivity(){
    	Intent intent = new Intent(this,MainActivity.class);
    	startActivity(intent);
    }
	
	private void startRegisterActivity(){
		Intent intent = new Intent(this,RegisterActivity.class);
		startActivity(intent);
	}
}
