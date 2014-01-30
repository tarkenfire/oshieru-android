/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * The Class VocabSetArrayAdapter.
 */
public class VocabSetArrayAdapter extends BaseAdapter
{
	
	private JSONArray items;
	private Context ctx;
	
	/**
	 * Instantiates a new vocab set array adapter.
	 *
	 * @param context the context
	 * @param array the array
	 */
	public VocabSetArrayAdapter(Context context, JSONArray array)
	{
		this.ctx = context;
		this.items = array;
	}
	
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		
		return items.length();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		try
		{
			return items.get(position);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(ctx);
			convertView = inflater.inflate(R.layout.listview_vocab, parent, false);
		}
		
		TextView termView = (TextView)convertView.findViewById(R.id.vocab_term);
		TextView defineView = (TextView)convertView.findViewById(R.id.vocab_define);
		
		JSONObject term = (JSONObject) getItem(position);
		
		if (term != null)
		{
			try
			{
				termView.setText(term.getString("term"));
				defineView.setText(term.getString("def"));
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		return convertView;
	}

}
