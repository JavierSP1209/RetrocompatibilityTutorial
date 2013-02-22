package com.androidtitlan.retrocompatibilitytutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * Main activity for retrocompatibility on action bar and view pager tutorial
 * 
 * @author M. en C. Javier Silva Pérez - [javier]
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

	private SparseArray<String> TITLES;
	private static final Integer OPTIONS_FRAGMENT = 0;
	private static final Integer COLLECTION_FRAGMENT = 1;
	private static final Integer OTHER_FRAGMENT = 2;

	public static final String TAG = "COMPATIBILITY_TUTORIAL";

	/**
	 * The {@link ViewPager} that will display the primary sections of the app,
	 * one at a time.
	 */
	ViewPager mViewPager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);

		fillSubtitlesArray();
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar ab = getSupportActionBar();
		// ab.setDisplayHomeAsUpEnabled(Boolean.TRUE);
		ab.setSubtitle(TITLES.get(OPTIONS_FRAGMENT));
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
						ab.setSubtitle(TITLES.get(position));
						ab.setSelectedNavigationItem(position);
					}
				});

		ab.addTab(ab.newTab().setText(R.string.subtitle_options)
				.setIcon(R.drawable.ic_menu_options).setTabListener(this));
		ab.addTab(ab.newTab().setText(R.string.subtitle_collection)
				.setIcon(R.drawable.ic_menu_collection).setTabListener(this));
		ab.addTab(ab.newTab().setText(R.string.subtitle_other)
				.setIcon(R.drawable.ic_menu_other).setTabListener(this));

		// default to tab navigation
		showTabsNav();
		ab.setSelectedNavigationItem(0);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case android.R.id.home:
			// TODO handle clicking the app icon/logo
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showTabsNav() {
		ActionBar ab = getSupportActionBar();
		if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}
	}

	/**
	 * Fills the subtitle array with the resource strings, so it would be
	 * multilanguage
	 */
	private void fillSubtitlesArray() {
		TITLES = new SparseArray<String>();
		TITLES.put(OPTIONS_FRAGMENT,
				getResources().getString(R.string.subtitle_options));
		TITLES.put(COLLECTION_FRAGMENT,
				getResources().getString(R.string.subtitle_collection));
		TITLES.put(OTHER_FRAGMENT,
				getResources().getString(R.string.subtitle_other));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getSupportMenuInflater().inflate(R.menu.main_menu, menu);

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
				// Fragment of the app is the Key Management.
				return new OptionsFragment();
			case 1:
				// Fragment of the app is the Certificate Management.
				return new OptionsFragment();

			case 2:
				// Fragment of the app is the Trust Network Management.
				return new OptionsFragment();

			

			default:
				return new OptionsFragment();
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	}
}
