package com.iiitd.team.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 21-03-2015.
 */
public class PendingRequestFragment extends Fragment{

    String data=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pending_request, container, false);
        TextView mTextView = (TextView)rootView.findViewById(R.id.pending_req);
        if(Main.mData!=null) {
            data = Main.mData;
            mTextView.setText(data);
        }
        return rootView;
    }


}