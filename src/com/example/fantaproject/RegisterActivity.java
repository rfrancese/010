package com.example.fantaproject;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.fantandroid.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Activity{
	
	//private MioDatabase databaseHelper;
	private EditText username;
	private EditText password;
	private AutoCompleteTextView p1;
	private AutoCompleteTextView p2;
	private AutoCompleteTextView p3;
	private AutoCompleteTextView d1;
	private AutoCompleteTextView d2;
	private AutoCompleteTextView d3;
	private AutoCompleteTextView d4;
	private AutoCompleteTextView d5;
	private AutoCompleteTextView d6;
	private AutoCompleteTextView d7;
	private AutoCompleteTextView d8;
	private AutoCompleteTextView c1;
	private AutoCompleteTextView c2;
	private AutoCompleteTextView c3;
	private AutoCompleteTextView c4;
	private AutoCompleteTextView c5;
	private AutoCompleteTextView c6;
	private AutoCompleteTextView c7;
	private AutoCompleteTextView c8;
	private AutoCompleteTextView a1;
	private AutoCompleteTextView a2;
	private AutoCompleteTextView a3;
	private AutoCompleteTextView a4;
	private AutoCompleteTextView a5;
	private AutoCompleteTextView a6;
	


	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_utente = "http://pepoman.altervista.org/create_utente.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	ArrayList<String> portieri = new ArrayList<String>();
	ArrayList<String> difensori = new ArrayList<String>();
	ArrayList<String> centrocampisti = new ArrayList<String>();
	ArrayList<String> attaccanti = new ArrayList<String>();



	ArrayAdapter<String> adapterPortieri;
	ArrayAdapter<String> adapterDifensori;
	ArrayAdapter<String> adapterCentrocampisti;
	ArrayAdapter<String> adapterAttaccanti;

	private MioDatabase mioDatabase;
	
	
	
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_register);
	        mioDatabase = new MioDatabase(this);
	        Button registra = (Button) findViewById(R.id.button3);
	        username = (EditText) findViewById(R.id.editText3);
			password = (EditText) findViewById(R.id.editText4);
			p1 = (AutoCompleteTextView) findViewById(R.id.editText1);
			p2 = (AutoCompleteTextView) findViewById(R.id.editText2);
			p3 = (AutoCompleteTextView) findViewById(R.id.editText5);
			
			d1 = (AutoCompleteTextView) findViewById(R.id.editText6);
			d2 = (AutoCompleteTextView) findViewById(R.id.editText7);
			d3 = (AutoCompleteTextView) findViewById(R.id.editText8);
			d4 = (AutoCompleteTextView) findViewById(R.id.editText9);
			d5 = (AutoCompleteTextView) findViewById(R.id.editText10);
			d6 = (AutoCompleteTextView) findViewById(R.id.editText11);
			d7 = (AutoCompleteTextView) findViewById(R.id.editText12);
			d8 = (AutoCompleteTextView) findViewById(R.id.editText13);
			
			c1 = (AutoCompleteTextView) findViewById(R.id.editText14);
			c2 = (AutoCompleteTextView) findViewById(R.id.editText15);
			c3 = (AutoCompleteTextView) findViewById(R.id.editText16);
			c4 = (AutoCompleteTextView) findViewById(R.id.editText17);
			c5 = (AutoCompleteTextView) findViewById(R.id.editText18);
			c6 = (AutoCompleteTextView) findViewById(R.id.editText19);
			c7 = (AutoCompleteTextView) findViewById(R.id.editText20);
			c8 = (AutoCompleteTextView) findViewById(R.id.editText21);
			
			a1 = (AutoCompleteTextView) findViewById(R.id.editText22);
			a2 = (AutoCompleteTextView) findViewById(R.id.editText23);
			a3 = (AutoCompleteTextView) findViewById(R.id.editText24);
			a4 = (AutoCompleteTextView) findViewById(R.id.editText25);
			a5 = (AutoCompleteTextView) findViewById(R.id.editText26);
			a6 = (AutoCompleteTextView) findViewById(R.id.editText27);
			


			new LoadCalciatoriTask().execute("portieri");
			adapterPortieri = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, portieri);
			 adapterPortieri.notifyDataSetChanged();
			p1.setAdapter(adapterPortieri);
			p2.setAdapter(adapterPortieri);
			p3.setAdapter(adapterPortieri);
			
			new LoadCalciatoriTask().execute("difensori");
			adapterDifensori = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, difensori);
			 adapterDifensori.notifyDataSetChanged();
			d1.setAdapter(adapterDifensori);
			d2.setAdapter(adapterDifensori);
			d3.setAdapter(adapterDifensori);
			d4.setAdapter(adapterDifensori);
			d5.setAdapter(adapterDifensori);
			d6.setAdapter(adapterDifensori);
			d7.setAdapter(adapterDifensori);
			d8.setAdapter(adapterDifensori);
			
			new LoadCalciatoriTask().execute("centrocampisti");
			adapterCentrocampisti = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, centrocampisti);
			 adapterCentrocampisti.notifyDataSetChanged();
			c1.setAdapter(adapterCentrocampisti);
			c2.setAdapter(adapterCentrocampisti);
			c3.setAdapter(adapterCentrocampisti);
			c4.setAdapter(adapterCentrocampisti);
			c5.setAdapter(adapterCentrocampisti);
			c6.setAdapter(adapterCentrocampisti);
			c7.setAdapter(adapterCentrocampisti);
			c8.setAdapter(adapterCentrocampisti);

			
			new LoadCalciatoriTask().execute("attaccanti");
			adapterAttaccanti = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,attaccanti);
			 adapterAttaccanti.notifyDataSetChanged();
			a1.setAdapter(adapterAttaccanti);
			a2.setAdapter(adapterAttaccanti);
			a3.setAdapter(adapterAttaccanti);
			a4.setAdapter(adapterAttaccanti);
			a5.setAdapter(adapterAttaccanti);
			a6.setAdapter(adapterAttaccanti);
	



			
	        registra.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {

					String squadra = p1.getText().toString()+","+p2.getText().toString()+","+p3.getText().toString()+","+d1.getText().toString()+","+d2.getText().toString()+","+d3.getText().toString()+","+d4.getText().toString()+","+d5.getText().toString()+","+d6.getText().toString()+","+d7.getText().toString()+","+d8.getText().toString()+","+c1.getText().toString()+","+c2.getText().toString()+","+c3.getText().toString()+","+c4.getText().toString()+","+c5.getText().toString()+","+c6.getText().toString()+","+c7.getText().toString()+","+c8.getText().toString()+","+a1.getText().toString()+","+a2.getText().toString()+","+a3.getText().toString()+","+a4.getText().toString()+","+a5.getText().toString()+","+a6.getText().toString();
					SQLiteDatabase db = mioDatabase.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put("p1",p1.getText().toString());
					values.put("p2",p2.getText().toString());
					values.put("p3",p3.getText().toString());
					values.put("d1",d1.getText().toString());
					values.put("d2",d2.getText().toString());
					values.put("d3",d3.getText().toString());
					values.put("d4",d4.getText().toString());
					values.put("d5",d5.getText().toString());
					values.put("d6",d6.getText().toString());
					values.put("d7",d7.getText().toString());
					values.put("d8",d8.getText().toString());
					values.put("c1",c1.getText().toString());
					values.put("c2",c2.getText().toString());
					values.put("c3",c3.getText().toString());
					values.put("c4",c4.getText().toString());
					values.put("c5",c5.getText().toString());
					values.put("c6",c6.getText().toString());
					values.put("c7",c7.getText().toString());
					values.put("c8",c8.getText().toString());
					values.put("a1",a1.getText().toString());
					values.put("a2",a2.getText().toString());
					values.put("a3",a3.getText().toString());
					values.put("a4",a4.getText().toString());
					values.put("a5",a5.getText().toString());
					values.put("a6",a6.getText().toString());
					db.insert("squadra",null, values);
					SharedPreferences prefs = getSharedPreferences("com.example.fantaproject", Context.MODE_PRIVATE);
					prefs.edit().putBoolean("squadra",true).commit();
					new CreateNewUser().execute(squadra);
				}
			});
	 }
	 private void startToastOk() {
			Toast t = Toast.makeText(this, "Registrazione effettuata", Toast.LENGTH_SHORT);			
			t.show();		
		}
	 
	 class LoadCalciatoriTask extends AsyncTask<String, Void, String> {

			
			private TagNode rootNode;
			String calciatoriScelti;
			
			@Override
			protected void onPreExecute() {
			}
				
			@Override
			protected String doInBackground(String... params) {
				
				// inizializzazione dell'oggetto HtmlCleaner utile a generare un html pulito
							HtmlCleaner cleaner = new HtmlCleaner();
							CleanerProperties props = cleaner.getProperties();
							props.setAllowHtmlInsideAttributes(true);
							props.setAllowMultiWordAttributes(true);
							props.setRecognizeUnicodeChars(true);
							props.setOmitComments(true);
							calciatoriScelti = params[0];
							String URL = "http://www.fantagazzetta.com/quotazioni-serie-a/"+calciatoriScelti+"/costodesc";
							// apertura della connessione
							URL url;
							try {
								
								url = new URL(URL);
								URLConnection conn = url.openConnection();
								
								//ora utilizziamo l'oggetto cleaner per "ripulire" l'html e inizializzare l'oggetto rootNode
								rootNode = cleaner.clean(new InputStreamReader(conn.getInputStream()));
								
								
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								Log.e("Error", e.getMessage());
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								Log.e("Error", e.getMessage());
							}
							return null;
			}
	 
			

			protected void onPostExecute(String result) {
			
		try {
	        	if(calciatoriScelti.equals("portieri")){

				
	        		 Object[] oPortieri = rootNode.evaluateXPath("//div[@class='wrapper']/div[@class='main']/div[@class='main-col']/div[@id='stats']/div[@class='content']/table/tbody/tr/td[position()=1]/a[position()=1]");
					
			   
			        	for(int i=0;i<oPortieri.length;i++){
			        		TagNode a = (TagNode) oPortieri[i];
			        		String p = a.getText().toString();
			        		String p2 = p.trim();
		        			portieri.add(p2);
		        		}
			      
			        adapterPortieri.notifyDataSetChanged();

	        		}else if(calciatoriScelti.equals("difensori")){
	        			Object[] Odifensori = rootNode.evaluateXPath("//div[@class='wrapper']/div[@class='main']/div[@class='main-col']/div[@id='stats']/div[@class='content']/table/tbody/tr/td[position()=1]/a[position()=1]");
				   
	        			for(int i=0;i<Odifensori.length;i++){
	        				TagNode a = (TagNode) Odifensori[i];
	        				String d = a.getText().toString();
	        				String d2 = d.trim();
	        				difensori.add(d2);
	        			}
	        			adapterDifensori.notifyDataSetChanged();
	        		}else if(calciatoriScelti.equals("centrocampisti")){
	        			Object[] oCentrocampisti = rootNode.evaluateXPath("//div[@class='wrapper']/div[@class='main']/div[@class='main-col']/div[@id='stats']/div[@class='content']/table/tbody/tr/td[position()=1]/a[position()=1]");
	 				   
	        			for(int i=0;i<oCentrocampisti.length;i++){
	        				TagNode a = (TagNode) oCentrocampisti[i];
	        				String c = a.getText().toString();
	        				String c2 = c.trim();

	        				centrocampisti.add(c2);
	        			}
	        			adapterCentrocampisti.notifyDataSetChanged();
	        		}else{
	        			Object[] oAttaccanti = rootNode.evaluateXPath("//div[@class='wrapper']/div[@class='main']/div[@class='main-col']/div[@id='stats']/div[@class='content']/table/tbody/tr/td[position()=1]/a[position()=1]");
	 				   
	        			for(int i=0;i<oAttaccanti.length;i++){
	        				TagNode a = (TagNode) oAttaccanti[i];
	        				String at = a.getText().toString();
	        				String at2 = at.trim();
	        				attaccanti.add(at2);
	        			}
	        			adapterAttaccanti.notifyDataSetChanged();
	        			
	        		}
				
		} catch (XPatherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
			}
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
			parametri.add(new BasicNameValuePair("squadra",params[0]));
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




