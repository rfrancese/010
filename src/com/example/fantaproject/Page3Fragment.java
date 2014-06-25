package com.example.fantaproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.fantandroid.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Page3Fragment extends ListFragment{
	
	private OnPageListener pageListener;
	private MioDatabase mioDatabase;
	
	public interface OnPageListener{
		public void settaGiocatore(String s,int position);

	}
	
	public void onAttach(Activity activity){
		super.onAttach(activity);
		if(activity instanceof OnPageListener){
			pageListener = (OnPageListener) activity;
		}
		
	}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		if(container == null)
			return null;
					
		View view = inflater.inflate(R.layout.fragment_rosa,container,false);
	
		return view;
	}
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	   /* mioDatabase = new MioDatabase(getActivity());
		SQLiteDatabase db = mioDatabase.getReadableDatabase();
		Cursor cursor = db.query("squadra",null,null,null,null,null,null);
		cursor.moveToFirst();
	    ArrayList<String> values = new ArrayList<String>();
	    Log.i("squadra db",cursor.getString(3));

	    for(int i=1;i<25;i++){
		    Log.i("squadra db",cursor.getString(i));

	    values.add(cursor.getString(i));
	    }*/
	    Bundle datiPassati = getActivity().getIntent().getExtras();
        String squadra = datiPassati.getString("squadra");
        Log.i("squadraInFragment3",squadra);
       
        String[] arrSquadra = squadra.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrSquadra);
        adapter.notifyDataSetChanged();
	    setListAdapter(adapter);
	  }
	
	
	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		 
		  
		  	String item = (String) getListAdapter().getItem(position);	
		  
		  	pageListener.settaGiocatore(item,position);
		  	Toast t = Toast.makeText(getActivity(), item+" aggiunto al campo", Toast.LENGTH_SHORT);			
			t.show();
		  	
		  }

	
	  
	  
	} 

