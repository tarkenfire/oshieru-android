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

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CharacterListActivity extends Activity
{	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_list);
		
		
		ListFragment hiraFrag = (ListFragment) this.getFragmentManager().findFragmentById(R.id.fragment_hira_list);
		DatabaseHelper helper = new DatabaseHelper(this);
		helper.openDatabase();
		SQLiteDatabase database = helper.getDatabase();
		DatabaseManager manager = new DatabaseManager(database);
		
		Cursor hiraCursor = manager.querySingleTableData("hiragana");
		KanaCursorAdapter adapter = new KanaCursorAdapter(this, hiraCursor, false);
		
		hiraFrag.setListAdapter(adapter);
		
		
	}

}
