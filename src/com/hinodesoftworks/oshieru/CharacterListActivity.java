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

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CharacterListActivity extends Activity
{	
	ViewPager viewPager;
	CharPagerAdapter charPagerAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_list);	
		
		charPagerAdapter = new CharPagerAdapter(getFragmentManager());
		
		final ActionBar actionBar = this.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		
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
		

	    for (int i = 0; i < 3; i++) 
	    {
	        actionBar.addTab(actionBar.newTab());
	    }

		

	}
	
	
	private class CharPagerAdapter extends FragmentPagerAdapter
	{

		public CharPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			
			//tabs have fixed positions.
			
			HiraganaListFragment hlf = new HiraganaListFragment();
			return hlf;
		
		}

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			// TODO Auto-generated method stub
			return "Tab";
		}
		
		
		
	}

}
