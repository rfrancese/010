package com.example.fantaproject;


import com.example.fantandroid.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	
	private MioDatabase databaseHelper;
	private EditText username;
	private EditText password;
	
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
			        SQLiteDatabase db = databaseHelper.getWritableDatabase();
					ContentValues  values = new ContentValues();
					values.put("username", username.getText().toString());
					values.put("password", password.getText().toString());
					db.insert("utenti",null, values);
					startToastOk();
					finish();
				}
			});
	 }
	 private void startToastOk() {
			Toast t = Toast.makeText(this, "Registrazione effettuata", Toast.LENGTH_SHORT);			
			t.show();		
		}

}
