package com.example.fantaproject;


import java.util.ArrayList;

import com.example.fantandroid.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

  private final SparseArray<Group> groups;
  public LayoutInflater inflater;
  public Activity activity;

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
public MyExpandableListAdapter(Page1Fragment page1Fragment, SparseArray<Group> groups,LayoutInflater inflater) {
    activity = page1Fragment.getActivity();
    this.groups = groups;
    this.inflater = inflater;
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return groups.get(groupPosition).children.get(childPosition);
  }
  
  public Object getChild2(int groupPosition, int childPosition) {
	    return groups.get(groupPosition).children2.get(childPosition);
	  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public View getChildView(int groupPosition, final int childPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    final String children = (String) getChild(groupPosition, childPosition);
    final String children2 = (String) getChild2(groupPosition, childPosition);

    TextView text = null;
    TextView text2 = null;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listrow_details, null);
    }
    text = (TextView) convertView.findViewById(R.id.giocatoreCasa);
    text.setText(children);
    text2 = (TextView) convertView.findViewById(R.id.giocatoreFuori);
    text2.setText(children2);
    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(activity, children,
            Toast.LENGTH_SHORT).show();
      }
    });
    return convertView;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return groups.get(groupPosition).children.size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return groups.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groups.size();
  }

  @Override
  public void onGroupCollapsed(int groupPosition) {
    super.onGroupCollapsed(groupPosition);
  }

  @Override
  public void onGroupExpanded(int groupPosition) {
    super.onGroupExpanded(groupPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listrow_group, null);
    }
    Group group = (Group) getGroup(groupPosition);
    CheckedTextView cw= (CheckedTextView) convertView.findViewById(R.id.p);
    Drawable img = parent.getContext().getResources().getDrawable(caricaScudetto(group.string));
    img.setBounds(0,0,100,100);
    cw.setCompoundDrawables( img, null, null, null );
    cw.setText(group.string);
    cw.setChecked(isExpanded);
    TextView tw = (TextView) convertView.findViewById(R.id.squadraFuori);
    ImageView iw = (ImageView) convertView.findViewById(R.id.scudettoSquadraFuori);
    iw.setImageResource(caricaScudetto(group.string2));
    tw.setText(group.string2);
    return convertView;
  }
  
  public int caricaScudetto(String squadra){
	 if(squadra.equalsIgnoreCase("napoli"))
		 return R.drawable.napoli;
	 if(squadra.equalsIgnoreCase("roma"))
		 return R.drawable.roma; 
	 if(squadra.equalsIgnoreCase("juventus"))
			 return R.drawable.juventus;
	 if(squadra.equalsIgnoreCase("udinese"))
		 return R.drawable.udinese;
	 if(squadra.equalsIgnoreCase("genoa"))
		 return R.drawable.genoa;
	 if(squadra.equalsIgnoreCase("bologna"))
		 return R.drawable.bologna;
	 if(squadra.equalsIgnoreCase("inter"))
		 return R.drawable.inter;
	 if(squadra.equalsIgnoreCase("verona hellas"))
		 return R.drawable.verona;
	 if(squadra.equalsIgnoreCase("lazio"))
		 return R.drawable.lazio;
	 if(squadra.equalsIgnoreCase("sampdoria"))
		 return R.drawable.sampdoria;
	 if(squadra.equalsIgnoreCase("catania"))
		 return R.drawable.catania;
	 if(squadra.equalsIgnoreCase("fiorentina"))
		 return R.drawable.fiorentina;
	 if(squadra.equalsIgnoreCase("parma"))
		 return R.drawable.parma;
	 if(squadra.equalsIgnoreCase("cagliari"))
		 return R.drawable.cagliari;
	 if(squadra.equalsIgnoreCase("chievo"))
		 return R.drawable.chievo;
	 if(squadra.equalsIgnoreCase("milan"))
		 return R.drawable.milan;
	 if(squadra.equalsIgnoreCase("sassuolo"))
		 return R.drawable.sassuolo; 
	 if(squadra.equalsIgnoreCase("torino"))
			 return R.drawable.torino;
	 if(squadra.equalsIgnoreCase("atalanta"))
		 return R.drawable.atalanta;
	 if(squadra.equalsIgnoreCase("livorno"))
		 return R.drawable.livorno;
	
	 
	 return R.drawable.ic_launcher;
	 
		 
	  
	  }
  

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
} 

