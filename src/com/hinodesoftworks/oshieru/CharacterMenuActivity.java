/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The Class CharacterMenuActivity.
 */
public class CharacterMenuActivity extends Activity
{
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_menu);
		getActionBar().setTitle("Oshieru - Characters");
	}
	
	/**
	 * On click callback for button in this activity's fragment.
	 *
	 * @param v the button pressed
	 */
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.button_char_list:
			Intent cli = new Intent(this, CharacterListActivity.class);
			startActivity(cli);
			break;
		case R.id.button_char_quiz:
			Intent cqi = new Intent(this, CharacterQuizSetupActivity.class);
			startActivity(cqi);
			break;
		}
	}

}
