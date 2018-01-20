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
import com.example.felipearango.appcompact.util.ManejoUser;
import com.example.felipearango.appcompact.util.Util;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.felipearango.appcompact.util.Util.usuario_empresa;
import static com.example.felipearango.appcompact.util.Util.usuario_estudiante;
import static com.example.felipearango.appcompact.util.Util.usuario_profesor;

public class Activity_Register extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistrar;
    private EditText txtEmail, txtPass, txtConfirPass;
    private Spinner spnTipoUser;
    private final String[] tipos = {"Tipo de usuario", "Estudiante", "Profesor", "Empresa"};
    private int tipoUser;
    private ManejoUser mn = new ManejoUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        iniciar();
        mn.inicializatedFireBase();
    }


    private void iniciar(){
        btnRegistrar = findViewById(R.id.btnRegisterUser);
        txtEmail = findViewById(R.id.txtRegistroEmail);
        txtPass = findViewById(R.id.txtRegistroPass);
        txtConfirPass = findViewById(R.id.txtRegistroConfirPass);
        spnTipoUser = findViewById(R.id.spnTipoUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipos);
        spnTipoUser.setAdapter(adapter);
        btnRegistrar.setOnClickListener(this);
        spnTipoUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        tipoUser = -1;
                        break;
                    case 1:
                        tipoUser = usuario_estudiante;
                        break;
                    case 2:
                        tipoUser = usuario_profesor;
                        break;
                    case 3:
                        tipoUser = usuario_empresa;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegisterUser:{
                if(validarCampos()){
                    if(validarPass()){
                        mn.registrarUser(Util.geteTxt(txtEmail),Util.geteTxt(txtPass), Activity_Register.this, tipoUser);
                    }else{
                        txtConfirPass.setText("Las contraseñas no coinciden");
                    }
                }
                break;
            }
        }
    }

    private boolean validarCampos(){
        return !Util.emptyCampMSG(txtEmail,"Ingrese Correo") && !Util.emptyCampMSG(txtPass,"Ingrese contraseña") &&
                !Util.emptyCampMSG(txtConfirPass, "Ingrese confirmación contraseña");
    }

    private boolean validarPass(){
        return Util.geteTxt(txtPass).equals(Util.geteTxt(txtConfirPass));
    }
}
