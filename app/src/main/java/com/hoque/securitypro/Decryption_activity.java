package com.hoque.securitypro;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;

public class Decryption_activity extends AppCompatActivity {

    EditText de_encrypted_msg, de_password, show;
    Button b_de, b_de_paste;

    private String message = "", password = "";
    private String decryptedMSG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption_activity);

        b_de = (Button)findViewById(R.id.b_de);
        show = (EditText)findViewById(R.id.edit_decypted_msg);
        de_encrypted_msg = (EditText)findViewById(R.id.edit_de_encrypted_msg);
        de_password = (EditText)findViewById(R.id.edit_de_password);
        b_de_paste = (Button) findViewById(R.id.b_de_paste);

        b_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start_Decryption();
            }
        });
        b_de_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasteText();
            }
        });
    }

    private void Start_Decryption()
    {
        ViewData();

        String a[] = message.split(" ");
        int numeric[] = new int[a.length];

        try
        {
            for(int i = 0 ; i < a.length; ++i)
            {
                String value = a[i];
                numeric[i] = Integer.parseInt(value);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please put single space between characters", Toast.LENGTH_LONG).show();
        }

        char passC[] = password.toCharArray();

        int passN[] = new int[passC.length];

        for(int i = 0 ; i < passC.length ; ++i)
            passN[i] = (int)passC[i];

        int n = 0;

        char msgBody[] = new char[numeric.length];

        for(int i = 0 ; i < msgBody.length; ++i)
        {
            if(n < passN.length-1)
            {
                n++;
            }
            else
            {
                n=0;
            }

            if(i%2==1)
                msgBody[i] = (char)(numeric[i] - passN[n]);
            else
                msgBody[i] = (char)(numeric[i] + passN[n]);
        }

        for(int i = 0; i < msgBody.length; ++i)
        {
            decryptedMSG += msgBody[i];
        }


        show.setText(decryptedMSG);
        decryptedMSG = "";
    }

    private void ViewData()
    {
        try
        {
            message = de_encrypted_msg.getText().toString();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Encrypted message field empty!", Toast.LENGTH_LONG).show();
        }
        try
        {
            password = de_password.getText().toString();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Password field empty!", Toast.LENGTH_LONG).show();
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

            de_encrypted_msg.setText(pasteData);
        }

    }
}
