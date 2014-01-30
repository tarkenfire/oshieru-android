/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * The Class CharacterDetailFragment.
 */
public class CharacterDetailFragment extends Fragment
{
	TextView charView;
	TextView defView;
	TextView radicalView;
	TextView kunView;
	TextView onView;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_character_detail, null);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		Activity ref = getActivity();
		Bundle dataBundle = ref.getIntent().getExtras();
		
		charView = (TextView) ref.findViewById(R.id.kanji_detail_char);
		defView = (TextView) ref.findViewById(R.id.kanji_detail_defs);
		radicalView = (TextView) ref.findViewById(R.id.kanji_detail_radical);
		kunView = (TextView) ref.findViewById(R.id.kanji_detail_kun);
		onView = (TextView) ref.findViewById(R.id.kanji_detail_on);
		
		charView.setText(dataBundle.getString("char"));
		defView.setText(dataBundle.getString("defs"));
		radicalView.setText(dataBundle.getString("radical"));
		kunView.setText(dataBundle.getString("kun"));
		onView.setText(dataBundle.getString("on"));
		
		
	}
	
	
	
}
