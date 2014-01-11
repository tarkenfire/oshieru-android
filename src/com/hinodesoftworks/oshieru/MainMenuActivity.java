/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.button_characters:
				Intent i = new Intent(this, CharacterMenuActivity.class);
				startActivity(i);
				
				return;
			case R.id.button_grammar:
				return;
				
			case R.id.button_vocabulary:
				return;
		}
	}
}
