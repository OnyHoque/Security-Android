package com.hoque.securitypro;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PasswordGenerator extends AppCompatActivity {

    SeekBar seekBar;
    Switch sw1, sw2, sw3;
    EditText generatedPassword;
    Button generate, copy;
    TextView view_length;
    Vibrator vibe;
    int length = 8;
    boolean s1, s2, s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;

        sw1 = findViewById(R.id.switch_generator_capital);
        sw2 = findViewById(R.id.switch_generator_small);
        sw3 = findViewById(R.id.switch_generator_symbol);
        generate = findViewById(R.id.btn_generator_generate);
        copy = findViewById(R.id.btn_generator_Copy);
        seekBar = findViewById(R.id.genrator_seekBar);
        view_length = findViewById(R.id.view_genrator_viewLength);
        generatedPassword = findViewById(R.id.input_generator_generatedPass);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    s1 = true;
                } else {
                    s1 = false;
                }
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    s2 = true;
                } else {
                    s2 = false;
                }
            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    s3 = true;
                } else {
                    s3 = false;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                view_length.setText(""+progress);
                length = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String str = generate();
                generatedPassword.setText(str);
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(5);
                String str = generate();
                generatedPassword.setText(str);
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(5);
                String str = generatedPassword.getText().toString();
                if(str.length() <1){
                    Helper.MakeText(getApplicationContext(), "Drag the slider first.");
                }else{
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Password", str);
                    clipboard.setPrimaryClip(clip);
                    Helper.MakeText(getApplicationContext(), "copied to clipboard.");
                }
            }
        });
    }

    String generate(){
        char arr1[] = {'0', '1', '2', '3', '4', '5', '6', '7' ,'8', '9'};
        char arr2[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' ,'i', 'j' ,'k', 'l', 'm'
                , 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char arr3[] = {'!', '@', '#', '$', '%', '&', '*', '\'' ,'\"', '\\', '(', ')', '[', ']', '{', '}'};

        String str = "";
        try {
            while (str.length() < length) {
                int ran = new Random().nextInt(4);
                if (ran == 1 && s1) {
                    int val = new Random().nextInt(arr2.length);
                    str += ("" + arr2[val]).toUpperCase();
                } else if (ran == 2 && s2) {
                    int val = new Random().nextInt(arr2.length);
                    str += "" + arr2[val];
                } else if (ran == 3 && s3) {
                    int val = new Random().nextInt(arr3.length);
                    str += "" + arr3[val];
                } else {
                    int val = new Random().nextInt(arr1.length);
                    str += "" + arr1[val];
                }
            }
        }catch (Exception e){
            Helper.MakeText(getApplicationContext(), ""+e);
        }
        return str;
    }
}
