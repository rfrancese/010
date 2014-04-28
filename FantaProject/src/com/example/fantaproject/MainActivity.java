package com.example.fantaproject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.example.fantandroid.R;
import com.example.fantaproject.Page3Fragment.OnPageListener;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnPageListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    PagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    	
    ViewPager mViewPager;
    List<Fragment> fragments = new Vector<Fragment>();
    TextView tv;
    private static final int PICK_CONTACT=131072;
    String phoneNumber;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
     // creating fragments and adding to list
        fragments.add(Fragment.instantiate(this,Page1Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this,Page2Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this,Page3Fragment.class.getName()));
     

        this.mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        

        
    }
    
   
    
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
    	super.onActivityResult(reqCode, resultCode, data);
 
    	if(reqCode == PICK_CONTACT && resultCode == Activity.RESULT_OK){
    			Cursor cursor = managedQuery(data.getData(),null,null,null,null);
    			while(cursor.moveToNext()){
    			String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
    			Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+contactId,null,null);
    				while (phones.moveToNext()) 
    				{
    					phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
    				}
    				
    	        phones.close();
    			SmsManager smanager = SmsManager.getDefault();
    			//String s = data.getStringExtra("formazioneTitolare");
    			Page2Fragment f2 = (Page2Fragment) fragments.get(1);
				smanager.sendTextMessage(phoneNumber,null,f2.formazioneTitolare,null,null);
				Toast t2 = Toast.makeText(this,"Messaggio inviato", Toast.LENGTH_SHORT);			
				t2.show();
    			}
    	}
    	
    }
    
   

    
    
    
    
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	public void settaGiocatore(String s,int position) {
    	//Page2Fragment f2 = (Page2Fragment) mSectionsPagerAdapter.getItem(1);
    	fragments = getSupportFragmentManager().getFragments();
    	Page2Fragment f2 = (Page2Fragment) fragments.get(1);

    	mViewPager.setCurrentItem(1);
		View v2 = f2.getView();
        if(v2 != null){
			if(position < 3)
				f2.settaPortiere(s);
			else if((position > 2) &&  (position < 11))
				f2.settaDifensore(s);
			else if((position > 10) && (position <19))
				f2.settaCentrocampista(s);
			else if((position > 18) && (position <25))
				f2.settaAttaccante(s);
        }
        
   
    }
    
    protected void onSaveInstanceState(Bundle outState){
    	super.onSaveInstanceState(outState);
    }
    
    
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    
 public class PagerAdapter extends FragmentPagerAdapter {
    	
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        

		@Override
        public Fragment getItem(int position) {
                 return fragments.get(position);
             }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
          
		@Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
		
		
       }
}

       
        
    

        
    
   

    
    

    
   

    	
   



	

