package com.example.fantaproject;

import com.example.fantandroid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Page2Fragment extends Fragment{
	
	TextView d;
	TextView c;
	TextView a;
	int i=1;
	int j=1;
	int k=1;
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		if(container == null)
			return null;
					
		View view = inflater.inflate(R.layout.fragment_squadra,container,false);
		return view;
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
			
			
	


