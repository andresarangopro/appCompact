package com.example.felipearango.appcompact.util;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Felipe Arango on 29/11/2017.
 */

public class Util {

    public static int usuario_estudiante =1;
    public static int usuario_profesor =2;
    public static int usuario_empresa =3;

    public static boolean emptyCampMSG(EditText txt, String msg){
        if(emptyWT(txt))txt.setError(msg);
        return emptyWT(txt);
    }

    public static boolean emptyWT(EditText txt){
        return txt.getText().toString().equals("");
    }

    public static String geteTxt(EditText etText){
        return !etText.getText().toString().equals("")? etText.getText().toString(): "";
    }

    public static boolean spinerUser(Spinner sp , int valor, String mng){
        if(valor < 0)((TextView)sp.getChildAt(0)).setError(mng);
        return valor > 0;
    }

}
