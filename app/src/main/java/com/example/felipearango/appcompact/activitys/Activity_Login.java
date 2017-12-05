package com.example.felipearango.appcompact.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.Util;
import com.google.firebase.auth.FirebaseAuth;


public class Activity_Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnIngresar;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView tvRegistrarse;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    private ManejoUser mn = new ManejoUser();
    public static boolean calledAlready = false;
    public static int TIPO_USUARIO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        initComponents();
        mn.inicializatedFireBase();
        verificaSignIn();
    }

    private void verificaSignIn(){
        if(mn.firebaseAuth.getCurrentUser() != null){
            mn.account(Activity_Login.this);
            finish();
        }
    }

    private void initComponents(){
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        tvRegistrarse = (TextView) findViewById(R.id.tvRegistrate);
        btnIngresar.setOnClickListener(this);
        tvRegistrarse.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int vista = v.getId();
        switch (vista){
            case R.id.btnIngresar:{
                if(!Util.emptyCampMSG(txtEmail, "Por favor ingrese un correo") &&
                        !Util.emptyCampMSG(txtEmail, "Por favor ingrese una contraseña")) {

                    mn.ingresar(txtEmail.getText().toString(), txtPassword.getText().toString(), Activity_Login.this);

                }
                break;
            }
            case R.id.tvRegistrate:{
                startActivity(new Intent(getApplicationContext(), Activity_Register.class));
                break;
            }
        }
    }


}
