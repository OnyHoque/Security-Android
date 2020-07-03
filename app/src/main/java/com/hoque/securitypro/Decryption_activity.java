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

import java.util.StringTokenizer;

public class Decryption_activity extends AppCompatActivity {

    EditText input_encrypted_msg, input_password, input_show;
    Button btn_decrypt, btn_paste;
    Vibrator vibe;

    private String message = "", password = "";
    private String decryptedMSG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption_activity);


        input_show = findViewById(R.id.edit_decypted_msg);
        input_encrypted_msg = findViewById(R.id.edit_de_encrypted_msg);
        input_password = findViewById(R.id.edit_de_password);
        btn_decrypt = findViewById(R.id.b_de);
        btn_paste = findViewById(R.id.b_de_paste);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;

        btn_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(5);
                Start_Decryption();
            }
        });
        btn_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(5);
                PasteText();
            }
        });
    }

    private void Start_Decryption()
    {
        message = input_encrypted_msg.getText().toString();
        password = input_password.getText().toString();

        String hashed_password = AESUtils.secret_key_maker(password);
        try{
            String encrypted_message = AESUtils.decrypt(message, hashed_password);
            input_show.setText(encrypted_message);
        }catch (Exception e){

        }
    }



    private void PasteText()
    {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        String pasteData;
        if (!(clipboard.hasPrimaryClip()))
        {
            Toast.makeText(getApplicationContext(),"Nothing is saved on Clipboard!", Toast.LENGTH_LONG).show();
        }
        else {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

            pasteData = item.getText().toString();

            input_show.setText(pasteData);
        }

    }
}
