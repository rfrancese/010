package com.example.fantaproject;


import java.util.ArrayList;
import java.util.List;

import com.example.fantandroid.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Activity{
	
	private MioDatabase databaseHelper;
	private EditText username;
	private EditText password;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_utente = "http://pepoman.altervista.org/create_utente.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	
	
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_register);
	        Button registra = (Button) findViewById(R.id.button3);
	        username = (EditText) findViewById(R.id.editText3);
			password = (EditText) findViewById(R.id.editText4);
	        databaseHelper = new MioDatabase(this);
	        registra.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
			        /*SQLiteDatabase db = databaseHelper.getWritableDatabase();
					ContentValues  values = new ContentValues();
					values.put("username", username.getText().toString());
					values.put("password", password.getText().toString());
					db.insert("utenti",null, values);
					startToastOk();
					finish();*/
					new CreateNewUser().execute();
				}
			});
	 }
	 private void startToastOk() {
			Toast t = Toast.makeText(this, "Registrazione effettuata", Toast.LENGTH_SHORT);			
			t.show();		
		}
	 
	 class CreateNewUser extends AsyncTask<String,String,String>{
		 
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(RegisterActivity.this);
	            pDialog.setMessage("Creando Utente...");
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
			JSONObject json = jsonParser.makeHttpRequest(url_create_utente, "POST", parametri);
			Log.i("JSONObject",json.toString());
			 try {
	                int success = json.getInt(TAG_SUCCESS);
	                if (success == 1) {
		                Log.i("success",""+success);
		                pDialog.dismiss();

		                finish();
	                    // closing this screen
	                } else {
		                Log.i("success",""+success);

	                    // failed to create product
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	 
			return null;
		}
		 
	 }
	 
	 protected void onPostExecute(String file_url) {
         // dismiss the dialog once done
     }

}
