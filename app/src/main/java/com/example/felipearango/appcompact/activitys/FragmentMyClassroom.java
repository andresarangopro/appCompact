package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
                        mn.databaseReference.child("Users").child(mn.firebaseUser.getUid()).child("Aulas").push().setValue(txtKeyAula.getText().toString());
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
}
