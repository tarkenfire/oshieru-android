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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CharacterListActivity extends Activity implements OnItemClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_list);
		
		//TODO Static placeholder layout/code.
		//TODO FRAGMENT THIS. FRAGMENT THIS. FRAGMENT THIS.
		ListView testListView = (ListView)findViewById(R.id.placeholder_listview);
		String[] placeholderArray = {"Example Character", "Example Character", "Example Character", "Example Character", "Example Character", "Example Character"};
		ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholderArray);
		testListView.setAdapter(testAdapter);
		testListView.setOnItemClickListener(this);
	}
	
	//TODO REMOVE. FRAGMENT.
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		Intent i = new Intent(this, CharacterDetailActivity.class);
		startActivity(i);
	}
}
