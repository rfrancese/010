package com.example.fantaproject;

import com.example.fantandroid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Page2Fragment extends Fragment{
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		if(container == null)
			return null;
					
		View view = inflater.inflate(R.layout.fragment_squadra,container,false);
		return view;
	}

}
