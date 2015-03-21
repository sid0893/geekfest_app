package com.iiitd.team.test;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;


public class Main extends ActionBarActivity implements ActionBar.TabListener {


    private ViewPager viewPager;
    private TabAdaper mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Post Request", "Pending Request"};
    private ListView mListView;
    private ArrayAdapter<CharSequence> navAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        mAdapter = new TabAdaper(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));

            /*if (findViewById(R.id.drawer_layout) != null) {

                if (savedInstanceState != null) {
                    return;
                }
                PostRequestFragment defaultFragment = new PostRequestFragment();
                defaultFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.drawer_layout, defaultFragment).commit();
            }*/



            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


        }

        Spinner fertiliserList = (Spinner)findViewById(R.id.fertiliserMenu);


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ferti,android.R.layout.simple_spinner_dropdown_item);
        mListView = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0: Spinner fertiliserMenu = (Spinner)findViewById(R.id.fertiliserMenu);
                            fertiliserMenu.setAdapter(adapter);break;
                }

            }
        });

    }

    private void addDrawerItems() {
        //navAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, R.array.nav_drawer);
        navAdapter = ArrayAdapter.createFromResource(this,R.array.nav_drawer,android.R.layout.simple_list_item_1);
        mListView.setAdapter(navAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());

        /*if(tab.getText().equals("Post Request")) {

            PostRequestFragment mPostRequestFragment = new PostRequestFragment();
                    *//*Bundle args = new Bundle();
                    args.putInt(mPendingRequestFragment.ARG_POSITION, position);
                     mPendingRequestFragment.setArguments(args);*//*
            fragmentTransaction.replace(R.id.drawer_layout, mPostRequestFragment);
            fragmentTransaction.addToBackStack(null);
        }
        else{
                PendingRequestFragment mPendingRequestFragment = new PendingRequestFragment();
                fragmentTransaction.replace(R.id.drawer_layout, mPendingRequestFragment);
                fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();*/


// Commit the transaction

    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
