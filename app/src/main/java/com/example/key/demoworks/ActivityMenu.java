package com.example.key.demoworks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class ActivityMenu extends AppCompatActivity {
    public RelativeLayout login;
    public RelativeLayout register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        login = (RelativeLayout) findViewById(R.id.login);
        register = (RelativeLayout) findViewById(R.id.adduser);

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


}

