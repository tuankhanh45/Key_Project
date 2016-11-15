package com.example.key.demoworks;

/**
 * Created by key on 10/6/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by key on 9/26/2016.
 */
public class ActivityRegister extends AppCompatActivity {
    //Button home1;

    private EditText addname;
    private EditText addpass;
    private EditText mail;
    private EditText addcompany;
    Button adduser;
    public String name;
    public String pass;
    public String company;
    public String email;

    // "http://minhkhang45.esy.es/Register.php";
    private static final String REGISTER_URL = "http://minhkhang45.esy.es/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //home1 = (Button) findViewById(R.id.home);
        addname = (EditText) findViewById(R.id.edtadduser);
        addpass = (EditText) findViewById(R.id.edtadduserpass);
        mail = (EditText) findViewById(R.id.edtmail);
        addcompany = (EditText) findViewById(R.id.edtaddusercompany);
        adduser = (Button) findViewById(R.id.btnadd);

       /* home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityRegister.this, ActivityMenu.class);
                startActivity(i);
            }
        });*/
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAddUser();
            }
        });
    }

    public void doAddUser() {
        name = addname.getText().toString().trim();
        pass = addpass.getText().toString().trim();
        email = mail.getText().toString().trim();
        company = addcompany.getText().toString();
        //test add
        if (!checkdetail()) {
         Log.d("thieu"," hoac sai thong tin");
        } else {
            checkdb();
        }
    }

    public void checkdb() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    //dua ra thong tin thành cong hay ko.
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ActivityRegister.this, response, Toast.LENGTH_LONG).show();
                        Log.d("", response.trim());
                        if (response.trim().equals("success"))
                            Toast.makeText(ActivityRegister.this, "Success! You can Login with new acount: "+"Username: "+name+"  password: "+pass, Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(ActivityRegister.this, "Sorry! Your acount had been used", Toast.LENGTH_LONG).show();
                            addname.setText("");
                            addpass.setText("");
                            mail.setText("");
                            addcompany.setText("");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityRegister.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("password", pass);
                params.put("email", email.toLowerCase());
                params.put("company", company);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public boolean checkdetail() {
        boolean check = true;
        if (name.equals("") || pass.equals("") || email.equals("") || company.equals("")){
            Toast.makeText(ActivityRegister.this, "Erorr! Fill full infomation ", Toast.LENGTH_SHORT).show();
            check=false;
        }


        return check;

    }
}

