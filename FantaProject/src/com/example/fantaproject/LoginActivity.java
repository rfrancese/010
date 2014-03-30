package com.example.fantaproject;


import com.example.fantandroid.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	private MioDatabase database;
	private EditText username;
	private EditText password;

	
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
				SQLiteDatabase db = database.getReadableDatabase();
				String[] selezione = {username.getText().toString(),password.getText().toString()};
				Cursor cursor = db.query("utenti",null,"username = ? AND password = ?",selezione,null,null,null);
			if(cursor.getCount() > 0)
				startMainActivity();	
			else
				startToastError();
			
				
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
