package com.androidtitlan.retrocompatibilitytutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * Main activity for retrocompatibility on action bar and view pager tutorial
 * 
 * @author M. en C. Javier Silva Perez - [javier]
 * @since 22/02/2013
 * @version 1.0
 */
public class InitialActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the three primary sections of the app. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	private String[] sections;

	public static final String TAG = "COMPATIBILITY_TUTORIAL";

	/**
	 * The {@link ViewPager} that will display the primary sections of the app,
	 * one at a time. Using this we could use swipe for change between tabs
	 */
	ViewPager mViewPager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		sections = getResources().getStringArray(R.array.sections);
		// Set up the action bar.
		final ActionBar ab = getSupportActionBar();
		// ab.setDisplayHomeAsUpEnabled(Boolean.TRUE);
		ab.setSubtitle(sections[0]);
		ab.setDisplayShowTitleEnabled(true);

		// Set up the ViewPager, attaching the adapter and setting up a listener
		// for when the user swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections, select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this if
						// we have a reference to the
						// Tab.
						ab.setSubtitle(sections[position]);
						ab.setSelectedNavigationItem(position);
					}
				});

		// Add tabs to view
		ab.addTab(ab.newTab().setText(sections[0])
				.setIcon(R.drawable.ic_menu_options).setTabListener(this));
		ab.addTab(ab.newTab().setText(sections[1])
				.setIcon(R.drawable.ic_menu_collection).setTabListener(this));
		ab.addTab(ab.newTab().setText("").setIcon(R.drawable.ic_menu_other)
				.setTabListener(this));

		// set up list navigation
		ab.setListNavigationCallbacks(ArrayAdapter.createFromResource(this,
				R.array.sections, R.layout.sherlock_spinner_dropdown_item),
				new OnNavigationListener() {
					public boolean onNavigationItemSelected(int itemPosition,
							long itemId) {
						// When an option is selected, update view pager
						// position
						mViewPager.setCurrentItem(itemPosition);
						return false;
					}
				});

		// default to tab navigation
		showTabsNav();
		ab.setSelectedNavigationItem(0);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			//This is called when home button is pressed
			Toast.makeText(this, "Home Button", Toast.LENGTH_LONG).show();
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Change navigation type to tabs
	 */
	public void showTabsNav() {
		ActionBar ab = getSupportActionBar();
		if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
			ab.setDisplayShowTitleEnabled(true);
			ab.setSubtitle(sections[0]);
			ab.setTitle(getString(R.string.app_name));
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}
	}

	/**
	 * Change navigation type to list
	 */
	public void showDropDownNav() {
		ActionBar ab = getSupportActionBar();
		if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_LIST) {
			ab.setDisplayShowTitleEnabled(false);
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main_menu, menu);

		return super.onCreateOptionsMenu(menu);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.actionbarsherlock.app.ActionBar.TabListener#onTabSelected(com.
	 * actionbarsherlock.app.ActionBar.Tab,
	 * android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.actionbarsherlock.app.ActionBar.TabListener#onTabUnselected(com.
	 * actionbarsherlock.app.ActionBar.Tab,
	 * android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.actionbarsherlock.app.ActionBar.TabListener#onTabReselected(com.
	 * actionbarsherlock.app.ActionBar.Tab,
	 * android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public class AppSectionsPagerAdapter extends FragmentStatePagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				// Fragment for Option tab
				return new OptionsFragment();
			case 1:
				// Fragment for collection tab
				return new CollectionFragment();

			case 2:
				// Fragment for dummy
				return new DummyFragment();

			default:
				return new DummyFragment();
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

}
