package com.hoque.securitypro;

import android.content.Context;
import android.widget.Toast;

public class Helper {

    public static void MakeText(Context context, String str){
        Toast.makeText(context,str, Toast.LENGTH_SHORT).show();
    }
}
