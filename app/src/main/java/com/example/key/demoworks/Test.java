package com.example.key.demoworks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by key on 10/18/2016.
 */

public class Test  extends AppCompatActivity{@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test);
}
}
/*package com.example.key.demoworks;

        import android.content.Intent;
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
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.appindexing.Thing;
        import com.google.android.gms.common.api.GoogleApiClient;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Locale;
        import java.util.Map;

/**
 * Created by key on 9/26/2016.
 */
/*public class ActivityLogin extends AppCompatActivity {
    public static final String LOGIN_URL = "http://minhkhang45.esy.es/login.php";
    public static final String GPS_URL = "http://minhkhang45.esy.es/gps.php";
    public Button home;
    public EditText name, pass;
    public TextView foget;
    public Button login;
    protected GPSTracker gps;
    private String TAG = "";
    String address = "";
    public String username;
    public String userphone;
    public String usercompany;
    public String userworkdays;
    public String userid;
    JSONObject User;
    //Lay gio he thong
  /*  Date thoiGian = new Date();
    //Khai bao dinh dang ngay thang
    SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    //parse ngay thang sang dinh dang va chuyen thanh string.
    final String DateTime = dinhDangThoiGian.format(thoiGian.getTime());*/
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        home = (Button) findViewById(R.id.home);
        foget = (TextView) findViewById(R.id.forget);
        login = (Button) findViewById(R.id.btnlogin);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this, ActivityMenu.class);
                startActivity(i);
            }
        });
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void UserLogin() {
        // login checking
        final String name1, pass1;
        name = (EditText) findViewById(R.id.edtlogin);
        pass = (EditText) findViewById(R.id.edtpass);

        name1 = name.getText().toString();
        pass1 = pass.getText().toString();
        if (name1.isEmpty() || pass1.isEmpty()) {
            Log.d("", "");
            Toast.makeText(ActivityLogin.this, "Thiếu thông tin tài khoản", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response:",response);
                            if (response.trim().equals("[]")) {
                                name.setText("");
                                pass.setText("");
                                Toast.makeText(ActivityLogin.this, "Sai tai khoan hoac mat khau", Toast.LENGTH_LONG).show();
                            } else {

                                //Toast.makeText(ActivityLogin.this, "response"+response, Toast.LENGTH_LONG).show();
                                sendGPSLocation();

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
            Log.d("ActivityLogin.this","can get gps");
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> addresses = geocoder.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);
                if (addresses.size() > 0) {
                    for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                        address += addresses.get(0).getAddressLine(i) + " ";
                }

                Log.d("address:",address);
            } catch (Exception e) {
                e.printStackTrace();
                address="default";

            }
            Log.d("latitude:",Double.toString(gps.getLatitude()));
            Log.d("longitude:",Double.toString(gps.getLongitude()));
            Log.d("address:",address);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, GPS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("post location",response.trim());
                    //Toast.makeText(ActivityLogin.this, response.trim() + "location", Toast.LENGTH_LONG).show();
                    openDetailUser();
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
                    map.put("Address", address);
                    map.put("Latitude", Double.toString(gps.getLatitude()));
                    map.put("Longitude", Double.toString(gps.getLongitude()));
                    return map;
                }
            };
            Log.d(TAG, address + Double.toString(gps.getLatitude()) + Double.toString(gps.getLongitude()));
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }*/

    //String array request with volleyball
    /*public void sendUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ActivityLogin.this, response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityLogin.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/

    //Parse Json and open details

   // public void openDetailUser() {
     //   Log.d("open","details");
        // Toast.makeText(ActivityLogin.this, "open details", Toast.LENGTH_LONG).show();
        //Json String request
       /* JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LOGIN_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, response.toString() + "cc");
                try {
                    Toast.makeText(ActivityLogin.this, response.get("Company").toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest); */

        //Array Json request
       /* JsonArrayRequest getRequest = new JsonArrayRequest(LOGIN_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Responsearrayjson", response.toString());
                try {
                    //Parsing json array response
                    //loop through each json object
                    for (int i = 0; i < response.length(); i++) {
                        User = (JSONObject) response.get(i);}
                    username = User.getString("UserName");
                    userphone = User.getString("Phone");
                    usercompany = User.getString("Company");
                    userid = User.getString("Id");
                    userworkdays = User.getString("WorkDays");

                    Log.d("parse Json", User.toString());
                    Log.d("phone",userphone);
                    Log.d("company",usercompany);
                    Log.d("name",username);
                    Log.d("parsjson", User.toString());

                    Intent i = new Intent(ActivityLogin.this, Details.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("name", username);
                    bundle.putString("phone", userphone);
                    bundle.putString("company", usercompany);
                    bundle.putString("id", userid);
                    bundle.putString("workdays", userworkdays);
                    bundle.putString("lastaddress", address);


                    Log.d("bundle:",bundle.toString());
                    //Đưa Bundle vào Intent
                    i.putExtra("User", bundle);
                    startActivity(i);
                    // Toast.makeText(ActivityLogin.this,"try jsonarray request"+username , Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    // Toast.makeText(ActivityLogin.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
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
        requestQueue1.add(getRequest);
        //Toast.makeText(ActivityLogin.this,"loi tai intent r",Toast.LENGTH_LONG).show();
        //Log.d("error","detailsactivity");
        //Intent i = new Intent(ActivityLogin.this, DetailUserActivity.class);
        //startActivity(i);
    }

    public void doFoget() {
        //do foget in here
        //chuyển tới help.
    }*/

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
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
}*/


