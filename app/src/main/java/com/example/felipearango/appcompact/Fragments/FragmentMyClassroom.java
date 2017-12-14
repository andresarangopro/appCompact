package com.example.felipearango.appcompact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Aula;
import com.example.felipearango.appcompact.models.RecyclerAdapterClassroom;
import com.example.felipearango.appcompact.util.ManejoUser;
import com.example.felipearango.appcompact.util.Util;
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


    private RecyclerView mRecyclerDates;
    private RecyclerAdapterClassroom mDates;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_my_classroom, container, false);
        mn.inicializatedFireBase();
        initListAulas();
        initComponents();

        return view;
    }

    private void initXml(){
        mRecyclerDates = view.findViewById(R.id.rv_aula) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);

        mDates = new RecyclerAdapterClassroom(view.getContext(), lstAulas);
        mRecyclerDates.setAdapter( mDates);
    }


    private void initComponents(){
        txtKeyAula = view.findViewById(R.id.txtKeyAula);
        btnAggUserAula = view.findViewById(R.id.btnAggUserAula);
        btnAggUserAula.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnAggUserAula:
                if (!Util.emptyCampMSG(txtKeyAula, "Por favor ingrese el codigo del aula")) {
                    Aula aula = new Aula();
                    aula = findAula(txtKeyAula.getText().toString());
                    if (aula != null) {
                        if (!existUserInAula(aula, mn.firebaseUser.getUid())) {
                            //Se agrega el aula al usuario
                            mn.databaseReference.child("Users").child(mn.firebaseUser.getUid()).child("Aulas").push().setValue(txtKeyAula.getText().toString());
                            if(aula.getLstIntegrantes().get(0).equals(" ")) aula.getLstIntegrantes().clear();
                            aula.getLstIntegrantes().add(0, mn.firebaseUser.getUid());
                            //Se agrega el usuario al aula
                            mn.databaseReference.child("Aulas").child(aula.getKey()).setValue(aula);
                            Toast.makeText(getContext(), "Has sido agregado al aula", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getContext(), "Ya estas registrado en esta aula", Toast.LENGTH_SHORT).show();
                    } else txtKeyAula.setError("Esta aula no existe");
                }
        }

    }

    private void initListAulas(){
        mn.databaseReference.child("Aulas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot aula:dataSnapshot.getChildren()) {
                    Aula a = aula.getValue(Aula.class);
                    lstAulas.add(a);
                    if(a != null && !a.getLstIntegrantes().get(0).equals(" ")) {
                        for (String student :
                                a.getLstIntegrantes()) {
                            if (student.equals(mn.firebaseUser.getEmail()))
                                lstAulaStudentLog.add(a);
                        }
                    }
                }
                initXml();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    private boolean existUserInAula(Aula aula, String keyUser){
        for (String key:
             aula.getLstIntegrantes()) {
            if(key.equals(keyUser)) return true;
        }
        return false;
    }
}
