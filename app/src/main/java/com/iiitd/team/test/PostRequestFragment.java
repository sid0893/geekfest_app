package com.iiitd.team.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by hp on 21-03-2015.
 */
public class PostRequestFragment extends Fragment implements View.OnClickListener{

    Activity activity;
    String fname;
    double qty;
    Data mData;
    String expectedDate;
    Button postrequest;
    Spinner mSpinner;
    EditText mEditText,mEditText1;
    public LocationManager locationManager;
    public Location myCurrentLocation=null;
    Toast mToast=null;
    public class CurrentLocation implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                mData.mLocation = location;
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

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.post_request, container, false);
        postrequest= (Button)rootView.findViewById(R.id.buttonRequest);
        mSpinner = (Spinner)rootView.findViewById(R.id.fertiliserMenu);
        mEditText = (EditText)rootView.findViewById(R.id.amountInput);
        mEditText1 = (EditText)rootView.findViewById(R.id.expectedDate);
        //fname = mSpinner.getChildAt(0).toString();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fname = parent.getSelectedItem().toString();
                Log.i(PostRequestFragment.class.toString(), "spinner");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        postrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(PostRequestFragment.class.toString(), "button");

                locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
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
                qty = Double.parseDouble(mEditText.getText().toString());
                expectedDate = mEditText1.getText().toString();
                mData = new Data();
                mData.expectedDateOfArrival =  expectedDate;
                mData.fName = fname;
                mData.qty = qty;

            /*while(mData.mLocation==null) {
                Log.i("BH","LOC NULL");
            }*/
                try{
                    ((OnOptionSelected) activity).onItemPicked(mData.toString());
                }catch (ClassCastException cce){

                }
            }
        });
        return rootView;
    }

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //if(parent.getId()==mSpinner.getId()) {
            fname = view.toString();
            Log.i(PostRequestFragment.class.toString(), "spinner");
        //}

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
*/
    @Override
    public void onClick(View v) {
        //if(v.getId()==postrequest.getId()) {
      /*  Log.i(PostRequestFragment.class.toString(), "button");
            mToast.makeText(getActivity(),"button clicked",Toast.LENGTH_SHORT);
            mToast.show();
            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
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
            qty = Double.parseDouble(mEditText.getText().toString());
            expectedDate = mEditText1.getText().toString();
            mData = new Data();
            mData.expectedDateOfArrival =  expectedDate;
            mData.fName = fname;
            mData.qty = qty;

            *//*while(mData.mLocation==null) {

            }*//*
            try{
                ((OnOptionSelected) activity).onItemPicked(mData);
            }catch (ClassCastException cce){

            }
       // }*/
    }
    public interface OnOptionSelected{
        public void onItemPicked(String mData);
    }
}

