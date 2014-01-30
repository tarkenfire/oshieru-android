/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import com.hinodesoftworks.utils.DatabaseManager;
import com.hinodesoftworks.utils.DatabaseHelper;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ActionBar.TabListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * The Class CharacterListActivity.
 */
public class CharacterListActivity extends Activity implements TabListener
{	
	ViewPager viewPager;
	CharPagerAdapter charPagerAdapter;
	DatabaseManager databaseManager;
	DatabaseHelper databaseHelper;
	
	
	public static final int FLAG_HIRA = 0;
	public static final int FLAG_KATA = 1;
	public static final int FLAG_KANJI = 2;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_list);	
		
		charPagerAdapter = new CharPagerAdapter(getFragmentManager());
		
		final ActionBar actionBar = this.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setTitle("Oshieru - Character Lists");
		
		
		viewPager = (ViewPager)findViewById(R.id.char_pager);
		viewPager.setAdapter(charPagerAdapter);
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});

        Tab tabToAdd = actionBar.newTab();
        tabToAdd.setText("Hiragana");
        tabToAdd.setTabListener(this);
        actionBar.addTab(tabToAdd);
	    
        tabToAdd = actionBar.newTab();
        tabToAdd.setText("Katakana");
        tabToAdd.setTabListener(this);
        actionBar.addTab(tabToAdd);
        
        tabToAdd = actionBar.newTab();
        tabToAdd.setText("Kanji");
        tabToAdd.setTabListener(this);
        actionBar.addTab(tabToAdd);
		
        //get database manager instance.
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();
        SQLiteDatabase database = databaseHelper.getDatabase();
        databaseManager = new DatabaseManager(database);
	}
	

	/* (non-Javadoc)
	 * @see android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
		//do nothing
	}


	/* (non-Javadoc)
	 * @see android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		viewPager.setCurrentItem(tab.getPosition());

	}


	/* (non-Javadoc)
	 * @see android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
		//do nothing
	}

	
	/**
	 * Gets a cursor from the database depending on the flag given to it.
	 *
	 * @param flag the flag to query an individual database table
	 * @return the cursor from the database
	 */
	public Cursor getCursor(int flag)
	{
		switch (flag)
		{
			case FLAG_HIRA:
				return databaseManager.querySingleTableData("hiragana");
			case FLAG_KATA:
				return databaseManager.querySingleTableData("katakana");
			case FLAG_KANJI:
				return databaseManager.querySingleTableData("kanji_g1");
			default:
				throw new IllegalStateException();
		}
		
	}
	
	
	/**
	 * The Class CharPagerAdapter.
	 */
	private class CharPagerAdapter extends FragmentPagerAdapter
	{

		/**
		 * Instantiates a new char pager adapter.
		 *
		 * @param fm the fragment manager for the pager
		 */
		public CharPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		/* (non-Javadoc)
		 * @see android.support.v13.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int position)
		{
			
			switch (position)
			{
				case 0:
					HiraganaListFragment hlf = new HiraganaListFragment();
					return hlf;
				case 1:
					KatakanaListFragment klf = new KatakanaListFragment();
					return klf;
				case 2:
					KanjiListFragment kjlf = new KanjiListFragment();
					return kjlf;
				default:
					throw new IllegalStateException();
			}
		}

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount()
		{
			//Static number of tabs.
			return 3;
		}
		
		
	}


}
