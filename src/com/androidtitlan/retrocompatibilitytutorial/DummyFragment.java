/**
 * File: DummyFragment.java
 * CreationDate: 22/02/2013
 * Author: "M. en C. Javier Silva Perez (JSP)"
 * Description: 
 * A dummy fragment representing a section of the app, but that simply displays dummy text.
 */
package com.androidtitlan.retrocompatibilitytutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * 
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 * 
 * @author "M. en C. Javier Silva Perez (JSP)"
 * @since 22/02/2013
 * @version 1.0
 * 
 */
public class DummyFragment extends SherlockFragment {

	public static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_dummy,
				container, false);
		((TextView) rootView.findViewById(android.R.id.text1))
				.setText(getString(R.string.dummySectionText));
		return rootView;
	}

}
