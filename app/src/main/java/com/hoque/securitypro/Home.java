package com.hoque.securitypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button password_generator, encryption, decryption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        password_generator = (Button)findViewById(R.id.b_password_generator);
        encryption = (Button)findViewById(R.id.b_encryption);
        decryption = (Button)findViewById(R.id.b_decryption);

        password_generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.hoque.securitypro.PasswordGenerator"));
            }
        });

        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.hoque.securitypro.Encryption_activity"));
            }
        });

        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.hoque.securitypro.Decryption_activity"));
            }
        });
    }
}
