package com.example.fantaproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MioDatabase extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "mioDatabase";
	private static final int DB_VERSION = 1;

	public MioDatabase(Context context) {
		super(context,DB_NAME,null,DB_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "";
		sql +="CREATE TABLE utenti ( _id INTEGER PRIMARY KEY, username TEXT NOT NULL, password TEXT NOT NULL )";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
