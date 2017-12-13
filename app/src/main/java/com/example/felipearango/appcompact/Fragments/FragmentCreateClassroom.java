package com.example.felipearango.appcompact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.activitys.Activity_Principal;
import com.example.felipearango.appcompact.util.ManejoUser;
import com.example.felipearango.appcompact.util.Util;


public class FragmentCreateClassroom extends Fragment implements View.OnClickListener{

    Button btnPublicarAula;
    View view;
    EditText txtNomAula, txtDesAula;
    public static String nombre_aula = "";
    public static String descripcion_aula = "";
    public static String key_aula = "";
    private ManejoUser mn = new ManejoUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_classroom, container, false);
        initComponents();

        btnPublicarAula.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPublicarAula:{
                if(validarCampos()) {
                    nombre_aula = txtNomAula.getText().toString();
                    descripcion_aula = txtDesAula.getText().toString();
                    key_aula = mn.databaseReference.push().getKey();
                    Activity_Principal activity = (Activity_Principal) view.getContext();
                    FragmentClassroomCode fragmentClassroomCode = new FragmentClassroomCode();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragments, fragmentClassroomCode).addToBackStack(null).commit();
                }
                break;
            }
        }
    }

    private void initComponents(){
        btnPublicarAula = view.findViewById(R.id.btnPublicarAula);
        txtNomAula = view.findViewById(R.id.txtNombreAula);
        txtDesAula = view.findViewById(R.id.txtDescripcionAula);
        mn.inicializatedFireBase();
    }

    private boolean validarCampos(){
        return !Util.emptyCampMSG(txtNomAula,"Por favor ingrese el nombre del aula") &&
                !Util.emptyCampMSG(txtDesAula,"Por favor ingrese una descripcion del aula");
    }
}
