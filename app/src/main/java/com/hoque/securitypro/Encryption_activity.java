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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Encryption_activity extends AppCompatActivity {

    EditText original_msg, edit_en_pass;
    Button btn_encrypt, btn_copy;
    TextView view_encrypted_msg;
    Vibrator vibe;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_activity);
        btn_encrypt = findViewById(R.id.btn_encryption_encrypt);
        btn_copy = findViewById(R.id.btn_encryption_copy);
        original_msg = findViewById(R.id.input_encryption_original_msg);
        edit_en_pass = findViewById(R.id.input_encryption_password);
        view_encrypted_msg = findViewById(R.id.view_encryption_encryptedMessage);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        linearLayout = findViewById(R.id.layout_encryption_show_message);
        linearLayout.setVisibility(View.GONE);

        btn_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(5);
                StartEncryption();
            }
        });

        btn_copy.setOnClickListener(new View.OnClickListener() {
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

        if(message.length() > 0 && password.length() > 0) {
            String hashed_password = AESUtils.secret_key_maker(password);
            try {
                String encrypted_message = AESUtils.encrypt(message, hashed_password);
                linearLayout.setVisibility(View.VISIBLE);
                view_encrypted_msg.setText(encrypted_message);
            } catch (Exception e) {
                Helper.MakeText(getApplicationContext(), "Ops!");
            }
        }else{
            Helper.MakeText(getApplicationContext(), "Enter message and password first!");
        }
    }



    private void Copy_text()
    {
        String str = view_encrypted_msg.getText().toString();
        if(str.length() <1){
            Helper.MakeText(getApplicationContext(), "Drag the slider first.");
        }else{
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Message", str);
            clipboard.setPrimaryClip(clip);
            Helper.MakeText(getApplicationContext(), "copied to clipboard.");
        }
    }
}
