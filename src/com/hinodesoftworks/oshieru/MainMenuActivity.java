/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import com.hinodesoftworks.utils.DatabaseHelper;
import com.hinodesoftworks.utils.DatabaseManager;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
