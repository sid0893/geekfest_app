package com.iiitd.team.test;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

import com.ibm.mobile.services.cloudcode.IBMCloudCode;
import com.ibm.mobile.services.core.IBMBluemix;
import com.ibm.mobile.services.data.IBMData;
import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bolts.Continuation;
import bolts.Task;


public class Main extends ActionBarActivity implements ActionBar.TabListener,PostRequestFragment.OnOptionSelected {

    private static final String CLASS_NAME = "Main";
    public static final String PENDING_DATA = "data";
    public static final String listofItem = "A";
    private ViewPager viewPager;
    private TabAdaper mAdapter;
    private ActionBar actionBar;
    public ArrayList<Item> itemList;
    // Tab titles
    private String[] tabs = { "Post Request", "Pending Request"};
    private ListView mListView;
    private ArrayAdapter<CharSequence> navAdapter;
//    public LocationManager locationManager;
//    public Location myCurrentLocation=null;
    Spinner fertiliserList;
    public static String mData=null;
    HashMap<Data, Integer> b ;

    @Override
    public void onItemPicked(String data) {
        mData = data;
       // mData = null;
        createItem(data);

        /*Bundle bundle = new Bundle();
        bundle.putString(PENDING_DATA,data);
        PendingRequestFragment mPendingRequestFragment = new PendingRequestFragment();
        mPendingRequestFragment.setArguments(bundle);*/
        viewPager.setCurrentItem(1);
    }



   /* public class CurrentLocation implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                mData.mLocation = location;
                *//*Toast mToast = Toast.makeText(getApplicationContext(), "location changed", Toast.LENGTH_SHORT);
                mToast.show();*//*
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
    }*/
    /*public void load(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("aaa",1);
        editor.commit();
        for(String s:sharedPref.getAll().keySet()){
            Log.i(CLASS_NAME,"kadjfhkdjf::::" + s);
            b.put(s, new Integer(sharedPref.getInt(s,0)));
        }
    }

    public void save(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (String key: b.keySet()){
            editor.putInt(key,b.get(key).intValue());
        }
        editor.commit();
    }*/

   /* public void postQuery(View v){


        Toast mToast = Toast.makeText(this,"entered postQuery", Toast.LENGTH_SHORT);
        mToast.show();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        CurrentLocation mCurrentLocation = new CurrentLocation();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mCurrentLocation);

        myCurrentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(myCurrentLocation == null){
            // leads to the settings because there is no last known location
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            //mToast = Toast.makeText(this,"Location null", Toast.LENGTH_SHORT);
            //mToast.show();
        }



    }*/

    public void listItems() {

        try{
            IBMQuery<Item> query = IBMQuery.queryForClass(Item.class);
            query.find().continueWith(new Continuation<List<Item>, Object>() {
                @Override
                public Object then(Task<List<Item>> listTask) throws Exception {
                    final List<Item> objects = listTask.getResult();
                    if(listTask.isCancelled()){
                        Log.e(CLASS_NAME, "Exception : Task " + listTask.toString() + " was cancelled.");
                    }else if(listTask.isFaulted()) {
                        Log.e(CLASS_NAME, "Exception : " + listTask.getError().getMessage());
                    }else{
                        itemList.clear();
                        for (IBMDataObject item:objects){
                            itemList.add((Item) item);
                        }
                    }
                    return null;
                }
            },Task.UI_THREAD_EXECUTOR);
        } catch (IBMDataException e) {
            e.printStackTrace();
        }
    }

    public void createItem(String d){
        //if(b.get(d) != null && b.get(d).intValue() != 0 ) {
//            Toast sid = Toast.makeText(this, "Cannot accept repeated request", Toast.LENGTH_SHORT);
//            sid.show();
           // return;
        //}
       // Log.i(CLASS_NAME,"data=" + d.mLocation+" "+d.qty+" "+d.fName+" "+d.expectedDateOfArrival);
        Item item = new Item();
        if(d!=null){
            item.setName(d);
            //if(b.get(d) != null)return;
            Log.i(CLASS_NAME,"calling createItem and "+item.toString());

            item.save().continueWith(new Continuation<IBMDataObject, Object>() {
                    @Override
                public Object then(Task<IBMDataObject> ibmDataObjectTask) throws Exception {
                    if(ibmDataObjectTask.isCancelled()){
                        Log.e(CLASS_NAME, "Exception : Task " + ibmDataObjectTask.toString() + " was cancelled.");
                    }else if(ibmDataObjectTask.isFaulted()){
                        Log.e(CLASS_NAME, "Exception : " + ibmDataObjectTask.getError().getMessage());
                    }else{
                        Log.i(CLASS_NAME,"Successful transmission of data");
                        listItems();
                    }
                    return null;
                }
            });
        }
        //b.put(d,1);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //fertiliserList = (Spinner)findViewById(R.id.fertiliserMenu);
        mAdapter = new TabAdaper(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //mData = new Data();
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));


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

            IBMBluemix.initialize(this, "6bcef7fb-eed0-4263-b1e5-b09e313f00ed", "fa0c39a356850474078dde994b6c10b070051f02", "http://test-tgz-app.mybluemix.net");
            IBMCloudCode.initializeService();
            IBMData.initializeService();
            Item.registerSpecialization(Item.class);

        }




        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ferti,android.R.layout.simple_spinner_dropdown_item);
        mListView = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0: Intent mIntent = new Intent(getApplicationContext(),AdminLogin.class);
                           // mIntent.putParcelableArrayListExtra(listofItem, (ArrayList<? extends android.os.Parcelable>) itemList);
                            startActivity(mIntent);break;
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
