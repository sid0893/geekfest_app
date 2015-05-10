package com.iiitd.team.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class AdminLogin extends ActionBarActivity {

    String uname = "abc";
    String pswrd = "123";
    EditText nameI,passI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Button login = (Button)findViewById(R.id.loginButton);
        nameI = (EditText)findViewById(R.id.user_email);
        passI = (EditText)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameI.getText().toString();
                String pass = passI.getText().toString();
                if(name.equals(uname)&&pswrd.equals(pass)){
                    Intent mIntent = new Intent(getApplicationContext(),DisplayToAdmin.class);
                    startActivity(mIntent);
                }
                else{
                    Toast mToast = Toast.makeText(getApplicationContext(),"wrong id or password",Toast.LENGTH_SHORT);
                    mToast.show();
                }
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        nameI.setText("");
        passI.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_login, menu);
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
