package com.iiitd.team.test;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;

public class DisplayToAdmin extends ActionBarActivity {

    double[][] loc = new double[][]{{29.061427, 78.560474},
            {29.061427, 78.923023},
            {28.946125, 79.197681},
            {28.744038, 79.307544},
            {28.387027, 79.054859},
            {28.666949, 78.593433},
            {28.551209, 79.571216},
            {28.019111, 80.373218},
            };
    int[] qty = new int[8];
    String[] fname = new String[8];
    String[] date = new String[8];
    String [] record = new String[4];
    TextView mTextView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_to_admin);
        Random rand = new Random();
        for(int i=0;i<8;i++){
            qty[i] = rand.nextInt(101);
            fname[i] = "aslkdj"+rand.nextInt();
            date[i] = "12/1/1934";
        }
        mTextView = (TextView)findViewById(R.id.dataDisplay);


        if(isExternalStorageWritable()){
            String csv = "data.csv";
            CSVWriter writer = null;
            try {
                writer = new CSVWriter(new FileWriter("/sdcard/myfile.csv"));
                //String [] record = "4,David,Miller,Australia,30".split(",");
                //Write the record to file
                for(int i=0;i<8;i++){
                    record[0] = loc[i][0]+":"+loc[i][1];
                    record[1] = fname[i];
                    record[2] = String.valueOf(qty[i]);
                    record[3] = date[i];
                    writer.writeNext(record);

                }
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Create record
            //close the writer
           /* try {

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

        else{
            System.out.println("Storage not available");
        }

    }





    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    @Override
    protected void onResume(){
        super.onResume();
        mTextView.setText("Location\t\t\tfname\t\t\t\t\t\t\t\t\t\t\t\tqty\n");
        for(int i=0;i<8;i++){
            mTextView.append(loc[i][0]+"\t\t\t"+fname[i]+"\t\t\t"+qty[i]+"\n"+loc[i][1]+"\n\n");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_to_admin, menu);
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
}

