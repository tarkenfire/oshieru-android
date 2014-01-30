/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */

package com.hinodesoftworks.oshieru;

import java.util.ArrayList;

import com.hinodesoftworks.utils.ConjugationItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * The Class ConjugationItemAdapter.
 */
public class ConjugationItemAdapter extends BaseAdapter
{
	private ArrayList<ConjugationItem> items;
	private Context ctx;
	
	/**
	 * Instantiates a new conjugation item adapter.
	 *
	 * @param context the activity context
	 * @param items the items managed by the adapter
	 */
	public ConjugationItemAdapter(Context context, ArrayList<ConjugationItem> items)
	{
		this.items = items;
		this.ctx = context;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return items.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		return items.get(position);
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
			convertView = inflater.inflate(R.layout.listview_conjugation, parent, false);
		}
		
		TextView formView = (TextView)convertView.findViewById(R.id.conj_form_label);
		TextView formTypeView = (TextView)convertView.findViewById(R.id.conj_type_label);
		
		ConjugationItem item = (ConjugationItem) this.getItem(position);
		
		formTypeView.setText(item.getConjugationType());
		formView.setText(item.getConjugatedForm());
		
		return convertView;
	}

}
