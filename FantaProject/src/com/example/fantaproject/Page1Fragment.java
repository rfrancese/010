package com.example.fantaproject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class Page1Fragment extends Fragment{
	
	TextView tv;
	private ProgressBar progressBar;

	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		if(container == null)
			return null;
		
		View view = inflater.inflate(R.layout.fragment_formazioni,container,false);
		tv=(TextView) view.findViewById(R.id.textView1);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
		tv.setMovementMethod(new ScrollingMovementMethod());
		new LoadFormazioniTask().execute();
		return view;
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
			
			StringBuffer sb = new StringBuffer();
			  
	        try
	        {
	           List<? extends TagNode> DivScoreProbabili = rootNode.getElementListByAttValue("class","score-probabili",true,true);
	     
	           int i=0;
	           int j=0;
	           sb.append(">>> GIORNATA SERIE A\n");
	           Object[] formazioniCasa = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='in']/div[@class='name']/a");
	           Object[] formazioniFuori = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='out']/div[@class='name']/a");

	            for (Iterator<? extends TagNode> iterator = DivScoreProbabili.iterator(); iterator.hasNext();)
	            {
	               TagNode Element = (TagNode) iterator.next();
	                TagNode squadraCasa = Element.findElementByAttValue("class","team-in-p",true,true);
	                TagNode squadraFuori = Element.findElementByAttValue("class","team-out-p",true,true);
	                sb.append(squadraCasa.getText()+" - "+squadraFuori.getText()+" "+"\n");
	                if(i%11==0){
	                	TagNode fCasa = (TagNode) formazioniCasa[i];
	                    sb.append(fCasa.getText()+"\n");
	                    i++;
	                }
	                while(i%11 != 0){
	                TagNode fCasa = (TagNode) formazioniCasa[i];
	                sb.append(fCasa.getText()+"\n");
	                i++;
	                }
	                sb.append("****************************************************\n");
	                
	                if(j%11==0){
	                	TagNode CalciatoreCasa = (TagNode) formazioniFuori[j];
	                    sb.append(CalciatoreCasa.getText()+"\n");
	                    j++;
	                }
	                while(j%11 != 0){
	                TagNode calciatoreFuori = (TagNode) formazioniFuori[j];
	                sb.append(calciatoreFuori.getText()+"\n");
	                j++;
	                }
	                
	                
	            }
	            return sb.toString();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	    
			
			
			return null;
		}
		

		@Override
		protected void onPostExecute(String result) {

			
			progressBar.setVisibility(ProgressBar.INVISIBLE);
			tv.setText(result);

		}

		

		
	}
	
}
