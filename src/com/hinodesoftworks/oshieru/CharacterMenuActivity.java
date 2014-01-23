/* 
 * Date: Jan 11, 2014
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

public class CharacterMenuActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_menu);
		
		//TODO: Get Title from resource rather than hard code
		getActionBar().setTitle("Oshieru - Characters");
	}
	
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
