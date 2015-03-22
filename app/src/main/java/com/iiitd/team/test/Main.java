package com.iiitd.team.test;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class Main extends ActionBarActivity implements ActionBar.TabListener {


    private ViewPager viewPager;
    private TabAdaper mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Post Request", "Pending Request"};
    private ListView mListView;
    private ArrayAdapter<CharSequence> navAdapter;
    public LocationManager locationManager;
    public Location myCurrentLocation=null;
    Spinner fertiliserList;



    public class CurrentLocation implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                myCurrentLocation = location;
                /*Toast mToast = Toast.makeText(getApplicationContext(), "location changed", Toast.LENGTH_SHORT);
                mToast.show();*/
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    public void postQuery(View v){

        Toast mToast = Toast.makeText(this,"entered postQuery", Toast.LENGTH_SHORT);
        mToast.show();
         //postrequest = (Button)findViewById(R.id.buttonRequestButton);
        /*postrequest.setOnClickListener(new View.OnClickListener() {
            @Override*/
           // public void onClick(View v) {
                    //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        CurrentLocation mCurrentLocation = new CurrentLocation();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mCurrentLocation);

                    myCurrentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    //mylistener = new MyLocationListener();
                    if(myCurrentLocation == null){
                        // leads to the settings because there is no last known location
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                        mToast = Toast.makeText(this,"Location null", Toast.LENGTH_SHORT);
                        mToast.show();
                    }

                        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new CurrentLocation() );
                    //while(myCurrentLocation==null) {
                       /* mToast = Toast.makeText(this,""+myCurrentLocation.getLatitude(), Toast.LENGTH_SHORT);
                        mToast.show();
                    //}*/

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        fertiliserList = (Spinner)findViewById(R.id.fertiliserMenu);
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



        }
        /*else{
                PendingRequestFragment mPendingRequestFragment = new PendingRequestFragment();
                fragmentTransaction.replace(R.id.drawer_layout, mPendingRequestFragment);
                fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();*/


// Commit the transaction




    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
