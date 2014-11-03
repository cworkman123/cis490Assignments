package com.example.channing.notmymusic2.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import com.example.channing.notmymusic2.R;
import com.example.channing.notmymusic2.listener.MyTabListener;

/**
 * Created by Channing on 11/3/2014.
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        //Details Actionbar tab
        ActionBar.Tab details = actionbar.newTab().setText("Details");
        details.setTabListener(new MyTabListener());
        actionbar.addTab(details);

        //More Music Action tab
        ActionBar.Tab moreMusic = actionbar.newTab().setText("More Music");
        moreMusic.setTabListener(new MyTabListener());
        actionbar.addTab(moreMusic);

        //Events Action tab
        ActionBar.Tab events = actionbar.newTab().setText("Events");
        events.setTabListener(new MyTabListener());
        actionbar.addTab(events);

    }
}
