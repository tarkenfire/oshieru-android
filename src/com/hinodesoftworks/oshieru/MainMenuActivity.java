/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * The Class MainMenuActivity.
 */
public class MainMenuActivity extends Activity
{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		getActionBar().setTitle("Oshieru - Main Menu");
	}
	
	/**
	 *  On click callback for button in this activity's fragment.
	 *
	 * @param v the button clicked
	 */
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.button_characters:
				Intent ci = new Intent(this, CharacterMenuActivity.class);
				startActivity(ci);
				return;
			case R.id.button_grammar:
				Intent gi = new Intent(this, GrammarMenuActivity.class);
				startActivity(gi);
				return;
			case R.id.button_vocabulary:
				Intent vi = new Intent(this, VocabMasterListActivity.class);
				startActivity(vi);
				return;
		}
	}
}
