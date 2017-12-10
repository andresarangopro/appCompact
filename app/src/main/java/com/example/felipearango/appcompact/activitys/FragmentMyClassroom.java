package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Aula;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentMyClassroom extends Fragment implements View.OnClickListener {

    View view;
    EditText txtKeyAula;
    Button btnAggUserAula;
    ManejoUser mn = new ManejoUser();
    ArrayList<Aula> lstAulas = new ArrayList<>();
    ArrayList<Aula> lstAulaStudentLog = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_my_classroom, container, false);
        mn.inicializatedFireBase();
        initListStudents();
        initComponents();

        return view;
    }



    private void initComponents(){
        txtKeyAula = view.findViewById(R.id.txtKeyAula);
        btnAggUserAula = view.findViewById(R.id.btnAggUserAula);
        btnAggUserAula.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAggUserAula:
                if(!Util.emptyCampMSG(txtKeyAula,"Por favor ingrese el codigo del aula")){
                    if(existAula(txtKeyAula.getText().toString())) {
                        if(!existUserInAula(mn.firebaseUser.getEmail())) {
                            mn.databaseReference.child("Users").child(mn.firebaseUser.getUid()).child("Aulas").push().setValue(txtKeyAula.getText().toString());
                            Aula aula = new Aula();
                            aula = findAula(txtKeyAula.getText().toString());
                            aula.getLstIntegrantes().add(0, mn.firebaseUser.getEmail());
                            mn.databaseReference.child("Aulas").child(aula.getKey()).setValue(aula);
                            Toast.makeText(getContext(), "Has sido agregado al aula", Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(getContext(), "Ya estas registrado en esta aula", Toast.LENGTH_SHORT).show();
                    }else txtKeyAula.setError("Esta aula no existe");
                }
        }
    }

    private void initListStudents(){
        mn.databaseReference.child("Aulas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot aula:dataSnapshot.getChildren()) {
                    Aula a = aula.getValue(Aula.class);
                    lstAulas.add(a);

                    for (String student:
                         a.getLstIntegrantes()) {
                        if(student.equals(mn.firebaseUser.getEmail())) lstAulaStudentLog.add(a);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean existAula(String key){
        for (Aula aula:
             lstAulas) {
            if(aula.getKey().equals(key)) return true;
        }
        return false;
    }

    private Aula findAula(String key){
        for (Aula aula:
             lstAulas) {
            if(aula.getKey().equals(key)){
                return aula;
            }
        }
        return null;
    }

    private boolean existUserInAula(String emailUser){
        for (Aula aula:
             lstAulaStudentLog) {
            for (String email:
                 aula.getLstIntegrantes()) {
                if(email.equals(emailUser)) return true;
            }
        }
        return false;
    }
}
