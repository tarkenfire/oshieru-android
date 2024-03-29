/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * The Class VocabMasterListFragment.
 */
public class VocabMasterListFragment extends ListFragment
{
	LinkedHashMap<String, JSONArray> vocabSets;
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_vocab_list, null);
	}
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View view, int position, long id)
	{
		super.onListItemClick(l, view, position, id);
		TextView tv = (TextView)view.findViewById(android.R.id.text1);
		String key = tv.getText().toString();
		
		JSONArray setToSend = vocabSets.get(key);
		
		Intent sendingIntent = new Intent(getActivity(), VocabDetailListActivity.class);
		sendingIntent.putExtra("set_name", key);
		sendingIntent.putExtra("set_list", setToSend.toString());
		
		getActivity().startActivity(sendingIntent);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		populateData();
	}

	/**
	 * Populate data into the vocabulary list.
	 */
	private void populateData()
	{
		try
		{
			JSONObject jsonObject = new JSONObject(getJSONFromAssets());
			JSONArray sets = jsonObject.getJSONArray("vocab_sets");
			
			vocabSets = new LinkedHashMap<String, JSONArray>();
			
			
			//this is a bit convoluted, but will save me pain when I go to the next activity.
			for (int i = 0; i < sets.length(); i++)
			{
				JSONObject set = sets.getJSONObject(i);
				vocabSets.put(set.getString("name"), set.getJSONArray("vocab"));
			}
			
			ArrayList<String> listNames = new ArrayList<String>();
			
			for (Map.Entry<String, JSONArray> entry : vocabSets.entrySet())
			{
				listNames.add(entry.getKey());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
					android.R.layout.simple_list_item_1, listNames);
			
			this.setListAdapter(adapter);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return;
		}
		
	}
	
	
	
	/**
	 * Gets the JSON file from assets.
	 *
	 * @return the JSON string from assets
	 */
	private String getJSONFromAssets()
	{
		String JSONString = "";
		try
		{
			InputStream is = getActivity().getAssets().open("vocab.json");
			
			int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();

	        JSONString = new String(buffer, "UTF-8");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return JSONString;
	}
	
}
