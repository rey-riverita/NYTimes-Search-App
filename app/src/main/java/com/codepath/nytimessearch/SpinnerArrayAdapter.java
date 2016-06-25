package com.codepath.nytimessearch;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by riverita on 6/22/16.
 */
public class SpinnerArrayAdapter extends ArrayAdapter<Spinner> {

    public SpinnerArrayAdapter(Context context, List<Spinner> sortOptions) {
        super(context, android.R.layout.simple_list_item_1, sortOptions);
    }



}
