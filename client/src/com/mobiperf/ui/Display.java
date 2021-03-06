/****************************
 * This file is part of the MobiPerf project (http://mobiperf.com). 
 * We make it open source to help the research community share our efforts.
 * If you want to use all or part of this project, please give us credit and cite MobiPerf's official website (mobiperf.com).
 * The package is distributed under license GPLv3.
 * If you have any feedbacks or suggestions, don't hesitate to send us emails (3gtest@umich.edu).
 * The server suite source code is not included in this package, if you have specific questions related with servers, please also send us emails
 * 
 * Contact: 3gtest@umich.edu
 * Development Team: Junxian Huang, Birjodh Tiwana, Zhaoguang Wang, Zhiyun Qian, Cheng Chen, Yutong Pei, Feng Qian, Qiang Xu
 * Copyright: RobustNet Research Group led by Professor Z. Morley Mao, (Department of EECS, University of Michigan, Ann Arbor) and Microsoft Research
 *
 ****************************/

package com.mobiperf.ui;
import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.mobiperf.R;



/**
 * Summary
 *
 * @author breath
 * 
 * BASIC INFO:
 * 	Device ID(1)
 * 	Network type(1)
 * 	Carrier(1)
 * 	Cell ID(1)
 * 	Location Area Code(1)
 * 	GPS info(1)
 * 	local IP(1)
 * 	Global IP(1)
 *
 *Performance INFO
 *	Local DNS server Status(1)
 *	Local DNS server latency(1)
 *	Signal strength
 *	Downlink throughput 
 * 	Uplink throughput 
 *
 *Policy
 *	Firewall allows IP spoofing
 *	DNS to external server allowed
 *	Blocked ports(4)
 *	Allowed ports(4)
 */

public class Display extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.display1);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Basic.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Basic").setIndicator("Basic",
	                      res.getDrawable(R.layout.basic))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, Performance.class);
	    spec = tabHost.newTabSpec("Perf").setIndicator("Performance",
	                      res.getDrawable(R.layout.performance))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, Policy.class);
	    spec = tabHost.newTabSpec("Policy").setIndicator("Policy",
	                      res.getDrawable(R.layout.policy))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}
	/******************** Menu starts here by cc ********************/
    // Define menu ids
	protected static final int NEW_TEST = Menu.FIRST;
	//TODO:new menu --------cc
	protected static final int PAST_RECORD = Menu.FIRST +5;
	// Create menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	        super.onCreateOptionsMenu(menu);

	        menu.add(0, NEW_TEST, 0, "New Test");
	      //TODO:new menu --------cc
	        menu.add(0, PAST_RECORD, 0, "View past record");
	        return true;
	}
	// Deal with menu event
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        Log.v("menu","onOptionsItemSelected "+item.getItemId());
	        switch (item.getItemId()) {
	       
	        case NEW_TEST:
	        	Intent i = new Intent(this, com.mobiperf.Main.class);
				 startActivityForResult(i, 0);
                break;
                
            //TODO:new menu --------cc
	        case PAST_RECORD:
	        	 Intent in = new Intent(this, com.mobiperf.ui.HistoricalList.class);
				 startActivityForResult(in, 0);
				 break;
	        }

	        return true;

	}
	
	public static ArrayList<String> titles_basic = new ArrayList<String>();
	public static ArrayList<String> description_basic = new ArrayList<String>();
	public static ArrayList<String> result_basic = new ArrayList<String>();
	
	public static ArrayList<String> titles_performance = new ArrayList<String>();
	public static ArrayList<String> description_performance = new ArrayList<String>();
	public static ArrayList<String> result_performance = new ArrayList<String>();
	
	public static ArrayList<String> titles_policy = new ArrayList<String>();
	public static ArrayList<String> description_policy = new ArrayList<String>();
	public static ArrayList<String> result_policy = new ArrayList<String>();
    
	/**
	 * 
	 * @param title
	 * @param description
	 * @param result
	 * @param tab 0: basic, 1: performance, 2: policy
	 */
	public static void displayResult(String title, String description, String result, int tab){
		if(tab == 0){
			titles_basic.add(title);
			description_basic.add(description);
			result_basic.add(result);
		}
		else if (tab == 1){
			titles_performance.add(title);
			description_performance.add(description);
			result_performance.add(result);
			
		}
		else if(tab == 2){
			titles_policy.add(title);
			description_policy.add(description);
			result_policy.add(result);
		}
	}
	
	public static void clearTabView(){
		titles_basic = new ArrayList<String>();
    	description_basic = new ArrayList<String>();
    	result_basic = new ArrayList<String>();
    	
        titles_performance = new ArrayList<String>();
    	description_performance = new ArrayList<String>();
    	result_performance = new ArrayList<String>();
    	
    	titles_policy = new ArrayList<String>();
    	description_policy = new ArrayList<String>();
    	result_policy = new ArrayList<String>();
	}
	
	
}
