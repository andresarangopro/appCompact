package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.felipearango.appcompact.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_Register extends AppCompatActivity {

    Button btnRegistrar;
    EditText txtEmail, txtPass, txtConfirPass;
    Spinner spnTipoUser;
    String [] tipos = {"Tipo de usuario", "Estudiante", "Profesor", "Empresa"};
    String tipoUser;
    public DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        iniciar();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipos);
        spnTipoUser.setAdapter(adapter);

        spnTipoUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        tipoUser = null;
                        break;
                    case 1:
                        tipoUser = "estudiante";
                        break;
                    case 2:
                        tipoUser = "profesor";
                        break;
                    case 3:
                        tipoUser = "empresa";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void iniciar(){
        btnRegistrar = (Button) findViewById(R.id.btnRegisterUser);
        txtEmail = (EditText) findViewById(R.id.txtRegistroEmail);
        txtPass = (EditText) findViewById(R.id.txtRegistroPass);
        txtConfirPass = (EditText) findViewById(R.id.txtRegistroConfirPass);
        spnTipoUser = (Spinner) findViewById(R.id.spnTipoUser);
    }
}
