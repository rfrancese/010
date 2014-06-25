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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
	
	private EditText username;
	private EditText password;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_login_utente = "http://pepoman.altervista.org/login.php";
	private static final String TAG_SUCCESS = "success";

	private MioDatabase mioDatabase;



	
	protected void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.activity_login);
		mioDatabase = new MioDatabase(this);	
		
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		Button entra = (Button)findViewById(R.id.button1);
		entra.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
		
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
				    /*	SharedPreferences prefs = getSharedPreferences("com.example.fantaproject", Context.MODE_PRIVATE);
					if(prefs.getBoolean("squadra", false)){
						Log.i("squadraON",json.getString("squadra"));
												*/
			        Log.i("squadraInLoginActivity",json.getString("squadra"));

							startMainActivity(json.getString("squadra"));

						
					//}else{
						/*Log.i("squadraOFF",json.getString("squadra"));
						String[] arrSquadra = json.getString("squadra").split(",");
						
						SQLiteDatabase db = mioDatabase.getWritableDatabase();
						ContentValues values = new ContentValues();
						values.put("p1",arrSquadra[0]);
						values.put("p2",arrSquadra[1]);
						values.put("p3",arrSquadra[2]);
						values.put("d1",arrSquadra[3]);
						values.put("d2",arrSquadra[4]);
						values.put("d3",arrSquadra[5]);
						values.put("d4",arrSquadra[6]);
						values.put("d5",arrSquadra[7]);
						values.put("d6",arrSquadra[8]);
						values.put("d7",arrSquadra[9]);
						values.put("d8",arrSquadra[10]);
						values.put("c1",arrSquadra[11]);
						values.put("c2",arrSquadra[12]);
						values.put("c3",arrSquadra[13]);
						values.put("c4",arrSquadra[14]);
						values.put("c5",arrSquadra[15]);
						values.put("c6",arrSquadra[16]);
						values.put("c7",arrSquadra[17]);
						values.put("c8",arrSquadra[18]);
						values.put("a1",arrSquadra[19]);
						values.put("a2",arrSquadra[20]);
						values.put("a3",arrSquadra[21]);
						values.put("a4",arrSquadra[22]);
						values.put("a5",arrSquadra[23]);
						values.put("a6",arrSquadra[24]);
						db.insert("squadra",null, values);
						prefs.edit().putBoolean("squadra",true).commit();
					}*/
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
			if(success == 1)
				pDialog.dismiss();
			if(success != 1)
				startToastError();

	     }
		
	}
	
	private void startToastError() {
		Toast t = Toast.makeText(this, "Login/Password Errata", Toast.LENGTH_SHORT);			
		t.show();		
	}
	private void startMainActivity(String squadra){
    	Intent intent = new Intent(this,MainActivity.class);
    	intent.putExtra("squadra",squadra);
    	startActivity(intent);
    }
	
	private void startRegisterActivity(){
		Intent intent = new Intent(this,RegisterActivity.class);
		startActivity(intent);
	}
}
