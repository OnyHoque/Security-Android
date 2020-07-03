package com.hoque.securitypro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    ImageView password_generator, encryption, decryption, information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        encryption = findViewById(R.id.img_btn1);
        decryption = findViewById(R.id.img_btn2);
        password_generator = findViewById(R.id.img_btn3);
        information = findViewById(R.id.img_btn4);


        password_generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoTo = new Intent(getApplicationContext(), PasswordGenerator.class);
                startActivity(GoTo);
            }
        });

        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoTo = new Intent(getApplicationContext(), Encryption_activity.class);
                startActivity(GoTo);
            }
        });

        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoTo = new Intent(getApplicationContext(), Decryption_activity.class);
                startActivity(GoTo);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.MakeText(getApplicationContext(), "Coming Soon!");
            }
        });
    }
}
