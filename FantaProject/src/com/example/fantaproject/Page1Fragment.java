package com.example.fantaproject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;


import com.example.fantandroid.R;

import android.graphics.Bitmap;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class Page1Fragment extends Fragment{
	
	private MyExpandableListAdapter adapter;
    private ProgressBar progressBar;
    View view;
	  SparseArray<Group> groups = new SparseArray<Group>();
	  
	 

	
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
    	 view = inflater.inflate(R.layout.fragment_formazioni,container,false);
    		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    	 new LoadFormazioniTask().execute();
		    ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.listView);
		    adapter = new MyExpandableListAdapter(this,groups,inflater);
		    adapter.notifyDataSetChanged();
		    listView.setAdapter(adapter);
    	return view;
    }
   
  
   
	 @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState); 
	  
	  }

	
	
	
	private class LoadFormazioniTask extends AsyncTask<Void, Void, String> {

		
		private static final String URL = "http://www.fantagazzetta.com/probabili-formazioni-serie-a";
		private TagNode rootNode;
		
		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(ProgressBar.VISIBLE);
		}
			
		@Override
		protected String doInBackground(Void... params) {
			
			// inizializzazione dell'oggetto HtmlCleaner utile a generare un html pulito
						HtmlCleaner cleaner = new HtmlCleaner();
						CleanerProperties props = cleaner.getProperties();
						props.setAllowHtmlInsideAttributes(true);
						props.setAllowMultiWordAttributes(true);
						props.setRecognizeUnicodeChars(true);
						props.setOmitComments(true);
						
						// apertura della connessione
						URL url;
						try {
							
							url = new URL(URL);
							URLConnection conn = url.openConnection();
							
							//ora utilizziamo l'oggetto cleaner per "ripulire" l'html e inizializzare l'oggetto rootNode
							rootNode = cleaner.clean(new InputStreamReader(conn.getInputStream()));
							
							
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							Log.e("Error", e.getMessage());
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Log.e("Error", e.getMessage());
						}
						return null;
		}
		

		protected void onPostExecute(String result) {
			
				  
		        try
		        {
		           List<? extends TagNode> DivScoreProbabili = rootNode.getElementListByAttValue("class","score-probabili",true,true);
		     
		           int i=0;
		           int j=0;
		           int x=0;
		          
		           Object[] formazioniCasa = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='in']/div[@class='name']/a");
		           Object[] formazioniFuori = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='out']/div[@class='name']/a");
		           for (Iterator<? extends TagNode> iterator = DivScoreProbabili.iterator(); iterator.hasNext();)
		            {	
		               TagNode Element = (TagNode) iterator.next();
		                TagNode squadraCasa = Element.findElementByAttValue("class","team-in-p",true,true);
		                TagNode squadraFuori = Element.findElementByAttValue("class","team-out-p",true,true);
		                Group group = new Group(squadraCasa.getText().toString());
		                group.setSquadraFuori(squadraFuori.getText().toString());
		    	       groups.append(x, group);
		    	       x++;
		    	       if(i%11==0){
		                	TagNode fCasa = (TagNode) formazioniCasa[i];
		                    group.children.add(fCasa.getText().toString());
		                    TagNode fFuori = (TagNode) formazioniFuori[i];
		                    group.children2.add(fFuori.getText().toString());
		                    i++;
		                }
		                while(i%11 != 0){
		                TagNode fCasa = (TagNode) formazioniCasa[i];
	                    group.children.add(fCasa.getText().toString());
	                    TagNode fFuori = (TagNode) formazioniFuori[i];
	                    group.children2.add(fFuori.getText().toString());
		                i++;
		                }
		             
		            }
		        
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		        adapter.notifyDataSetChanged();
				progressBar.setVisibility(ProgressBar.GONE);

	        }
	}




	public static Fragment newInstance(int position) {
		Page1Fragment p1 = new Page1Fragment();
		 Bundle args = new Bundle();
	        args.putInt("val", position);
	        p1.setArguments(args);
		return p1;
	}
}
		

		

		
	
	

