package com.example.key.demoworks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by key on 11/1/2016.
 */

public class FogetActivity extends AppCompatActivity {
    private RelativeLayout addmail;
    protected EditText mail;
    private Button send;
    private RelativeLayout addkey;
    protected EditText edtkey;
    private Button sendkey;
    private RelativeLayout repass;
    protected EditText edtpass;
    protected EditText edtrepass;
    private Button sendpass;
    private TextView name;

    public String mk;
    private final String postmail_URL = "http://minhkhang45.esy.es/postmail.php";
    private final String repass_URL = "http://minhkhang45.esy.es/repass.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);

        addmail = (RelativeLayout) findViewById(R.id.addemail);
        mail = (EditText) findViewById(R.id.sendemail);
        send = (Button) findViewById(R.id.send);

        addkey = (RelativeLayout) findViewById(R.id.addkey);
        edtkey = (EditText) findViewById(R.id.edtkey);
        sendkey = (Button) findViewById(R.id.sendkey);


        repass = (RelativeLayout) findViewById(R.id.repass);
        name = (TextView) findViewById(R.id.name);
        edtpass = (EditText) findViewById(R.id.edtpass);
        edtrepass = (EditText) findViewById(R.id.edtrepass);
        sendpass = (Button) findViewById(R.id.sendpass);

        addkey.setVisibility(View.GONE);
        repass.setVisibility(View.GONE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

    }

    //Send mail to server
    protected void sendEmail() {
        if (mail.getText().toString().equals("")) {
            Log.d("erorr", ":");
        } else {
            mk = "";
            //Random rd = new Random();
            // mk = String.valueOf(rd.nextInt(9999 - 1000) + 1000);
            //Make string request to send key to user's email
            StringRequest stringRequest = new StringRequest(Request.Method.POST, postmail_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response:", response);
                            //take key from server
                            mk = response.trim();
                            Log.d("mail:", mail.getText().toString());
                            if (response.trim() != null) {
                                //Test key send and fill in
                                doKey();
                                // startActivity(new Intent(FogetActivity.this,RepassActivity.class));
                                //Toast.makeText(ActivityLogin.this, "Sai tai khoan hoac mat khau", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(FogetActivity.this, "Sorry!Fail to send mail", Toast.LENGTH_LONG).show();


                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("foget", error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    // map.put("mk", mk);
                    map.put("email", mail.getText().toString().toLowerCase());
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    //Compare key and editext of user
    protected void doKey() {
        addmail.setVisibility(View.GONE);
        addkey.setVisibility(View.VISIBLE);

        sendkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mk.equals(edtkey.getText().toString())) {
                    doRepass();
                } else {
                    Toast.makeText(FogetActivity.this, "Sorry! Wrong key, please checked  ", Toast.LENGTH_LONG).show();
                    Log.d("sai key", " email");
                    Log.d("mk:" + mk, edtkey.getText().toString());
                }

            }
        });


    }

    // Change pass word.
    protected void doRepass() {
        Log.d("do", "Repass");
        name.setText(mail.getText().toString());
        addkey.setVisibility(View.GONE);
        repass.setVisibility(View.VISIBLE);

        sendpass.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if ((edtpass.getText().toString().equals("")) || edtrepass.getText().toString().equals("")) {
                                                Toast.makeText(FogetActivity.this, "Sorry! Please enter ", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (edtpass.getText().toString().equals(edtrepass.getText().toString())) {
                                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, repass_URL,
                                                            new Response.Listener<String>() {
                                                                @Override
                                                                public void onResponse(String response) {
                                                                    Log.d("response:", response);
                                                                    Log.d("pass", edtpass.getText().toString());
                                                                    Log.d("mail send", mail.getText().toString().toLowerCase());
                                                                    if (response.trim() != null) {
                                                                        Toast.makeText(FogetActivity.this, "Success! You can login!", Toast.LENGTH_LONG).show();
                                                                        Toast.makeText(FogetActivity.this, response + "  Pass:" + edtpass.getText().toString(), Toast.LENGTH_LONG).show();
                                                                        Toast.makeText(FogetActivity.this, response + "  Pass:" + edtpass.getText().toString(), Toast.LENGTH_LONG).show();
                                                                        Toast.makeText(FogetActivity.this, response + "  Pass:" + edtpass.getText().toString(), Toast.LENGTH_LONG).show();
                                                                        edtpass.setText("");
                                                                        edtrepass.setText("");
                                                                    } else {
                                                                        Toast.makeText(FogetActivity.this, "Sorry!Fail to repass", Toast.LENGTH_LONG).show();

                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    Log.d("foget", error.toString());
                                                                }
                                                            }) {
                                                        @Override
                                                        protected Map<String, String> getParams() throws AuthFailureError {
                                                            Map<String, String> map = new HashMap<String, String>();
                                                            map.put("email", mail.getText().toString().toLowerCase());
                                                            map.put("pass", edtpass.getText().toString());

                                                            return map;
                                                        }
                                                    };
                                                    RequestQueue requestQueue = Volley.newRequestQueue(FogetActivity.this);
                                                    requestQueue.add(stringRequest);
                                                    startActivity(new Intent(FogetActivity.this, ActivityLogin.class));


                                                } else {
                                                    edtrepass.setText("");
                                                    Toast.makeText(FogetActivity.this, "Sorry! Pass and repass not compare ", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    }
        );

    }
}


