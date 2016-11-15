package com.example.key.demoworks;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
    String address = "";
    public String username;
    public String useremail;
    public String usercompany;
    public String userworkdays;
    public String userid;
    JSONObject User;
    public String name1, pass1;
    public GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
       // home = (Button) findViewById(R.id.home);
        foget = (TextView) findViewById(R.id.forget);
        login = (Button) findViewById(R.id.btnlogin);
       /* home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this, ActivityMenu.class);
                startActivity(i);
            }
        });*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                sendGPSLocation();
                            } else {
                                //Toast.makeText(ActivityLogin.this, "response"+response, Toast.LENGTH_LONG).show();
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
        gps = new GPSTracker(ActivityLogin.this);
        // check if GPS enabled and take address
        if (gps.canGetLocation()) {
            Log.d("", "can get gps");
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);
                if (addresses.size() > 0) {
                    for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                        address += addresses.get(0).getAddressLine(i) + " ";
                }

                Log.d("address:", address);
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, GPS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    parseJson();
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
                    map.put("Latitude", Double.toString(gps.getLatitude()));
                    map.put("Longitude", Double.toString(gps.getLongitude()));

                    Log.d("login", "send location and address");
                    Log.d("login", map.toString());
                    return map;
                }
            };
            Log.d("login", address + Double.toString(gps.getLatitude()) + Double.toString(gps.getLongitude()));
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            gps.showSettingsAlert();
        }
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
                        }
                        else {try {

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

       /* JsonArrayRequest getRequest = new JsonArrayRequest(LOGINJson_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("get ", "json");
                Log.d("Responsearrayjson", response.toString());

                try {
                    for (int i = 0; i < response.length(); i++) {
                        User = (JSONObject) response.get(i);
                    }
                    username = User.getString("UserName");
                    userphone = User.getString("Phone");
                    usercompany = User.getString("Company");
                    userid = User.getString("Id");
                    userworkdays = User.getString("WorkDays");

                    Log.d("parse Json", User.toString());
                    Log.d("phone", userphone);
                    Log.d("company", usercompany);
                    Log.d("name", username);
                    Log.d("parsjson", User.toString());
                    Intent i = new Intent(ActivityLogin.this, Details.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", username);
                    bundle.putString("phone", userphone);
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
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(getRequest);*/

    }

    public void doFoget() {
        //Intent i =new Intent(ActivityLogin.this,FogetActivity.class);
        startActivity(new Intent(ActivityLogin.this,FogetActivity.class));

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


