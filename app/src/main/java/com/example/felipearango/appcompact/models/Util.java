package com.example.felipearango.appcompact.models;

import android.widget.EditText;

/**
 * Created by Felipe Arango on 29/11/2017.
 */

public class Util {

    public static boolean emptyCampMSG(EditText txt, String msg){
        if(emptyWT(txt))txt.setError(msg);
        return emptyWT(txt);
    }

    public static boolean emptyWT(EditText txt){
        return txt.getText().toString().equals("");
    }


}
