package com.example.fantaproject;

import com.example.fantandroid.R;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Page2Fragment extends Fragment{
	
	String formazioneTitolare="";
	TextView d;
	TextView c;
	TextView a;
	String p="";
	String d1="";
	String d2="";
	String d3="";
	String d4="";
	String c1="";
	String c2="";
	String c3="";
	String c4="";
	String a1="";
	String a2="";
	String a3="";



	int i=1;
	int j=1;
	int k=1;
	String moduloScelto = "3-4-3";
	
	/* @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setRetainInstance(true);
	    }*/
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

		if(container == null)
			return null;
		View view = inflater.inflate(R.layout.fragment_squadra,container,false);


		
		return view;
	}
	
	 @Override
	public void onSaveInstanceState(Bundle outState){
		TextView p = (TextView) getView().findViewById(R.id.portiere);
		TextView d1 = (TextView) getView().findViewById(R.id.d1);
		TextView d2= (TextView) getView().findViewById(R.id.d2);
		TextView d3 = (TextView) getView().findViewById(R.id.d3);
		TextView c1 = (TextView) getView().findViewById(R.id.c1);
		TextView c2 = (TextView) getView().findViewById(R.id.c2);
		TextView c3 = (TextView) getView().findViewById(R.id.c3);
		TextView c4 = (TextView) getView().findViewById(R.id.c4);
		TextView a1 = (TextView) getView().findViewById(R.id.a1);
		TextView a2 = (TextView) getView().findViewById(R.id.a2);
		TextView a3 = (TextView) getView().findViewById(R.id.a3);
		outState.putString("p",(String) p.getText());
		outState.putString("d1",(String) d1.getText());
		outState.putString("d2",(String) d2.getText());
		outState.putString("d3",(String) d3.getText());
		outState.putString("c1",(String) c1.getText());
		outState.putString("c2",(String) c2.getText());
		outState.putString("c3",(String) c3.getText());
		outState.putString("c4",(String) c4.getText());
		outState.putString("a1",(String) a1.getText());
		outState.putString("a2",(String) a2.getText());
		outState.putString("a3",(String) a3.getText());
		outState.putString("moduloScelto", moduloScelto);
		outState.putInt("i",i);
		outState.putInt("j",j);
		outState.putInt("k",k);
		outState.putAll(outState);
		



		

		}
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    if(savedInstanceState != null){
	    	TextView p = (TextView) getView().findViewById(R.id.portiere);
			TextView d1 = (TextView) getView().findViewById(R.id.d1);
			TextView d2= (TextView) getView().findViewById(R.id.d2);
			TextView d3 = (TextView) getView().findViewById(R.id.d3);
			TextView c1 = (TextView) getView().findViewById(R.id.c1);
			TextView c2 = (TextView) getView().findViewById(R.id.c2);
			TextView c3 = (TextView) getView().findViewById(R.id.c3);
			TextView c4 = (TextView) getView().findViewById(R.id.c4);
			TextView a1 = (TextView) getView().findViewById(R.id.a1);
			TextView a2 = (TextView) getView().findViewById(R.id.a2);
			TextView a3 = (TextView) getView().findViewById(R.id.a3);
			p.setText(savedInstanceState.getString("p"));
			d1.setText(savedInstanceState.getString("d1"));
			d2.setText(savedInstanceState.getString("d2"));
			d3.setText(savedInstanceState.getString("d3"));
			c1.setText(savedInstanceState.getString("c1"));
			c2.setText(savedInstanceState.getString("c2"));
			c3.setText(savedInstanceState.getString("c3"));
			c4.setText(savedInstanceState.getString("c4"));
			a1.setText(savedInstanceState.getString("a1"));
			a2.setText(savedInstanceState.getString("a2"));
			a3.setText(savedInstanceState.getString("a3"));
			i = savedInstanceState.getInt("i");
			j = savedInstanceState.getInt("j");
			k = savedInstanceState.getInt("k");


	
						 
	    }
	    Button inviaSms = (Button)getView().findViewById(R.id.button1);
	    inviaSms.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				TextView p = (TextView) getView().findViewById(R.id.portiere);
				TextView d1 = (TextView) getView().findViewById(R.id.d1);
				TextView d2= (TextView) getView().findViewById(R.id.d2);
				TextView d3 = (TextView) getView().findViewById(R.id.d3);
				TextView c1 = (TextView) getView().findViewById(R.id.c1);
				TextView c2 = (TextView) getView().findViewById(R.id.c2);
				TextView c3 = (TextView) getView().findViewById(R.id.c3);
				TextView c4 = (TextView) getView().findViewById(R.id.c4);
				TextView a1 = (TextView) getView().findViewById(R.id.a1);
				TextView a2 = (TextView) getView().findViewById(R.id.a2);
				TextView a3 = (TextView) getView().findViewById(R.id.a3);
				formazioneTitolare += p.getText()+"\n"+d1.getText()+"\n"+d2.getText()+"\n"+d3.getText()+"\n"+c1.getText()+"\n"+c2.getText()+"\n"+c3.getText()+"\n"+c4.getText()+"\n"+a1.getText()+"\n"+a2.getText()+"\n"+a3.getText()+"\n";
				Intent i = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
			//	i.putExtra("formazioneTitolare", formazioneTitolare);
				startActivityForResult(i,0);
				
			}
	    	
	    });
	    Spinner spinner = (Spinner) getView().findViewById(R.id.spinner1);
	    String[] values = new String[] {"3-4-3","4-4-2","4-3-3"};
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
	    spinner.setAdapter(adapter);
	    spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				moduloScelto = parent.getItemAtPosition(position).toString();
				if(moduloScelto.equals("3-4-3")){
					TextView tw = (TextView) getView().findViewById(R.id.d4);
					tw.setText("");
					k=3;
				}
				if(moduloScelto.equals("4-4-2")){
					TextView tw = (TextView) getView().findViewById(R.id.a3);
					tw.setText("");
					i=4;
				}
				if(moduloScelto.equals("4-3-3")){
					TextView tw = (TextView) getView().findViewById(R.id.c1);
					tw.setText("");
					
					
				}
				Toast.makeText(parent.getContext(),parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
	    
	    });
	  }
	
	public void settaPortiere(String item){
		
		TextView portiere = (TextView) getView().findViewById(R.id.portiere);

		portiere.setText(item);
	}
	
	public void settaDifensore(String item){
			if(i==1){

			d = (TextView) getView().findViewById(R.id.d1);

			d.setText(item);
			i++;
			}else if(i==2){
				d = (TextView) getView().findViewById(R.id.d2);
				d.setText(item);
				i++;
			}else if(i==3){
				d= (TextView) getView().findViewById(R.id.d3);
				d.setText(item);
					if(moduloScelto.equals("3-4-3"))
						i=1;
					else
						i++;
			}else if(i==4){
				d = (TextView) getView().findViewById(R.id.d4);
				d.setText(item);
				i=1;
			}
			}
	public void settaCentrocampista(String item){
		if(j==1){
		c = (TextView) getView().findViewById(R.id.c1);
		c.setText(item);
		j++;
		}else if(j==2){
			c = (TextView) getView().findViewById(R.id.c2);
			c.setText(item);
			j++;
		}else if(j==3){
			c= (TextView) getView().findViewById(R.id.c3);
			c.setText(item);
			j++;
		}else if(j==4){
			c= (TextView) getView().findViewById(R.id.c4);
			c.setText(item);
			j=1;
		}
	}
	
	public void settaAttaccante(String item){
		if(k==1){
		a = (TextView) getView().findViewById(R.id.a1);
		a.setText(item);
		k++;
		}else if(k==2){
			a = (TextView) getView().findViewById(R.id.a2);
			a.setText(item);
			k++;
		}else if(k==3){
			a= (TextView) getView().findViewById(R.id.a3);
			a.setText(item);
			k=1;
		}
		}
}
			
			
	


