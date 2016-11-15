package com.example.key.demoworks;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ActivityMenu extends AppCompatActivity {
    public RelativeLayout login;
    public RelativeLayout register;
    public String JsonArrayLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        login = (RelativeLayout) findViewById(R.id.login);
        register = (RelativeLayout) findViewById(R.id.adduser);

         GPSTracker gps = new GPSTracker(ActivityMenu.this);
        // check if GPS enabled and take address
        if (gps.canGetLocation()) {
            String Details="";
            JsonArrayLocation="";
            String address="";
            Log.d("", "can get gps");
            DateFormat df = new SimpleDateFormat("yyyy:MM:dd  HH:mm:ss ");
            String date = df.format(Calendar.getInstance().getTime());
            //Take address from location
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);
                if (addresses.size() > 0) {
                    for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                        address += addresses.get(0).getAddressLine(i) + " ";
                }

                Log.d("address:", address);
                Log.d("La: "+gps.getLatitude()," Lo:"+gps.getLongitude());
                Log.d("time:",date);
            } catch (Exception e) {
                e.printStackTrace();
            }
           //make Json Object
            JSONObject obj = new JSONObject();
            try {
                obj.put("Address", address);
                obj.put("Longitude",gps.getLongitude());
                obj.put("Latitude", gps.getLatitude());
                obj.put("DateTime", date);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JsonArrayLocation=obj.toString();
            Log.d("string:",JsonArrayLocation);
            //Write to gps.txt file
            //with losing older data:
             overwriteData();
            //without losing data??????
            ApendWriteData();

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityMenu.this, ActivityLogin.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ActivityMenu.this, ActivityRegister.class);
                startActivity(i1);
            }
        });

    }
    public void overwriteData()
    {
        try {

            FileOutputStream fileout = openFileOutput("gps.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(JsonArrayLocation);
            Log.d("write to","gps.txt file");
            outputWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ApendWriteData(){
        //coding here
    }



}

