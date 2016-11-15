package com.example.key.demoworks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by key on 9/26/2016.
 */
public class ActivityLogin extends AppCompatActivity {
    public static final String LOGIN_URL = "http://minhkhang45.esy.es/login.php";
    public static final String LOGINJson_URL = "http://minhkhang45.esy.es/LoginJson.php";
    public static final String GPS_URL = "http://minhkhang45.esy.es/Gps.php";
    // public Button home;
    public EditText name, pass;
    public TextView foget;
    public Button login;
    protected GPSTracker gps;
    private String TAG = "";

    public String address = "";
    public String latitude;
    public String longitude;
    public String datetime;
    public String username;
    public String useremail;
    public String usercompany;
    public String userworkdays;
    public String userid;
    public String s="";
    JSONObject User;
    JSONObject Gpslocation;
    public String name1, pass1;
    public GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        foget = (TextView) findViewById(R.id.forget);
        login = (Button) findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendGPSLocation();
                UserLogin();
            }
        });
        foget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFoget();
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void UserLogin() {
        // login checking
        name = (EditText) findViewById(R.id.edtlogin);
        pass = (EditText) findViewById(R.id.edtpass);
        name1 = name.getText().toString();
        pass1 = pass.getText().toString();
        if (name1.isEmpty() || pass1.isEmpty()) {
            Toast.makeText(ActivityLogin.this, "Sorry! Put your name and pass", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("send name and pass", "");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response:", response);
                            Log.d("", name1);
                            Log.d("", pass1);
                            if (response.trim().equals("success")) {
                               // sendGPSLocation();
                            } else {
                                name.setText("");
                                pass.setText("");
                                Toast.makeText(ActivityLogin.this, "Sai tai khoan hoac mat khau", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", name1);
                    map.put("password", pass1);
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }


    public void sendGPSLocation() {
        Log.d("send gps location","ok");
        //Read data from gps.txt
        try {
            InputStream inputStream=openFileInput("gps.txt");
            if(inputStream !=null){
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String s1="";
                while ((s1=bufferedReader.readLine())!=null)
                    s+=s1;
            }
            Log.d("readtext",s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Parse JsonObject
        try {

            Gpslocation = new JSONObject(s);
            address= Gpslocation.getString("Address");
            latitude=Gpslocation.getString("Latitude");
            longitude=Gpslocation.getString("Longitude");
            datetime=Gpslocation.getString("DateTime");
            Log.d("Gpslocation:",Gpslocation.toString());
        } catch (JSONException e) {
            e.printStackTrace();

        }
        //Make String request and push map to server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GPS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //parseJson();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("UserName", name1);
                map.put("Address", address);
                map.put("Latitude", latitude);
                map.put("Longitude", longitude);
                map.put("DateTime",datetime);
                Log.d("login", "send location and address");
                Log.d("login", map.toString());
                return map;
            }
        };
        Log.d("login", address + Double.toString(gps.getLatitude()) + Double.toString(gps.getLongitude()));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void parseJson() {
        Log.d("open", "details");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGINJson_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response:", response);
                        if (response.trim().equals("[]")) {
                            Toast.makeText(ActivityLogin.this, "Connect Erorr", Toast.LENGTH_SHORT).show();
                        } else {
                            //parse respon to Jsonobject user
                            try {

                                User = new JSONObject(response);
                                username = User.getString("UserName");
                                useremail = User.getString("Email");
                                usercompany = User.getString("Company");
                                userid = User.getString("Id");
                                userworkdays = User.getString("WorkDays");

                                Log.d("parse Json", User.toString());
                                Log.d("phone", useremail);
                                Log.d("company", usercompany);
                                Log.d("name", username);
                                Log.d("parsjson", User.toString());
                                Intent i = new Intent(ActivityLogin.this, Details.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("name", username);
                                bundle.putString("mail", useremail);
                                bundle.putString("company", usercompany);
                                bundle.putString("id", userid);
                                bundle.putString("workdays", userworkdays);
                                bundle.putString("lastaddress", address);


                                Log.d("bundle:", bundle.toString());

                                i.putExtra("User", bundle);
                                startActivity(i);


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", name1);
                map.put("password", pass1);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    public void doFoget() {
        //Intent i =new Intent(ActivityLogin.this,FogetActivity.class);
        startActivity(new Intent(ActivityLogin.this, FogetActivity.class));

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ActivityLogin Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


