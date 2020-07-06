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

import java.util.StringTokenizer;

public class Decryption_activity extends AppCompatActivity {

    Button btn_paste, btn_decrypt, btn_copy;
    EditText input_encrypted_msg, input_password;
    TextView view_original_msg;
    Vibrator vibe;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption_activity);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        btn_paste = findViewById(R.id.btn_decryption_paste);
        btn_decrypt = findViewById(R.id.btn_decryption_decrypt);
        btn_copy = findViewById(R.id.btn_decryption_copy);
        input_encrypted_msg = findViewById(R.id.input_decryption_encrypted_msg);
        input_password = findViewById(R.id.input_decryption_password);
        view_original_msg = findViewById(R.id.view_decryption_original_msg);
        linearLayout = findViewById(R.id.layout_decryption_show_message);
        linearLayout.setVisibility(View.GONE);

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
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(5);
                Copy_text();
            }
        });
    }


    private void Start_Decryption()
    {
        String message = input_encrypted_msg.getText().toString();
        String password = input_password.getText().toString();

        if(message.length() > 0 && password.length() > 0){
            String hashed_password = AESUtils.secret_key_maker(password);
            try{
                String decrypted_message = AESUtils.decrypt(message, hashed_password);
                linearLayout.setVisibility(View.VISIBLE);
                view_original_msg.setText(decrypted_message);
            }catch (Exception e){
                Helper.MakeText(getApplicationContext(), "Wrong Password!");
            }
        }else{
            Helper.MakeText(getApplicationContext(), "Enter message and password first!");
        }
    }


    private void PasteText()
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData;
        if (!(clipboard.hasPrimaryClip()))
        {
            Helper.MakeText(getApplicationContext(), "Nothing is saved on Clipboard!");
        }
        else {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            pasteData = item.getText().toString();
            input_encrypted_msg.setText(pasteData);
        }
    }


    private void Copy_text()
    {
        String str = view_original_msg.getText().toString();
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
