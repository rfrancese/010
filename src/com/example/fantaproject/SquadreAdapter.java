package com.example.fantaproject;

import java.util.ArrayList;

import com.example.fantandroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SquadreAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	  private final ArrayList<String> values;
	  private final ArrayList<String> values2;

	
	 public SquadreAdapter(Context context, ArrayList<String> values,ArrayList<String> values2) {
		    super(context, R.layout.rowlayout, values);
		    this.context = context;
		    this.values = values;
		    this.values2 = values2;
		  }
	 
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.squadraCasa);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.scudettoSquadraCasa);
	    TextView textView2 = (TextView) rowView.findViewById(R.id.squadraFuori);
	    textView.setText(values.get(position));
	    textView2.setText(values2.get(position));
	    
	    

	    return rowView;
	  }
	} 


