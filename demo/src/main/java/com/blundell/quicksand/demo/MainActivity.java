package com.blundell.quicksand.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.blundell.quicksand.demo.activitytransition.FromHereActivity;
import com.blundell.quicksand.demo.amazeanimation.AmazeAnimationFragment;
import com.blundell.quicksand.demo.simpleanimation.SimpleAnimationActivity;
import com.blundell.quicksand.demo.viewanimation.ViewAnimateActivity;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new AmazeAnimationFragment())
                    .commit();
        } else if (position == 1) {
            Intent intent = new Intent(this, FromHereActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(this, ViewAnimateActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SimpleAnimationActivity.class);
            startActivity(intent);
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }
}
