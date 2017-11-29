package com.example.felipearango.appcompact.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.models.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Activity_Login extends AppCompatActivity  implements View.OnClickListener{

    Button btnIngresar;
    EditText txtEmail;
    EditText txtPassword;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        mAuth = FirebaseAuth.getInstance();
        iniciar();
        verificaSignIn();
    }

    @Override
    public void onClick(View v) {
        int vista = v.getId();
        switch (vista) {
            case R.id.btnIngresar:{
                startActivity(new Intent(Activity_Login.this,Activity_Principal.class));
                 if(!Util.emptyCampMSG(txtEmail, "Ingrese email") && !Util.emptyCampMSG(txtPassword, "Ingrese password")) {
                     ingresar(txtEmail.getText().toString(), txtPassword.getText().toString());
                 }
                break;
            }
        }
    }

    private void verificaSignIn(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Activity_Principal.class));
            Toast.makeText(getApplicationContext(), "Sesion iniciada con email: " + user.getEmail(), Toast.LENGTH_LONG).show();
        }
    }

    public void cerrarSesion(){
        mAuth.signOut();
    }

    private void ingresar(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor verifique su usuario y contraseña", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(listener != null){
            mAuth.removeAuthStateListener(listener);
        }
    }

    public void iniciar(){
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIngresar.setOnClickListener(this);
    }

}
