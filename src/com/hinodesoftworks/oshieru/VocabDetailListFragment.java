/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VocabDetailListFragment extends ListFragment
{
	TextView header;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_vocab_detail, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		Bundle dataBundle = getActivity().getIntent().getExtras();
		JSONArray dataArray = null;
		
		try
		{
			dataArray = new JSONArray(dataBundle.getString("set_list"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		
		VocabSetArrayAdapter adapter = new VocabSetArrayAdapter(getActivity(), dataArray); 
		this.setListAdapter(adapter);
		
		getActivity().getActionBar().setTitle("Oshieru - " + dataBundle.getString("set_name"));
		header = (TextView)getActivity().findViewById(R.id.vocab_detail_header);
		header.setText(dataBundle.getString("set_name"));
		
	}
	
	
}
