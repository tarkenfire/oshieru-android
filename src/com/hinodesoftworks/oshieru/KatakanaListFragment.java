/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class KatakanaListFragment extends ListFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_character_list, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		CharacterListActivity activity = (CharacterListActivity) this.getActivity();
		KanaCursorAdapter adapter = new KanaCursorAdapter(activity, 
				activity.getCursor(CharacterListActivity.FLAG_KATA) , false);
		
		this.setListAdapter(adapter);
		
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		// Does Nothing
		super.onListItemClick(l, v, position, id);
	}
	
}
