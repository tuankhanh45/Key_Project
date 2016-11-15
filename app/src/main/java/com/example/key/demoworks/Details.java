package com.example.key.demoworks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by key on 10/18/2016.
 */

public class Details extends AppCompatActivity {
    public Button home;
    public TextView name;
    public TextView workdays;
    public TextView id;
    public TextView email;
    public TextView company;
    public TextView lastaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

       // home = (Button) findViewById(R.id.home);

        name = (TextView) findViewById(R.id.ten);
        id = (TextView) findViewById(R.id.id);
        email = (TextView) findViewById(R.id.email);
        company = (TextView) findViewById(R.id.company);
        workdays = (TextView) findViewById(R.id.cong);
        lastaddress = (TextView) findViewById(R.id.lastadd);
      /*  home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Details.this, ActivityMenu.class);
                startActivity(i);
            }
        });*/
        if (getIntent().toString().equals("")){
            Log.d("Intent","No value");
        }
        else {
        Intent calleri=getIntent();
        Bundle packagecaller=calleri.getBundleExtra("User");
        name.setText( packagecaller.getString("name"));
        id.setText(packagecaller.getString("id"));
        email.setText(packagecaller.getString("mail"));
        company.setText(packagecaller.getString("company"));
        workdays.setText(packagecaller.getString("workdays"));
        lastaddress.setText("");
        lastaddress.setText(packagecaller.getString("lastaddress"));
        Log.d("company",company.getText().toString());
    }}
}