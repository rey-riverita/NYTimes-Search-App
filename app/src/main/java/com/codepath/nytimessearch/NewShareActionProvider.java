package com.codepath.nytimessearch;

import android.content.Context;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;

/**
 * Created by riverita on 6/24/16.
 */
public class NewShareActionProvider extends ShareActionProvider {

    public NewShareActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }
}
