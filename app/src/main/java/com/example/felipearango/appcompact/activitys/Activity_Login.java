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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Activity_Login extends AppCompatActivity {

    Button btnIngresar;
    EditText txtEmail;
    EditText txtPassword;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        iniciar();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()) {
                    ingresar(txtEmail.getText().toString(), txtPassword.getText().toString());
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user == null){
                    //No esta logeado
                }else{
                    //esta logeado
                    Intent intent = new Intent(getApplicationContext(), Activity_Principal.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Sesion iniciada con email: " + user.getEmail(), Toast.LENGTH_LONG).show();
                }
            }
        };
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
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(listener != null){
            mAuth.removeAuthStateListener(listener);
        }
    }

    public boolean validarCampos(){
        if(txtEmail.getText().toString().equals("")){
            txtEmail.setError("Por favor ingrese un correo");
            return false;
        }else if(txtPassword.getText().toString().equals("")){
            txtPassword.setError("Por favor ingrese una contraseña");
            return false;
        } else return true;
    }

    public void iniciar(){
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }
}
