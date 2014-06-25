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
		sql +="CREATE TABLE squadra ( _id INTEGER PRIMARY KEY, p1 TEXT NOT NULL, p2 TEXT NOT NULL , p3 TEXT NOT NULL , d1 TEXT NOT NULL , d2 TEXT NOT NULL , d3 TEXT NOT NULL , d4 TEXT NOT NULL , d5 TEXT NOT NULL , d6 TEXT NOT NULL , d7 TEXT NOT NULL , d8 TEXT NOT NULL , c1 TEXT NOT NULL , c2 TEXT NOT NULL , c3 TEXT NOT NULL , c4 TEXT NOT NULL , c5 TEXT NOT NULL , c6 TEXT NOT NULL , c7 TEXT NOT NULL , c8 TEXT NOT NULL , a1 TEXT NOT NULL , a2 TEXT NOT NULL , a3 TEXT NOT NULL , a4 TEXT NOT NULL , a5 TEXT NOT NULL , a6 TEXT NOT NULL  )";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
