/**
 *  Created on  : 22/02/2013
 *  Author      : M. en C. Javier Silva Pérez - [javier]
 *  Description :
 *  	This Fragment includes all the activity operations, like: 
 *  <ul> 
 *  <li> Change default tabs for list
 *  <li> Active split menu or show its options in action bar
 *  </ul>
 */
package com.androidtitlan.retrocompatibilitytutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * This Fragment includes all the activity operations, like:
 * <ul>
 * <li>Change default tabs for list
 * <li>Active split menu or show its options in action bar
 * </ul>
 * 
 * @author M. en C. Javier Silva Perez - [javier]
 * @since 22/02/2013
 * @version 1.0
 */
public class OptionsFragment extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_options, container,
				false);

		// Get spinner from view
		Spinner spinnerNavigationType = (Spinner) rootView
				.findViewById(R.id.spinnerNavigationType);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.navigationType,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerNavigationType.setAdapter(adapter);

		spinnerNavigationType
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View v,
							int pos, long id) {
						// An item was selected. You can retrieve the selected
						// item using

						// If pos = 0 means that tabs options was selected
						if (pos == 0) {
							((InitialActivity) getActivity()).showTabsNav();
						} else if (pos == 1) {
							// If pos == 1 list option was selected
							((InitialActivity) getActivity()).showDropDownNav();
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});

		// Show or hide home button depending on check box value
		CheckBox chkUp = (CheckBox) rootView.findViewById(R.id.chkUp);

		// Add chang
		chkUp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// Get Fragments activity and parse it
				((SherlockFragmentActivity) getActivity())
						.getSupportActionBar().setDisplayHomeAsUpEnabled(
								isChecked);

			}
		});

		return rootView;
	}

}
