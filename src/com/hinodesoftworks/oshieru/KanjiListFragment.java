/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * The Class KanjiListFragment.
 */
public class KanjiListFragment extends ListFragment
{
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{	
		return inflater.inflate(R.layout.fragment_character_list, null);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		CharacterListActivity activity = (CharacterListActivity) this.getActivity();
		KanjiCursorAdapter adapter = new KanjiCursorAdapter(activity, 
				activity.getCursor(CharacterListActivity.FLAG_KANJI) , false);
		
		this.setListAdapter(adapter);	
	}	

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
		KanjiCursorAdapter adp = (KanjiCursorAdapter) this.getListAdapter();
		Cursor cursorToSend = adp.getCursor();
		Intent sendingIntent = new Intent(getActivity(), CharacterDetailActivity.class);
		
		sendingIntent.putExtra("char", cursorToSend.getString(1));
		sendingIntent.putExtra("kun", cursorToSend.getString(2));
		sendingIntent.putExtra("on", cursorToSend.getString(3));
		sendingIntent.putExtra("radical", cursorToSend.getString(4));
		sendingIntent.putExtra("defs", cursorToSend.getString(5));
		
		getActivity().startActivity(sendingIntent);
		
	}
}
