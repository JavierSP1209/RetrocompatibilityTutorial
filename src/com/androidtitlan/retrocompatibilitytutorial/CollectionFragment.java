package com.androidtitlan.retrocompatibilitytutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * This fragment only contains a button which open a new activity with a view
 * pager
 * 
 * @author "M. en C. Javier Silva Perez (JSP)"
 * @since 22/02/2013
 * @version 1.0
 * 
 */
public class CollectionFragment extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_collection,
				container, false);

		// Get the button from the view and open the new activity
		Button btnCollection = (Button) rootView
				.findViewById(R.id.btnCollection);
		btnCollection.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						CollectionDemoActivity.class);
				startActivity(intent);

			}
		});

		return rootView;
	}

}
