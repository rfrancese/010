package com.example.fantaproject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.util.Log;

public class HtmlParser {
	
	// oggetto TagNode radice del file html
	private TagNode rootNode;
	private String url_str = null;
	
	public HtmlParser(String url_str)
	{
		//URL di cui si vuole fare il parsing
		this.url_str = url_str;
		
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
			
			url = new URL(url_str);
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
	}
	
	
	
	
	public String stampa(String elementName)
    {
    	StringBuffer sb = new StringBuffer();
  
        try
        {
           List<? extends TagNode> DivScoreProbabili = rootNode.getElementListByAttValue("class","score-probabili",true,true);
          //  List<Object> DivMatch = rootNode.getElementListByAttValue("class","match",true,true);
         /*   Object[] formazioneCasa = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='in']/div[@class='name']/a");
            for(Object o:formazioneCasa){
            TagNode spanNode = (TagNode) o;
            sb.append(spanNode.getText()+"\n");
            }*/
           int i=0;
           int j=0;
           sb.append(">>> GIORNATA SERIE A\n");
           Object[] formazioniCasa = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='in']/div[@class='name']/a");
           Object[] formazioniFuori = rootNode.evaluateXPath("//div[@class='match']/div[@class='playerall']/div[@class='player']/div[@class='out']/div[@class='name']/a");

         //  Object[] squadraCasa = rootNode.evaluateXPath("//div[@class='score-probabili']/div[@class='team-in-p]'");
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
                sb.append("*****************************************************\n");
                
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
                
                
             /* for (Iterator<Object> iterator2 = DivMatch.iterator(); iterator2.hasNext();){
                TagNode Element2 = (TagNode) iterator2.next();
                	TagNode playerAll = Element2.findElementByAttValue("class","playerall",true,true);
                	TagNode player = playerAll.findElementByAttValue("class", "player", true, true);
                	TagNode in =player.findElementByAttValue("class","in",true,true);
                	TagNode name = in.findElementByAttValue("class","name",true,true);
                	TagNode a = name.findElementHavingAttribute("title",true);
                	sb.append(" ok\n");
                }*/
        
            
                
                
            
            return sb.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

	
}
