package com.hoque.securitypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PasswordGenerator extends AppCompatActivity {

    Button generate;
    EditText length_edit, show;
    CheckBox check1, check2, check3, check4;
    int length = -1;
    boolean checker1, checker2, checker3, checker4;
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);

        generate = (Button)findViewById(R.id.b_Generate);
        length_edit = (EditText)findViewById(R.id.editText_password);
        show = (EditText)findViewById(R.id.textView_generated_password);
        check1 = (CheckBox)findViewById(R.id.checkBox1);
        check2 = (CheckBox)findViewById(R.id.checkBox2);
        check3 = (CheckBox)findViewById(R.id.checkBox3);
        check4 = (CheckBox)findViewById(R.id.checkBox4);


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RunSteps();

            }
        });
    }

    private void RunSteps()
    {
        try
        {
            length = Integer.parseInt(length_edit.getText().toString());
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please enter only positive numbers or zero!",Toast.LENGTH_LONG).show();
        }

        if(length < 0)
        {
            Toast.makeText(getApplicationContext(),"Please enter only positive numbers or zero!",Toast.LENGTH_LONG).show();
            length = -1;
        }
        else
        {
            Check_checkBox();

            if(!(checker1 || checker2 || checker3 || checker4))
            {
                Toast.makeText(getApplicationContext(),"You have to check at lest 1 checkBox!",Toast.LENGTH_LONG).show();
            }
            else
            {
                generatePassword();
                show.setText(password);
                password = "";
                checker1 = false;
                checker2 = false;
                checker3 = false;
                checker4 = false;

            }
        }
    }

    private void Check_checkBox()
    {
        if(check1.isChecked())
        {
            checker1 = true;
        }
        if(check2.isChecked())
        {
            checker2 = true;
        }
        if(check3.isChecked())
        {
            checker3 = true;
        }
        if(check4.isChecked())
        {
            checker4 = true;
        }
    }

    private void generatePassword()
    {
        int c = 0;

        while(c < length)
        {
            Random rand = new Random();

            int num = rand.nextInt(4);

            if(num == 0 && checker1)
            {
                password += (char)(Random_Number());
                c++;
            }
            else if (num == 1 && checker2)
            {
                password += (char)(Random_Alphabet_Capital());
                c++;
            }
            else if(num == 2 && checker3)
            {
                password += (char)(Random_Alphabet_Small());
                c++;
            }
            else if(num == 3 && checker4)
            {
                password += (char)(Random_Symbol());
                c++;
            }
        }

    }

    private static int Random_Number()
    {
        Random rand = new Random();
        int value = rand.nextInt(10) + 48;

        return value;
    }

    private static int Random_Alphabet_Small()
    {
        Random rand = new Random();
        int value = rand.nextInt(26) + 97;

        return value;
    }

    private static int Random_Alphabet_Capital()
    {
        Random rand = new Random();
        int value = rand.nextInt(26) + 65;

        return value;
    }

    private static int Random_Symbol()
    {
        int a[] = {(int)'{', (int)'%', (int)'@',
                (int)')', (int)'@', (int)']', (int)'#', (int)':',
                (int)';', (int)'^', (int)',', (int)'.', (int)'?',
                (int)'!', (int)'|', (int)'&', (int)'_', (int)'~',
                (int)'@', (int)'$', (int)'%', (int)'=', (int)'+',
                (int)'-', (int)'"', (int)'@', (int)'/'};

        Random rand = new Random();
        int value = rand.nextInt(27);

        return a[value];
    }
}
