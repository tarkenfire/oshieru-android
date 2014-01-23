/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ActionBar.TabListener;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class CharacterListActivity extends Activity implements TabListener
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
	        Tab tabToAdd = actionBar.newTab();
	        tabToAdd.setText("Tab");
	        tabToAdd.setTabListener(this);
	        actionBar.addTab(tabToAdd);
	    }

		

	}
	

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
		// TODO Auto-generated method stub
		
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
