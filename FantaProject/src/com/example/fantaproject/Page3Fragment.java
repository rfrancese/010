package com.example.fantaproject;

import com.example.fantandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Page3Fragment extends ListFragment{
	
	private OnPageListener pageListener;
	
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
	    String[] values = new String[] { "Reina","Valdes","Buffon","Maicon", "Benatia","Barzagli","Paci","Albiol","Fernandez","Chiellini","Puyol","Pjanic", "Pirlo", "Vidal", "Cerci","Taarabt","Candreva","Totti","Diamanti","Higuain", "Callejon" ,"Tevez","Balotelli","Destro","Lorente"};
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
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

