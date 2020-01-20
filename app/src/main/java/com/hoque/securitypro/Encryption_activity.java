package com.hoque.securitypro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Encryption_activity extends AppCompatActivity {

    EditText original_msg, edit_en_pass, edit_en_encrypted_msg;
    Button b_en, b_en_copy;

    private String message = "";
    private String password = "";
    private String encrypted_MSG = "";

    public boolean ifCopy = false;
    private String copy_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_activity);
        b_en = (Button)findViewById(R.id.b_en);
        b_en_copy = (Button)findViewById(R.id.b_en_copy);
        original_msg = (EditText)findViewById(R.id.edit_original_msg);
        edit_en_pass = (EditText)findViewById(R.id.edit_en_pass);
        edit_en_encrypted_msg = (EditText)findViewById(R.id.edit_en_encrypted_msg);

        b_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartEncryption();
            }
        });

        b_en_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Copy_text();
            }
        });
    }

    private void StartEncryption()
    {
        getData();

        char msgBody[] = message.toCharArray();
        char passA[] = password.toCharArray();

        int msgN[] = new int[msgBody.length];
        int passN[] = new int[passA.length];

        for(int i = 0 ; i < passN.length; ++i)
            passN[i] = (int)passA[i];

        for(int i = 0 ; i < msgN.length; ++i)
            msgN[i] = (int)msgBody[i];

        int j = 0;

        int encrypted_message[] = new int[msgBody.length];

        for(int i = 0 ; i < encrypted_message.length; ++i)
        {
            if(j < passN.length-1)
            {
                ++j;
            }
            else
            {
                j = 0;
            }

            if(i%2==1)
                encrypted_message[i] = msgN[i] + passN[j];
            else
                encrypted_message[i] = msgN[i] - passN[j];
        }

        for(int i = 0 ; i < encrypted_message.length; ++i)
        {
            encrypted_MSG += encrypted_message[i] + " ";
        }

        edit_en_encrypted_msg.setText(encrypted_MSG);
        copy_text = encrypted_MSG;
        encrypted_MSG = "";
        ifCopy = true;
    }

    private void getData()
    {
        message = original_msg.getText().toString();
        password = edit_en_pass.getText().toString();
    }

    private void Copy_text()
    {
        if(ifCopy)
        {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("WordKeeper",copy_text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(),"Text copied to clipboard", Toast.LENGTH_LONG).show();
        }
    }
}
