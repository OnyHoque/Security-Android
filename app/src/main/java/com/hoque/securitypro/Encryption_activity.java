package com.hoque.securitypro;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Encryption_activity extends AppCompatActivity {

    EditText original_msg, edit_en_pass, edit_en_encrypted_msg;
    Button b_en, b_en_copy;
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_activity);
        b_en = (Button)findViewById(R.id.b_en);
        b_en_copy = (Button)findViewById(R.id.b_en_copy);
        original_msg = (EditText)findViewById(R.id.edit_original_msg);
        edit_en_pass = (EditText)findViewById(R.id.edit_en_pass);
        edit_en_encrypted_msg = (EditText)findViewById(R.id.edit_en_encrypted_msg);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;

        b_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(5);
                StartEncryption();
            }
        });

        b_en_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(5);
                Copy_text();
            }
        });
    }

    private void StartEncryption()
    {
        String message = original_msg.getText().toString();
        String password = edit_en_pass.getText().toString();

        String hashed_password = AESUtils.secret_key_maker(password);
        try{
            String encrypted_message = AESUtils.encrypt(message, hashed_password);
            edit_en_encrypted_msg.setText(encrypted_message);
        }catch (Exception e){

        }


    }



    private void Copy_text()
    {
        String str = edit_en_encrypted_msg.getText().toString();
        if(str.length() <1){
            Helper.MakeText(getApplicationContext(), "Drag the slider first.");
        }else{
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Password", str);
            clipboard.setPrimaryClip(clip);
            Helper.MakeText(getApplicationContext(), "copied to clipboard.");
        }
    }
}
